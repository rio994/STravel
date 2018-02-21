package com.example.darko.stravel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Darko on 20.1.2018..
 */

public class Phrase {

    String original;
    String translated;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }

    public Phrase() {

    }

    public Phrase(String original, String translated) {
        this.original = original;
        this.translated = translated;
    }

    public static void ReadPhrasesToVector(String file,Vector<Phrase> vec)
    {
        BufferedReader phraseBufferReader;
        try {
            phraseBufferReader = new BufferedReader(new FileReader(file));
            String line;
            String check = null;
            try {
                while ((line = phraseBufferReader.readLine()) != null)
                {
                    Scanner phraseScanner = new Scanner(line);
                    String o = null;
                    String t = null;

                    //adding to todo list - use stringbuilder
                    while(true){
                        check = phraseScanner.next();
                        if(check.equals('-')){
                            break;
                        }

                        if(o == null){
                            o = check;
                        }
                        else {
                            o = o + " " + check;
                        }
                    }
                    while(phraseScanner.hasNext()){
                        if(t == null){
                            t = phraseScanner.next();
                        }
                        else {
                            t = t + " " + phraseScanner.next();
                        }
                    }

                    vec.add(new Phrase(o,t));
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


    }
}
