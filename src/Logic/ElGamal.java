/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import java.math.*;
import java.util.*;
import java.security.*;
import java.io.*;
/**
 *
 * @author User
 */
public class ElGamal {
    private BigInteger p, b, c, secretKey;
    ElGamal(){
        Random sc = new SecureRandom();
        secretKey = new BigInteger("12345678901234567890");
        p = BigInteger.probablePrime(64, sc);
        b = new BigInteger("3");
        c = b.modPow(secretKey, p);
        }
    public BigInteger getP(){
    return p;
    }
    public BigInteger getB(){
    return b;
    }
    public BigInteger getC(){
    return c;
    }
    public BigInteger getSecretKey(){
    return secretKey;
    }
    public BigInteger[] ecrypt(String bigNumber){
        Random sc = new SecureRandom();
    BigInteger X = new BigInteger(bigNumber);
        BigInteger r = new BigInteger(64, sc);
        BigInteger EC = X.multiply(c.modPow(r, p)).mod(p);
        BigInteger brmodp = b.modPow(r, p);
        BigInteger[] y ={brmodp,EC};
        return y;
    }
    public BigInteger decrypt(BigInteger[]y){
         BigInteger crmodp = y[0].modPow(secretKey, p);
        BigInteger d = crmodp.modInverse(p);
        BigInteger ad = d.multiply(y[1]).mod(p);
        return ad;
    }
}
