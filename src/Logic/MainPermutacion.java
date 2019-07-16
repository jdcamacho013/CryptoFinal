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
public class MainPermutacion {
    public static void main(String[] args) {
        String textoClaro = "ESTE CIFRADO Permutacion FUNCIONA EN ESPAÑOL";
        Permutacion permutacion = new Permutacion (textoClaro);
        System.out.println("El cifrado con la clave aleatorea: "+permutacion.getKey()+" da como resultado "+permutacion.encrypt(textoClaro));
        permutacion.setKey("19,27,2,0,22,32,33,5,36,4,31,28,25,34,6,3,11,14,23,21,38,24,30,20,26,10,12,15,18,9,29,13,16,1,37,35,7,8,17");
        System.out.println("El cifrado con la clave fija: "+permutacion.getKey()+" da como resultado "+permutacion.encrypt(textoClaro));
        String textoCifrado = permutacion.encrypt(textoClaro);
        System.out.println("Tambien se puede desencriptar: "+ permutacion.decrypt(textoCifrado));
    }    
}
