/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author gabri
 */
public class DESMain {
    
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        
        
        DES encrypter;
        try {
            //la clave tiene que tener longitud par y estar en hexadecimal, con letras abcdef en minuscula
            encrypter = new DES("0123456789abcdef91283461925211");
            String encrypted = encrypter.encrypt("Es un secreto tututu que tu mirada y la mia tututu un presentimiento");
            String decrypted = encrypter.decrypt(encrypted);
            System.out.println(encrypted);
            System.out.println(decrypted);
            //usando una clave random 
            encrypter.generateRandomKey();
            System.out.println("Clave random : "+ encrypter.getClave());
            encrypted = encrypter.encrypt("Es un secreto tututu que tu mirada y la mia tututu un presentimiento");
            decrypted = encrypter.decrypt(encrypted);
            System.out.println(encrypted);
            System.out.println(decrypted);
        } catch (Exception ex) {
            Logger.getLogger(DESMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
  

}
