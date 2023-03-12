package com.hidratarse.comunicacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class GUITCP {

    private Socket socket;
    private DataInputStream dataInput;
    private DataOutputStream dataOut;
    private JTextField ipField;
    private JTextField portField;
    private JTextField messageField;
    private JLabel responseLabel;

    public GUITCP() {
        // Crear la disposición de la GUI
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Crear los elementos de la GUI
        JLabel ipLabel = new JLabel("Dirección IP:");
        panel.add(ipLabel);

        ipField = new JTextField();
        panel.add(ipField);

        JLabel portLabel = new JLabel("Puerto:");
        panel.add(portLabel);

        portField = new JTextField();
        panel.add(portField);

        JLabel messageLabel = new JLabel("Mensaje:");
        panel.add(messageLabel);

        messageField = new JTextField();
        panel.add(messageField);

        JButton sendButton = new JButton("Enviar");
        panel.add(sendButton);

        responseLabel = new JLabel();
        panel.add(responseLabel);

        // Agregar la acción al botón de envío
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Conectar con el servidor
                    socket = new Socket(ipField.getText(), Integer.parseInt(portField.getText()));
                    dataInput = new DataInputStream(socket.getInputStream());
                    dataOut = new DataOutputStream(socket.getOutputStream());

                    // Enviar el mensaje y recibir la respuesta
                    String message = messageField.getText();
                    dataOut.writeUTF(message);
                    String response = dataInput.readUTF();
                    responseLabel.setText(response);

                    // Cerrar la conexión
                    socket.close();
                    dataInput.close();
                    dataOut.close();
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                    responseLabel.setText("No se pudo conectar al servidor.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    responseLabel.setText("Error de entrada/salida.");
                }
            }
        });

        // Crear la ventana y mostrar la GUI
        JFrame frame = new JFrame("Cliente TCP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUITCP();
    }
}
