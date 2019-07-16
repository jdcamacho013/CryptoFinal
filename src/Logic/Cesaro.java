/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author gabriel
 */
public class Cesaro extends Encrypter{
    private int key;
    private int n;
    private final String abc = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private TreeMap<Character, Integer> abc_inv = new TreeMap<>();
    
    
    
    Cesaro(){
        for (int i=0;i<abc.length(); ++i){
            abc_inv.put( abc.charAt(i) , i);
        }
        n= abc.length();
        this.generateRandomKey();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        if (key>=n){
            throw new IllegalArgumentException("La llave es un numero entero entre 0 y "+(n-1));
        }
        this.key = key;
    }
    
    
    

    @Override
    public void generateRandomKey() {
        key = ThreadLocalRandom.current().nextInt(0, n + 1);
    }

    @Override
    public String encrypt(String textoClaro) {
        String texto = textoClaro.replaceAll(" ", "").toUpperCase();
        String ans = "";
        for (Character c: texto.toCharArray()){
            ans+= Character.toString(abc.charAt((abc_inv.get(c)+key+n)%n) );
        }
        return ans;
    }

    @Override
    public String decrypt(String textoCifrado) {
        String texto = textoCifrado.replaceAll(" ", "").toUpperCase();
        String ans="";
        for (Character c: texto.toCharArray()){
            ans+= Character.toString(abc.charAt((abc_inv.get(c)+n-key)%n));
        }
        return ans;
    }
}
