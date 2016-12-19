/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Mistiawan
 */
public class Genprep {
    
    private Pattern pattern;
    private Matcher matcher;
    

    public Genprep() {
    }
    
    public ArrayList<String> getPrep(String raw)
    {
        ArrayList<String> al = new ArrayList<String>();
            StringTokenizer st = tokenize(normalize(raw));
        while(st.hasMoreTokens()){
            al.add(st.nextToken());
        }
        return al;
    }
   
    public String normalize(String raw){
        String W = new String();
        W = format(raw);
        W = removeRT(removeHashtags(removeMention(removeUrl(W))));
        String[] words;
        words = W.split(" ");
        ArrayList<String> nuword = new ArrayList<String>();
        for(String w: words)
        {
            w = removerepetition(w);
            w = convsymboltoletter(w);
            w = convnumbertoletter(w);
            if (!w.isEmpty()) { // empty string ignored
                nuword.add(w);
            }
        }
        W = StringUtils.join(nuword, " ");
        return W;
    }
    
    public StringTokenizer tokenize(String raw)
    {
        raw = raw.toLowerCase();
        StringTokenizer wt = new StringTokenizer(raw, "[ \t\n\r\f.,]");
        return wt;
    }
    
    private String removeUrl(String raw)
    {
        String urlPattern = "([\\S]+://[\\S]+)";
        pattern = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(raw);
        int i = 0;
        while (matcher.find()) {
            raw = raw.replaceAll(matcher.group(i),"").trim();
            i++;
        }
        return raw;
    }
    
    private String removeRT(String raw)
    {
        raw = " "+ raw + " ";
        String urlPattern = "([\\s]RT[\\s])";
        pattern = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(raw);
        int i = 0;
        while (matcher.find()) {
            raw = raw.replaceAll(matcher.group(i),"").trim();
            i++;
        }
        return raw;
    }
    
    private String removeMention(String raw)
    {
        String urlPattern = "(@\\w+)";
        pattern = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(raw);
        int i = 0;
        while (matcher.find()) {
            raw = raw.replaceAll(matcher.group(i),"").trim();
            i++;
        }
        return raw;
    }
    
    private String removeHashtags(String raw)
    {
        String urlPattern = "(#\\w+)";
        pattern = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(raw);
        int i = 0;
        while (matcher.find()) {
            raw = raw.replaceAll(matcher.group(i),"").trim();
            i++;
        }
        return raw;
    }
    
    private String format(String raw) {
        // * Format punctuation: put white spaces to separate it from word
        String sent = new String();
        sent = raw;
        sent = sent.replace("‘", "");
        sent = sent.replace("’", "");
        sent = sent.replace("“", "");
        sent = sent.replace("”", "");
        sent = sent.replace("`", "");
        sent = sent.replace("[", "");
        sent = sent.replace("! ", "");
        sent = sent.replace("\"", "");
        sent = sent.replace("'", "");
        sent = sent.replace("(", "");
        sent = sent.replace(")", "");
        sent = sent.replace("*", "");
        sent = sent.replace("\\", "");
        sent = sent.replace("+", "");
        sent = sent.replace(",", "");
        sent = sent.replace(".", "");
        sent = sent.replace("/", "");
        sent = sent.replace(":", "");
        sent = sent.replace(";", "");
        sent = sent.replace("<", "");
        sent = sent.replace("=", "");
        sent = sent.replace(">", "");
        sent = sent.replace("?", "");
        sent = sent.replace("\\^", "");
        sent = sent.replace("~", "");
        sent = sent.replace("\\-", "");
        sent = sent.replace("`", "");
        sent = sent.replace("{", "");
        sent = sent.replace("|", "");
        sent = sent.replace("}", "");
        sent = sent.replace("…", "");
        sent = sent.replace("]", "");
        

        pattern = Pattern.compile("\\s+");
        matcher = pattern.matcher(sent);
        sent = matcher.replaceAll(" ");
        return sent;
    }

    private String removerepetition(String w) {
        String word = w;
        // repetition 1 char (3x or more repetition)
        word = word.replaceAll("([a-z])\\1{2,}", "$1");
        // repetition 2 char (3x or more repitition)
        word = word.replaceAll("([a-z][a-z])\\1{2,}", "$1");
        return word;
    }

    private String convsymboltoletter(String w) {
        String word = w;
        pattern = Pattern.compile("[a-z0-9]+[\\@\\!\\$]+[\\@\\!\\$0-9a-z]*");
        matcher = pattern.matcher(w);
        if (matcher.lookingAt()) {
        word = word.replaceAll("\\@", "a");
        word = word.replaceAll("\\!", "i");
        word = word.replaceAll("\\$", "s");
        }
        pattern = Pattern.compile("[\\@\\!\\$]+[a-z0-9]+[\\@\\!\\$0-9a-z]*");
        matcher = pattern.matcher(w);
        if (matcher.lookingAt()) {
        word = word.replaceAll("\\@", "a");
        word = word.replaceAll("\\!", "i");
        word = word.replaceAll("\\$", "s");
        }
        return word;
    }

    private String convnumbertoletter(String w) {
        String word = w;
        pattern = Pattern.compile("[a-z]+[0-9]+[0-9a-z]*");
        matcher = pattern.matcher(w);
        if (matcher.lookingAt()) {
        word = word.replaceAll("12", "r");
        word = word.replaceAll("13", "b");
        word = word.replaceAll("0", "o");
        word = word.replaceAll("1", "i");
        word = word.replaceAll("2", "");
        word = word.replaceAll("3", "e");
        word = word.replaceAll("4", "a");
        word = word.replaceAll("5", "s");
        word = word.replaceAll("6", "g");
        word = word.replaceAll("7", "j");
        word = word.replaceAll("8", "b");
        word = word.replaceAll("9", "g");
        } else {
        pattern = Pattern.compile("[0-9]+[a-z]+[0-9a-z]*");
        matcher = pattern.matcher(w);
        }
        if (matcher.lookingAt()) {
        word = word.replaceAll("12", "r");
        word = word.replaceAll("13", "b");
        word = word.replaceAll("0", "o");
        word = word.replaceAll("1", "i");
        word = word.replaceAll("2", "r");
        word = word.replaceAll("3", "e");
        word = word.replaceAll("4", "a");
        word = word.replaceAll("5", "s");
        word = word.replaceAll("6", "g");
        word = word.replaceAll("7", "j");
        word = word.replaceAll("8", "b");
        word = word.replaceAll("9", "g");
        } else {
        
        }
        return word;
    }
    
    
}
