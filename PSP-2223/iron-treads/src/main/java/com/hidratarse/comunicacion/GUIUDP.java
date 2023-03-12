package com.hidratarse.comunicacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.SocketException;

public class GUIUDP {
    private JFrame frame;
    private JPanel inputPanel, outputPanel;
    private JTextField inputField;
    private JButton sendButton;
    private JTextArea outputArea;
    private SocketUDP socket;

    public GUIUDP(String ip, int port) throws SocketException {
        this.socket = new SocketUDP(port);

        // Crear los componentes de la interfaz gráfica
        inputField = new JTextField(20);
        sendButton = new JButton("Enviar");
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        // Crear los paneles de entrada y salida
        inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(sendButton);

        outputPanel = new JPanel();
        outputPanel.add(new JScrollPane(outputArea));

        // Agregar los paneles a la ventana principal
        frame = new JFrame("Cliente UDP");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(outputPanel, BorderLayout.CENTER);

        // Agregar un ActionListener al botón de enviar
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                inputField.setText("");
                try {
                    socket.enviarMensaje(message, ip, port);
                    outputArea.append("Cliente: " + message + "\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Agregar un WindowListener para cerrar el socket cuando se cierre la ventana
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                socket.cerrar();
            }
        });

        // Mostrar la ventana principal
        frame.pack();
        frame.setVisible(true);

        // Iniciar un hilo para recibir mensajes del servidor
        new Thread(new Receiver(socket)).start();
    }

    class Receiver implements Runnable {
        private SocketUDP socket;

        public Receiver(SocketUDP socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                while (true) {
                    String message = this.socket.recibirMensaje();
                    outputArea.append("Servidor: " + message + "\n");
                    if (message.equalsIgnoreCase(ClienteUDP.FIN)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                socket.cerrar();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length <= 1) {
            System.out.println("Parametros [ip] [Puerto]");
            System.exit(0);
        }
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            new GUIUDP(ip, port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}