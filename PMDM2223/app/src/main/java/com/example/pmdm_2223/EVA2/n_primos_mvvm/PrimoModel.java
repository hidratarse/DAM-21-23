package com.example.pmdm_2223.EVA2.n_primos_mvvm;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

public class PrimoModel {
    private static final double MAX_DELAY = 1000;
    private int nPrimos;

    public PrimoModel(){
        nPrimos=0;
    }

    public int getNPrimos(){
        return nPrimos;
    }

    public void generarNPrimos(int rango1,int rango2){
        for (int i = rango1; i <= rango2; i++) {
            performAction();
            if (isPrime (i)) {
                Log.d(TAG,String.valueOf(i));
                nPrimos++;
            }
        }
    }

    public static boolean isPrime (int n) {
        if (n < 2) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
    private static void performAction(){
        try {
            Thread.sleep((int)(Math.random()*MAX_DELAY));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
