/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

public class MainAfin {
     public static void main(String[] args) {
        String textoClaro = "ESTE CIFRADO afin FUNCIONA EN ESPAÑOL";
        Afin afin = new Afin ();
        System.out.println("El cifrado con la clave aleatorea: ["+afin.getKey()[0]+","+afin.getKey()[1]+"] da como resultado "+afin.encrypt(textoClaro));
        int[] key = {13,24};
        afin.setKey(key);
        System.out.println("El cifrado con la clave fija: ["+afin.getKey()[0]+","+afin.getKey()[1]+"] da como resultado "+afin.encrypt(textoClaro));
        String textoCifrado = afin.encrypt(textoClaro);
        System.out.println("Tambien se puede desencriptar: "+ afin.decrypt(textoCifrado));
        //Ingresando una llave invalida, da un error
        key[0] = 30; 
        try{
            afin.setKey(key);
        }catch(Exception ex){
            System.out.println("Se ingresó una clave invalida");
        }
        //Ingresando una llave invalida, da un error
        key[0] = 13;
        try{
            afin.setKey(key);
        }catch(Exception ex){
            System.out.println("Se ingresó una clave invalida");
        }
    }
    
}
