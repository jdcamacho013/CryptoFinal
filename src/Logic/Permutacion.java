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
 * @author User
 */
public class Permutacion extends Encrypter{
     private ArrayList<Integer> key= new ArrayList<Integer>();
    private int n;
    private int len;
    private final String abc = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private TreeMap<Character, Integer> abc_inv = new TreeMap<>();
    
    
    
    Permutacion(String text){
        String texto = text.replaceAll(" ", "").toUpperCase();
        for (int i=0;i<abc.length(); ++i){
            abc_inv.put( abc.charAt(i) , i);
        }
        n= abc.length();
        len = texto.length();
        this.generateRandomKey();
    }

    public String getKey() {
        String pass = "";
         for (int i=0;i<key.size(); ++i){
             pass+= key.get(i)+",";
        }
        return pass;
    }

    public void setKey(String key) {
        this.key.clear();
        String texto = key.replaceAll(" ", "").toUpperCase();
        String[] tempArray;
        tempArray = texto.split("\\,");
        for (int i=0; i<tempArray.length;i++){
            this.key.add(Integer.parseInt(tempArray[i]));
        }
    }  

    @Override
    public void generateRandomKey() {
        this.key.clear();
        key.add(ThreadLocalRandom.current().nextInt(0, len));
        for(int i =1; i< len;i++){
            int k = ThreadLocalRandom.current().nextInt(0, len);
            while (key.contains(k)){
                k = ThreadLocalRandom.current().nextInt(0, len);
        }
            key.add(k);
        }
    }

    @Override
    public String encrypt(String textoClaro) {
        String texto = textoClaro.replaceAll(" ", "").toUpperCase();
        String ans = "";
        for (int i =0; i<key.size();i++){
            ans+= Character.toString(texto.charAt(key.get(i)));
        }
        return ans;
    }

    @Override
    public String decrypt(String textoCifrado) {
        String texto = textoCifrado.replaceAll(" ", "").toUpperCase();
        String ans="";
        for (int i =0; i<key.size();i++){
            ans+= Character.toString(texto.charAt(key.indexOf(i)));
        }
        return ans;
    }
}
