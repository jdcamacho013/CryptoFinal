/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.xml.bind.DatatypeConverter;

class DES {
    Cipher ecipher;
    Cipher dcipher;
    String clave;

    DES(String clave) throws Exception {
        this.clave= clave;
        byte[] keyBytes = DatatypeConverter.parseHexBinary(clave);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        SecretKey key = factory.generateSecret(new DESKeySpec(keyBytes));
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }

    public String getClave() {
        return clave;
    }

    public void generateRandomKey() throws Exception{
        int count = 20;
        String ALPHA_NUMERIC_STRING = "abcdef0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
        int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
        builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        setKey(builder.toString());
    }
    public String encrypt(String str) throws Exception {
      // Encode the string into bytes using utf-8
      byte[] utf8 = str.getBytes("UTF8");

      // Encrypt
      byte[] enc = ecipher.doFinal(utf8);

      // Encode bytes to base64 to get a string
      return new sun.misc.BASE64Encoder().encode(enc);
    }

    public String decrypt(String str) throws Exception {
      // Decode base64 to get bytes
      byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

      byte[] utf8 = dcipher.doFinal(dec);

      // Decode using utf-8
      return new String(utf8, "UTF8");
    }

    private void setKey(String clave) throws Exception {
        this.clave= clave;
        byte[] keyBytes = DatatypeConverter.parseHexBinary(clave);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        SecretKey key = factory.generateSecret(new DESKeySpec(keyBytes));
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }
}

