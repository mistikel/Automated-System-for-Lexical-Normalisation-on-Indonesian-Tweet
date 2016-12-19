/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Leksikon;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Mistiawan
 */
public class Anlword {
    private ArrayList<String> word;
    
    private  ArrayList<String> OOV;
    private  ArrayList<String> IV;

    public Anlword() {
    }

    public  ArrayList<String> getIV() {
        return IV;
    }

    public  void setIV(ArrayList<String> IV) {
        this.IV = IV;
    }

    public  ArrayList<String> getOOV() {
        return OOV;
    }

    public  void setOOV(ArrayList<String> OOV) {
        this.OOV = OOV;
    }

    public ArrayList<String> getWord() {
        return word;
    }

    public void setWord(ArrayList<String> word) {
        this.word = word;
    }

    public  void analysis(ArrayList<String> leksikon)
    {
        ArrayList<String> rslt = new ArrayList<String>(leksikon.size());
        ArrayList<String> e = new ArrayList<String>(leksikon.size());
        ArrayList<String> word = new ArrayList<String>();
        Leksikon.connleksikon();
        word = Leksikon.getLeksikon();
        for (String t : leksikon)
        {
            if(!word.contains(t))
                rslt.add(t);
            else
                e.add(t);
        }
        setOOV(rslt);
        setIV(e);
    }
    
}
