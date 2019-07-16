/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author User
 */
public class Rsa {

    KeyPairGenerator kpg;
    PublicKey publicKey;
    PrivateKey privateKey;
    KeyFactory kf;

    Rsa() throws NoSuchAlgorithmException, NoSuchProviderException {
        kpg = KeyPairGenerator.getInstance("RSA");
        kf = KeyFactory.getInstance("RSA");
        kpg.initialize(2048);
        this.generateRandomKey();
    }

    public StringBuffer getPublicKey() {
        byte[] publicKeyBytes = publicKey.getEncoded();
        StringBuffer key = new StringBuffer();
        for (int i = 0; i < publicKeyBytes.length; ++i) {
            key.append(Integer.toHexString(0x0100 + (publicKeyBytes[i] & 0x00FF)).substring(1));
        }
        return key;
    }

    public StringBuffer getPrivateKey() {
        byte[] privateKeyBytes = privateKey.getEncoded();
        StringBuffer key = new StringBuffer();
        for (int i = 0; i < privateKeyBytes.length; ++i) {
            key.append(Integer.toHexString(0x0100 + (privateKeyBytes[i] & 0x00FF)).substring(1));
        }
        return key;
    }

    public void setPublicKey(String file) throws InvalidKeySpecException, IOException {
        /* Read all bytes from the private key file */
        byte[] bytes = Files.readAllBytes(Paths.get(file));
        /* Generate private key. */
        X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
        publicKey = kf.generatePublic(ks);
    }

    public void setPrivateKey(String file) throws InvalidKeySpecException, IOException {
        /* Read all bytes from the private key file */
        byte[] bytes = Files.readAllBytes(Paths.get(file));
        /* Generate private key. */
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
        privateKey = kf.generatePrivate(ks);
    }

    public void saveKeys(String publicKeyName, String privateKeyName) throws FileNotFoundException, IOException {
        byte[] publicKeyBytes = publicKey.getEncoded();
        FileOutputStream fos = new FileOutputStream(publicKeyName);
        fos.write(publicKeyBytes);
        fos.close();
        byte[] privateKeyBytes = privateKey.getEncoded();
        FileOutputStream pfos = new FileOutputStream(privateKeyName);
        pfos.write(privateKeyBytes);
        pfos.close();
    }

    public byte[] signMessage(String data) throws InvalidKeyException, Exception {
        Signature sign = Signature.getInstance("SHA1withRSA");
        sign.initSign(privateKey);
        sign.update(data.getBytes());
        return sign.sign();
    }

    public void writeToFile(String filename, String data) throws FileNotFoundException, IOException, Exception {
        List<byte[]> message = new ArrayList<>();
        message.add(data.getBytes());
        message.add(signMessage(data));
        File f = new File(filename);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(message);
        out.close();
    }

    public boolean verifyMessage(String filename) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        List<byte[]> list = (List<byte[]>) in.readObject();
        in.close();
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(publicKey);
        sig.update(list.get(0));
        return sig.verify(list.get(1));
    }

    public void generateRandomKey() {
        KeyPair kp = kpg.generateKeyPair();
        publicKey = kp.getPublic();
        privateKey = kp.getPrivate();

    }

    public String encrypt(String plainText) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }
    
     public String decrypt(String cipherText) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes), UTF_8);
    }
    
}
