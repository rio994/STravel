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

public class PhoneNumber
{
    String owner;
    String number;

    PhoneNumber(String o,String n)
    {
        owner = new String(o);
        number = new String(n);
    }

    public String getOwner()
    {
        return owner;
    }

    public String getNumber()
    {
        return number;
    }

    public static void ReadPhoneNumbersToVector(String file,Vector<PhoneNumber> vec)
    {
        BufferedReader phoneBufferReader;
        try {
            phoneBufferReader = new BufferedReader(new FileReader(file));
            String line;
            try {
                while ((line = phoneBufferReader.readLine()) != null)
                {
                    Scanner phoneScanner = new Scanner(line);
                    String o = new String("");
                    String n = new String("");

                    //adding to todo list - use stringbuilder
                    while(!phoneScanner.hasNextInt() & !phoneScanner.hasNextLong())
                    {
                        o = o + " " + phoneScanner.next();
                    }
                    while(phoneScanner.hasNextInt() || phoneScanner.hasNextLong())
                    {
                        n = n + " " + phoneScanner.next();
                    }

                    vec.add(new PhoneNumber(o,n));
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
