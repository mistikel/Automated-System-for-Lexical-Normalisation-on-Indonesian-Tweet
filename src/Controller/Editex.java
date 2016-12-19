/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Leksikon;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Mistiawan
 */
public class Editex {
    
    private static final HashMap<Character,Integer> gWords = new HashMap<Character, Integer>();
    public static int editex(String string1, String string2){
        gWords.put('a',1);gWords.put('e',1);gWords.put('i',1);gWords.put('o',1);gWords.put('u',1);gWords.put('y',1);
        gWords.put('b',2);gWords.put('p',2);
        gWords.put('c',3);gWords.put('g',3);gWords.put('j',3);gWords.put('k',3);gWords.put('q',3);
        gWords.put('d',4);gWords.put('t',4);
        gWords.put('l',5);
        gWords.put('m',6);gWords.put('n',6);
        gWords.put('r',7);
        gWords.put('f',8);gWords.put('v',8);
        gWords.put('s',9);gWords.put('x',9);gWords.put('z',9);
        char[] str1 = (string1).toCharArray();
        char[] str2 = (string2).toCharArray();
        int length1, length2;
        length1 = str1.length;
        length2 = str2.length;

        int F[][] = new int[length1][length2];
        int dStr1[] = new int[length1-1];
        int dStr2[] = new int[length2-1];

        F[0][0] = 0;
        //System.out.println(str1);
        for(int i = 1; i < length1; i++) {
            dStr1[i-1] = D(str1[i-1],str1[i]);
            F[i][0] = F[i-1][0] + dStr1[i-1];
        }
        for(int i = 1; i < length2; i++) {
            dStr2[i-1] = D(str2[i-1],str2[i]);
            F[0][i] = F[0][i-1] + dStr2[i-1];
        }
//        for(int i=0; i<length1-1; i++){
//            System.out.print("dStr1["+i+"]="+dStr1[i]+ " ");
//            
//        }
//        System.out.println("");
//        for(int i=0; i<length1; i++){
//            System.out.print("F["+i+"][0]="+F[i][0]+ " ");
//        }
//        System.out.println("");
//         for(int i=0; i<length2-1; i++){
//            System.out.print("dStr2["+i+"]="+dStr2[i]+ " ");
//            
//        }
//         System.out.println("");
//        for(int i=0; i<length2; i++){
//            System.out.print("F[0]["+i+"]="+F[0][i]+ " ");
//        }
//        System.out.println("");
//        System.out.println("");
//        System.out.println("");

        for(int i = 1; i < length1; i++) {
            for(int j = 1; j < length2; j++) {
                F[i][j] = min(F[i - 1][j] + dStr1[i - 1],
                        F[i][j - 1] + dStr2[j - 1],
                        F[i - 1][j - 1] + R(str1[i], str2[j]));
                //System.out.print("F["+i+"]["+j+"]="+F[i][j]+ " ");
            }
            //System.out.println("");
        }
        
//        for(int i = 0; i < length1; i++) {
//            for(int j = 0; j < length2; j++) {
//               
//                System.out.print("F["+i+"]["+j+"]="+F[i][j]+ " ");
//            }
//            System.out.println("");
//        }
        
        return  F[length1-1][length2-1];
    }
    
    private static int D(char c, char c0)
    {
//        if( (c == 'h' || c0 == 'w') && c != c0)
//        {
//            return 1;
//        }
//        else 
//        {
            return R(c, c0);
        //}
    }

    private static int R(char c, char c0) {
        int result = 2;
        if(c == c0) {
            result = 0;
        }
        else if(isSameGroup(c,c0)==true){
            result = 1;
        }
        return result;
    }

    private static boolean isSameGroup(char c, char c0) {
        if(gWords.get(c)==gWords.get(c0)){
            return true;
           }
        else
            return false;
    }

    private static int min(int i, int i0, int i1) {
        if( i > i0 )
            return (( i1 > i0) ? i0 : i1);
        else
            return (( i1 > i) ? i : i1);
    }
    
    
}
