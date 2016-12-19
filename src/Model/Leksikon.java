/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mistiawan
 */
public class Leksikon {
    private String word;
    private static ArrayList<String> leksikon;
    
    private final static String path = "C:\\Users\\misti\\OneDrive\\SEMESTER\\Semester 8\\TA II\\NKC\\data\\gkamus.txt";
            //"C:\\Users\\misti\\OneDrive\\SEMESTER\\Semester 8\\TA II\\Code\\NKC\\data\\kamus.txt";

    public Leksikon(){}
    
    public Leksikon(Leksikon leksikon)
    {
        this.setWord(leksikon.getWord());
    }
    
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public static ArrayList<String> getLeksikon() {
        return leksikon;
    }

    public static void setLeksikon(ArrayList<String> leksikon) {
        Leksikon.leksikon = leksikon;
    }
    
    public static void connleksikon()
    {
        String path = new String();
        path = Leksikon.path;
        
        ArrayList<String> rslt = new ArrayList<String>();
        Leksikon ls ;
        String[] tmp = new String[2];
        FileInputStream fin = null;
        Scanner sc = null;
        try{
            fin = new FileInputStream(path);
            sc = new Scanner(fin, "UTF-8");
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                line = line.toLowerCase();
                tmp = line.split("\\s+");
                ls = new Leksikon();
                ls.setWord(tmp[0]);
                rslt.add(ls.getWord());
            }
            setLeksikon(rslt);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        finally{
            try {
                fin.close();
                sc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
   
       
}
