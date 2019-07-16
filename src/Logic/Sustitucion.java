/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class Sustitucion extends Encrypter{
    private ArrayList<Integer> key= new ArrayList<Integer>();
    private int n;
    private final String abc = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private TreeMap<Character, Integer> abc_inv = new TreeMap<>();
    
    
    
    Sustitucion(){
        for (int i=0;i<abc.length(); ++i){
            abc_inv.put( abc.charAt(i) , i);
        }
        n= abc.length();
        this.generateRandomKey();
    }

    public String getKey() {
        String pass = "";
         for (int i=0;i<key.size(); ++i){
             pass+= Character.toString(abc.charAt(key.get(i)) );
        }
        return pass;
    }

    public void setKey(String key) {
        this.key.clear();
        String texto = key.replaceAll(" ", "").toUpperCase();
        for (Character c: texto.toCharArray()){
            this.key.add((abc_inv.get(c)+n)%n);
        }
    }  

    @Override
    public void generateRandomKey() {
        this.key.clear();
        key.add(ThreadLocalRandom.current().nextInt(0, n));
        for(int i =1; i< abc.length();i++){
            int k = ThreadLocalRandom.current().nextInt(0, n);
            while (key.contains(k)){
                k = ThreadLocalRandom.current().nextInt(0, n);
        }
            key.add(k);
        }
    }

    @Override
    public String encrypt(String textoClaro) {
        String texto = textoClaro.replaceAll(" ", "").toUpperCase();
        String ans = "";
        for (Character c: texto.toCharArray()){
            ans+= Character.toString(abc.charAt(key.get((abc_inv.get(c)+n)%n)));
        }
        return ans;
    }

    @Override
    public String decrypt(String textoCifrado) {
        String texto = textoCifrado.replaceAll(" ", "").toUpperCase();
        String ans="";
        for (Character c: texto.toCharArray()){
            ans+= Character.toString(abc.charAt(key.indexOf((abc_inv.get(c)+n)%n)));
        }
        return ans;
    }
}
