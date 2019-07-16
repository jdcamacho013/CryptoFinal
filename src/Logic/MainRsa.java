/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class MainRsa {
      public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException, Exception {
          Rsa encrypter = new Rsa();
          String text = "Funciona para el Español?";
          //salva la clave generada
          encrypter.saveKeys("publicKeyName", "privateKeyName");
           System.out.println("El cifrado con la clave aleatorea: ["+encrypter.getPublicKey()+","+encrypter.getPrivateKey()+"] da como resultado "+encrypter.encrypt(text));
           encrypter.setPublicKey("publicKeyName");
           encrypter.setPrivateKey("privateKeyName");
           System.out.println("El cifrado con la clave fija: ["+encrypter.getPublicKey()+","+encrypter.getPrivateKey()+"]");
           System.out.println(" da como resultado "+encrypter.encrypt(text));
           String message = encrypter.encrypt(text);
           PrivateKey key ;
           System.out.println("Tambien se puede desencriptar: "+ encrypter.decrypt(message));
           //crea documento firmado
            encrypter.writeToFile("Nombre del documento", "Mensaje para firmar");
            //documento firmado
           System.out.println("El mensaje fue firmado:"+ encrypter.verifyMessage("Nombre del documento"));
           //documento no firmado firmado
           System.out.println("El mensaje fue firmado:"+ encrypter.verifyMessage("Nombre del documentoF"));
           
        
    }
    
}
