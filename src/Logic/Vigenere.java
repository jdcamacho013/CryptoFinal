/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author gabriel
 */
public class Vigenere extends Encrypter{
    private ArrayList<Integer> key= new ArrayList<Integer>();
    private int n;
    private final String abc = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private TreeMap<Character, Integer> abc_inv = new TreeMap<>();
    
    
    
    Vigenere(){
        for (int i=0;i<abc.length(); ++i){
            abc_inv.put( abc.charAt(i) , i);
        }
        n= abc.length();
        this.generateRandomKey();
    }

    public String getKey() {
        String ans = "";
        for (int i=0;i<key.size(); ++i){
            ans+=Character.toString(abc.charAt(key.get(i)) );
        }
        return ans;
    }

    public void setKey(String password) {
        String validPassword = password.replaceAll(" ", "").toUpperCase();
        this.key.clear();
        for (Character c: validPassword.toCharArray()){
             int key = abc_inv.get(c);
             this.key.add(key);
        }
    }
    
    
    

    @Override
    public void generateRandomKey() {
        this.key.clear();
        for(int i=0;i<9; ++i){
        key.add(ThreadLocalRandom.current().nextInt(0, n + 1)) ;}
    }

    @Override
    public String encrypt(String textoClaro) {
        String texto = textoClaro.replaceAll(" ", "").toUpperCase();
        String ans = "";
        int j = 0;
        for (Character c: texto.toCharArray()){
            ans+= Character.toString(abc.charAt((abc_inv.get(c)+key.get(j)+n)%n) );
            j = (j+1)%(key.size());
        }
        return ans;
    }

    @Override
    public String decrypt(String textoCifrado) {
        String texto = textoCifrado.replaceAll(" ", "").toUpperCase();
        String ans="";
        int j = 0;
        for (Character c: texto.toCharArray()){
            ans+= Character.toString(abc.charAt((abc_inv.get(c)+n-key.get(j))%n));
            j = (j+1)%(key.size());
        }
        return ans;
    }

}
