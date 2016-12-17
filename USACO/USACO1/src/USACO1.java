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

public class USACO1 {
    
    int N;
    int[] barn_circle;
    
    private void readStuffFromDisk(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            N = Integer.parseInt(br.readLine());
            barn_circle = new int[N];
            int counter = 0;
            while ((line = br.readLine()) != null) {
                barn_circle[counter] = Integer.parseInt(line);
                counter++;
            }
        } catch (IOException ex) {
            Logger.getLogger(USACO1.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    int doorCounter(int n){
        int sum = 0;
        for(int i = 0; i < N; i++){
            sum += barn_circle[cowReturnValue(i + n)] * i;
        }
        return sum;
    }
    
    int cowReturnValue(int n){
        if(n < 0) return N + n;
        else if(n >= N) return n % N;
        else return n;
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
    
    int minValue(){
        int[] valueArray = new int[N];
        for(int i = 0; i < N; i++){
            valueArray[i] = doorCounter(i);
        }
        int minValue = valueArray[0];
        for(int i = 0; i < N; i++){
            if(valueArray[i] < minValue) minValue = valueArray[i];
        }
        return minValue;
    }
            
            
            
    public static void main(String[] args) {
        USACO1 c = new USACO1();
        c.readStuffFromDisk("cbarn.in");
        c.saveStuffToDisk("cbarn.out", c.minValue());
    }
    
}
