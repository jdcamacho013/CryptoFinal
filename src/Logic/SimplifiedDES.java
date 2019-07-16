/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.DataInputStream;
import java.util.Random;

public class SimplifiedDES{
       
    public static void main( String args[]) throws Exception{
        //Me parecio bacano que mostrara todo el proceso entonces lo deje, no se si lo quiera meter
        
        //la clave es un numero de maximo 10 bits es decir 2^10
        int K =1023; 
        SDES A = new SDES( K);
        //este es el texto claro, que es un numero de maximo 2^8
        int m = 205;
        System.out.print("\nKey K1: ");
        SDES.printData( A.K1, 8);
        System.out.print("\nKey K2: ");
        SDES.printData( A.K2, 8);
        m = A.encrypt( m);
        System.out.print("\nEncrypted Message: ");
        SDES.printData( m, 8);
        m = A.decrypt( m);
        System.out.print("\nDecrypted Message: ");
        SDES.printData( m, 8);
        //Como hacer una clave aleatorea?, como no quiero meterle mano al codigo
        //por que seguramente lo termine rompiendo, entonces con un numero aleatoreo :v
        System.out.println("\n\nResultado con clave aleatorea");
        Random rand = new Random();
        K = rand.nextInt(255); 
        A = new SDES( K);
        //este es el texto claro, que es un numero de maximo 2^8
        m = 205;
        System.out.print("\nKey K1: ");
        SDES.printData( A.K1, 8);
        System.out.print("\nKey K2: ");
        SDES.printData( A.K2, 8);
        m = A.encrypt( m);
        System.out.print("\nEncrypted Message: ");
        SDES.printData( m, 8);
        m = A.decrypt( m);
        System.out.print("\nDecrypted Message: ");
        SDES.printData( m, 8);
    }
       
}