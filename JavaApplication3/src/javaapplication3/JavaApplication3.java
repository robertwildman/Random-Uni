/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author rober
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
         // Open this file.
         PrintWriter writer = new PrintWriter("the-name.txt", "UTF-8");
        BufferedReader reader = new BufferedReader(new FileReader(
                "combo.txt"));
        int i = 0;
        // Read lines from file.
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            i++;
            // Split line on comma.
            String[] parts = line.split(":");
            System.out.println(parts.length);
            if(parts.length > 3)
            {
                System.out.println(i);
                writer.println(parts[1] + ":" +parts[3]);
            }
            
            
            System.out.println();
        }
        writer.close();
        reader.close();
    }
    
}
