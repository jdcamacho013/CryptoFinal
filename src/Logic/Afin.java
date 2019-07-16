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
 * @author User
 */
public class Afin extends Encrypter{
    private int[] key={9,3};
    private int n;
    private final String abc = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private TreeMap<Character, Integer> abc_inv = new TreeMap<>();
    
    
    
    Afin(){
        for (int i=0;i<abc.length(); ++i){
            abc_inv.put( abc.charAt(i) , i);
        }
        n= abc.length();
        this.generateRandomKey();
    }

    public int[] getKey() {
        return key;
    }
    public int mcd (int b,int a){
    int m = b;
    int mcd = b;
    while (m!=0){
        mcd = m;
       m = a%m;
    }
    return mcd;
    }

    public void setKey(int[] key) {
        if (key[0]>=n){
            throw new IllegalArgumentException("El primer valor debe ser un numero entero entre 0 y "+(n-1));
        }
        if (this.mcd(key[0], n)!=1){
            throw new IllegalArgumentException("El primer numero debe ser primo relativo con "+n);
        }
        if (key[1]>=n){
            throw new IllegalArgumentException("El segundo valor debe ser un numero entero entre 0 y "+(n-1));
        }
        this.key = key;
    }
    
    public int inverse(int p){
    int ans = 2;
    while(ans*p%n!=1){
        ans++;
    }
    return ans;
    }
    

    @Override
    public void generateRandomKey() {
        key[0] = ThreadLocalRandom.current().nextInt(0, n + 1);
        while(this.mcd(key[0], n) !=1){
            key[0] = ThreadLocalRandom.current().nextInt(0, n + 1);
        }
        key[1] = ThreadLocalRandom.current().nextInt(0, n + 1);
    }

    @Override
    public String encrypt(String textoClaro) {
        String texto = textoClaro.replaceAll(" ", "").toUpperCase();
        String ans = "";
        for (Character c: texto.toCharArray()){
            ans+= Character.toString(abc.charAt((abc_inv.get(c)*key[0]+key[1]+n)%n) );
        }
        return ans;
    }

    @Override
    public String decrypt(String textoCifrado) {
        String texto = textoCifrado.replaceAll(" ", "").toUpperCase();
        String ans="";
        for (Character c: texto.toCharArray()){
            ans+= Character.toString(abc.charAt(((abc_inv.get(c)+n-key[1])*this.inverse(key[0])%n)));
        }
        return ans;
    }
    
}
