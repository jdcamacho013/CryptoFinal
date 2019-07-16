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
public abstract class Encrypter {
    
    public abstract void generateRandomKey();
    public abstract String encrypt (String textoClaro);
    public abstract String decrypt (String textoCifrado);
    
}
