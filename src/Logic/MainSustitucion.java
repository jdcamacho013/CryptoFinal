/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author User
 */
public class MainSustitucion {
    public static void main(String[] args) {
        String textoClaro = "ESTE CIFRADO sutitucion FUNCIONA EN ESPAÑOL";
        Sustitucion vigenere = new Sustitucion ();
        System.out.println("El cifrado con la clave aleatorea: "+vigenere.getKey()+" da como resultado "+vigenere.encrypt(textoClaro));
        vigenere.setKey("POIUYTREWQÑLKJHGFDSAMNBVCXZ");
        System.out.println("El cifrado con la clave fija: "+vigenere.getKey()+" da como resultado "+vigenere.encrypt(textoClaro));
        String textoCifrado = vigenere.encrypt(textoClaro);
        System.out.println("Tambien se puede desencriptar: "+ vigenere.decrypt(textoCifrado));
    }    
}
