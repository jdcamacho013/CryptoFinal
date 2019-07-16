/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.math.BigInteger;

/**
 *
 * @author User
 */
public class MainElGamal {
    public static void main(String[] args) {
        //solo cifra numeros
    String textoClaro = "1094575234848";
    ElGamal elGamal = new ElGamal();
    System.out.print("secretKey = " + elGamal.getSecretKey());
    System.out.println ("p = " +  elGamal.getP());
    System.out.println ("b = " + elGamal.getB());
    System.out.println ("c = " + elGamal.getC());
    BigInteger[] cifer = elGamal.ecrypt(textoClaro);
    System.out.println ("Al encriptar se obtiene: " + cifer);
    BigInteger clear = elGamal.decrypt(cifer);
    System.out.println ("Al desencriptar se obtiene: " + clear);
    }
}
