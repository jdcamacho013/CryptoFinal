/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 *
 * @author gabri
 */
public class VigenereCriptoanalisis
{
    private Integer TAM = 26;
    private final String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private TreeMap<Character, Integer> abc_inv = new TreeMap<>();

    public VigenereCriptoanalisis() {
        for (int i=0;i<abc.length(); ++i){
            abc_inv.put( abc.charAt(i) , i);
        }
    }
    
    
    
    public ArrayList<Integer> getFrecuencia(String s)
    {
            Integer [] ans= new Integer[TAM];
            for (Character c : s.toCharArray())
            {
                    ans[abc_inv.get(c)]++;
            }
            return new ArrayList<Integer>(Arrays.asList(ans));
    }

    public double indiceCoincidenciaFrecuencias(ArrayList<Integer> frecuencias)
    {
            double ans = 0.0;
            int t = 0;
            for (int i : frecuencias)
            {
                    t += i;
                    ans += i * (i - 1);
            }
            return ans / (t * (t - 1));
    }

    public  double indiceCoincidencia(String s)
    {
            return indiceCoincidenciaFrecuencias(getFrecuencia(s));
    }

    public  ArrayList<Double> indiceCoincidenciaTamanoClave(String s, int n)
    {
            Double[] ans = new Double[n];
            Integer[][] freq = new Integer [n][TAM];
            for (int i=0;i<n;++i){
                for (int j=0;j<TAM;++j){
                    freq[i][j]=0;
                }
            }
            for (int i = 0;i < s.length(); ++i)
            {
                    freq[(i % n)][abc_inv.get(s.charAt(i))]++;
            }
            for (int i = 0;i < n;++i)
            {
                ArrayList <Integer> foo = new ArrayList<Integer>(Arrays.asList(freq[i]));
                ans[i]= indiceCoincidenciaFrecuencias( foo );
            }
            return (new ArrayList<Double>(Arrays.asList(ans)));
    }

    public  ArrayList<ArrayList<Double>> indiceEsperadoTamanoClave(String s, int n)
    {
            ArrayList<ArrayList<Double> > ans = new ArrayList<>();
            Integer[][] freq = new Integer[n][TAM];
            for (int i=0;i<n;++i){
                for (int j=0;j<TAM;++j){
                    freq[i][j]=0;
                }
            }
            for (int i = 0;i < s.length(); ++i)
            {
                    freq[(i % n)][abc_inv.get(s.charAt(i))]++;
            }
            ArrayList<Double> prob = new ArrayList<Double>(Arrays.asList(8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966, 0.153, 0.772, 4.025, 2.406, 6.749, 7.507, 1.929, 0.095, 5.987, 6.327, 9.056, 2.758, 0.978, 2.360, 0.150, 1.974, 0.074));
            for (int i = 0;i < n;++i)
            {       
                    ArrayList<Double> foo= new ArrayList<>();
                    for (int j = 0;j < TAM;j++)
                    {
                            double a = 0;
                            for (int k = 0;i + k < TAM;++k)
                            {
                                    int newInd = (((k - j) % TAM) + TAM) % TAM;
                                    a += prob.get(newInd) * freq[(i)][(k)];
                            }
                            double d = s.length()/n;
                            foo.add(a/d);
                    }
                    ans.add(foo);
            }
            return (ans);
    }

    public String decifrar(String s, String clave)
    {
            String ans = "";
            for (int i = 0;i < s.length();++i)
            {
                    ans += abc.charAt( (abc_inv.get(s.charAt(i)) - abc_inv.get(clave.charAt(i % clave.length())) + TAM) % TAM);
            }
            return ans;
    }

    /*public final ArrayList< Double> geth0(ArrayList<Double> v)
    {
            ArrayList<Double> ans = new ArrayList<Double>(v.size());
            ArrayList<tangible.Pair<Double,Integer>> sorted = new ArrayList<tangible.Pair<Double,Integer>>();
            for (int i = 0;i < v.size();++i)
            {
                    sorted.add({v.get(i),i});
            }
            Collections.sort(sorted);
            for (int i = 0;i < v.size();i++)
            {
                    ans.set(sorted.get(i).second, Math.min(i == 0?1000:sorted.get(i).first - sorted.get(i - 1).first,i == v.size() - 1?1000:sorted.get(i + 1).first - sorted.get(i).first));
            }
            return new ArrayList<Double>(ans);
    }*/
}
