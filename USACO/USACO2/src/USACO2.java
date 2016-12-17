/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;


public class USACO2 {
    
    int min;
    int med;
    int max;
    int medmult;
    
    private void readStuffFromDisk(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String[] array = br.readLine().split(" ");            
            min = Integer.parseInt(array[0]);
            med = Integer.parseInt(array[1]);
            max = Integer.parseInt(array[2]);
            medmult = (int) (max - (max % med)) / med;
        } catch (IOException ex) {
            Logger.getLogger(USACO2.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void saveStuffToDisk(String fileName, int n){
        String s = Integer.toString(n);
        BufferedWriter outputFile;        
        try{
            File file = new File(fileName);
            outputFile = new BufferedWriter(new FileWriter(file));            
            outputFile.write(s);
            outputFile.close();
        } catch(IOException e){
            System.out.println("Cannot write Stuff" + e);
        }
    }
    
    int solution(){
        int[] array = new int[medmult + 2];
        for (int i = 0; i <= medmult; i++){
            int p = i * med;
            int k = max - p;
            array[i] = (k - (k % min)) + p;
        }
        int maxVal = array[0];
        for(int c: array) if (c > maxVal) maxVal = c;
        return maxVal;
    }
    
    
    
    public static void main(String[] args) {
        USACO2 c = new USACO2();
        c.readStuffFromDisk("pails.in");
        c.saveStuffToDisk("pails.out", c.solution());
    }
    
}
