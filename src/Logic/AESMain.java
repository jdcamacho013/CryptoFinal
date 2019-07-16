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
public class AESMain {
    public static void main(String[] args){
        AES aes = new AES();
        String secretKey = "usingrandomkeyfor this shit";

        String originalString = "this is vey usefull :v";
        aes.setKey(secretKey);
        String encryptedString = aes.encrypt(originalString) ;
        String decryptedString = aes.decrypt(encryptedString) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
        //Usando una llave aleatorea
        aes.generateRandomKey();
        originalString = "this is very usefull :v";
        System.out.println("clave aleatorea: "+ aes.getClave());
        encryptedString = aes.encrypt(originalString) ;
        decryptedString = aes.decrypt(encryptedString) ;
        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }
}
