/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author gabriel
 */
public class MainCesaro {
    public static void main(String[] args) {
        String textoClaro = "ESTE CIFRADO Cesaro FUNCIONA EN ESPAÑOL";
        Cesaro cesaro = new Cesaro ();
        System.out.println("El cifrado con la clave aleatorea: "+cesaro.getKey()+" da como resultado "+cesaro.encrypt(textoClaro));
        cesaro.setKey(1);
        System.out.println("El cifrado con la clave fija: "+cesaro.getKey()+" da como resultado "+cesaro.encrypt(textoClaro));
        String textoCifrado = cesaro.encrypt(textoClaro);
        System.out.println("Tambien se puede desencriptar: "+ cesaro.decrypt(textoCifrado));
        //Ingresando una llave invalida, da un error
        try{
            cesaro.setKey(69420);
        }catch(Exception ex){
            System.out.println("Se ingresó una clave invalida");
        }
    }
}
