package com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix;

//import java.net.URL;
import java.util.*;
import java.net.*;
import java.io.*;

public class Urllib {
    public static String urlopen(String addr) {
        try {
            URL url = new URL(addr);
            String inline = new String();
            Scanner sc = new Scanner(url.openStream());
            while(sc.hasNext())
            {
                inline+=sc.nextLine();
            }
//            System.out.println(inline);
            sc.close();
            return inline;
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
