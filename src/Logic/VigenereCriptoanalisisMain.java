/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class VigenereCriptoanalisisMain {
    public static void main(String[] args) {
        String s = "CHREEVOAHMAERATBIAXXWTNXBEEOPHBSBQMQEQERBWRVXUO"
                + "AKXAOSXXWEAHBWGJMMQMNKGRFVGXWTRZXWIAKLXFPSKAUTEMND"
                + "CMGBKVILCHRLNWTVRZBENWHWWNWHEEEABCHUSNWDFEGMGHRLYUGEP"
                + "JYSSKBIKQHWTUWMQEIMGNYNVWBWRVXKUFCTPAVRTBTUIPNLYSKPAAMSN"
                + "DSEKVEEWMAENXXMTUIBAVVRXBAAHMQEZSKNLNGDJDNMLRCNPGNITLUX"
                + "RFLNARVIWCOQSMQECVNWIAKMQELWAXUYHAJVRHHWEVRGXVRQUNR";
        VigenereCriptoanalisis vca = new VigenereCriptoanalisis();
        System.out.println("indices de coincidencia para algunos tamaños: \\n");
        for (int i=1;i<7;++i){
            ArrayList<Double> v = vca.indiceCoincidenciaTamanoClave(s, i);
            double x=0;
            System.out.print(i+" , ");
            for (Double c : v){
                x+=c;
            }
            System.out.print((x/i)+" : ");
            for (Double c: v){
                System.out.print(c+ " ");
            }
            System.out.println("");
        }
        System.out.println("Indices de coincidencia asumiendo determinada letra:(tamano fijo 5) \n");
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int x =5;
        ArrayList<ArrayList<Double> > m = vca.indiceEsperadoTamanoClave(s, x);
        String clave = "";
        for (int i=0;i<m.size();++i){
            System.out.print(i+" : ");
            for (int j=0;j<m.get(i).size() ;++j){
                System.out.print(abc.charAt(j)+" "+m.get(i).get(j));
            }
            System.out.println("");
            int idx = getIndexOfLargest(m.get(i));
            clave+=Character.toString(abc.charAt(idx));
        }
        System.out.println("Al finar tenemos que la solucion es ");
        System.out.println(vca.decifrar(s, clave));
        
    }
    
    public static int getIndexOfLargest( ArrayList<Double> array )
    {
      if ( array == null || array.size() == 0 ) return -1; // null or empty

      int largest = 0;
      for ( int i = 1; i < array.size(); i++ )
      {
          if ( array.get(i) > array.get(largest)) largest = i;
      }
      return largest; // position of the first largest found
    }
}
