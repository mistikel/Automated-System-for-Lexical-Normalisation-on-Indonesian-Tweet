/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Leksikon;
import View.Mform;
import java.util.ArrayList;
import java.util.HashMap;



/**
 *
 * @author Mistiawan
 */
public class Mcontrol {

   private static HashMap<String, String> norm = new HashMap<>();
   
   private ArrayList<String> editex = new ArrayList<String>();

    
   private  ArrayList<String> prep = new ArrayList<String>();
   private  ArrayList<String> anword = new ArrayList<String>();
    public Mcontrol() {
        
    }

   public String normK(String text)
   {
       Genprep prep = new Genprep();
       this.prep= prep.getPrep(text);
       Anlword aword = new Anlword();
       aword.analysis(this.prep);
       this.anword = aword.getOOV();     
       return topEdit(anword);
   }
   
   public  ArrayList<String> getEditex() 
   {
        return editex;
   }
   

    public ArrayList<String> getAnword() {
        return anword;
    }
    
     
   
   public  ArrayList<String> getPrep(String text)
   { 
       Genprep preproces = new Genprep();
       
       return preproces.getPrep(text);
   }
   
    public String topEdit(ArrayList<String> an_oov) {
        ArrayList<String> normal = this.prep;
        int distance;
        int cons = 100;
        int top_distance = 100;
        
        for(String ov : an_oov)
        {
             
            for(String word : Leksikon.getLeksikon())
            {
                distance = Editex.editex(ov, word);
                   if(ov.substring(0,1).equals(word.substring(0,1)) &&
                           word.length()>ov.length())   
                   {
                       if(distance <= top_distance ){//&& ov.equals(RankWord(ov, word))){
                           if(ov.substring(ov.length()-1).equals(word.substring(word.length()-1)))
                               norm.put(ov, word);
                           //cons = countCons(word);                           
                           top_distance = distance;
                           editex.add(word);
                       }
                   }
                   
            }
            top_distance =100;
            //cons = 100;
            
        }
        for(String ov : an_oov)
       {
           int idx;
           idx = normal.indexOf(ov);
           if(norm.get(ov)!=null)
               normal.set(idx, norm.get(ov));
           else
               normal.set(idx,ov);
       }
        
       StringBuilder tweet = new StringBuilder();
       for(String tw : normal){
           tweet.append(tw);
           tweet.append(" ");
       }
      
      return tweet.toString();
    }

    public static String RankWord(String a, String b) {
    int[][] lengths = new int[a.length()+1][b.length()+1];
 
    // row 0 and column 0 are initialized to 0 already
 
    for (int i = 0; i < a.length(); i++)
        for (int j = 0; j < b.length(); j++)
            if (a.charAt(i) == b.charAt(j))
                lengths[i+1][j+1] = lengths[i][j] + 1;
            else
                lengths[i+1][j+1] =
                    Math.max(lengths[i+1][j], lengths[i][j+1]);
 
    // read the substring out from the matrix
    StringBuffer sb = new StringBuffer();
    for (int x = a.length(), y = b.length();
         x != 0 && y != 0; ) {
        if (lengths[x][y] == lengths[x-1][y])
            x--;
        else if (lengths[x][y] == lengths[x][y-1])
            y--;
        else {
            assert a.charAt(x-1) == b.charAt(y-1);
            sb.append(a.charAt(x-1));
            x--;
            y--;
        }
    }
 
    return sb.reverse().toString();
}

    private static int countCons(String t) {
    String vowels = "aeiou";
    int vowelCount = 0;
    int consCount = 0;
    int i;

    int length = t.length();
    for(i = 0; i < length; i ++)
    {
        char currentChar = t.charAt(i);
        
        
            if (vowels.indexOf(currentChar)>=0)
            {
                vowelCount++;
            }
            else if(Character.isLetter(currentChar))
            {
                consCount++;
            }
        
                   
    }
    return consCount;
    }
   
}
