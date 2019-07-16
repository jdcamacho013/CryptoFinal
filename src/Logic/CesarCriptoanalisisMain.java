/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import static Logic.VigenereCriptoanalisisMain.getIndexOfLargest;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class CesarCriptoanalisisMain {
    public static void main(String[] args) {
        VigenereCriptoanalisis v= new VigenereCriptoanalisis();
        String s = "AOPZPZCLYFIVYPUNPDHUAAVNVVBAHUKWSHFSLAAOPZZLTLZALYLUKMVYNVKZZHRL";
        ArrayList<ArrayList<Double> > m = v.indiceEsperadoTamanoClave(s, 1);
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int idx = getIndexOfLargest(m.get(0));
        String clave =Character.toString(abc.charAt(idx));
        System.out.println("Al finar tenemos que la solucion automatica es ");
        System.out.println(v.decifrar(s, clave));
        
        System.out.println("todas las soluciones son:");
        for (Character letter: abc.toCharArray()){
            System.out.println(v.decifrar(s, Character.toString(letter)));
        }
        
    }
}
