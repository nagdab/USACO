

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;
        
/**
 *
 * @author Bhavik Nagda
 */
public class USACO {
    
    int N;
    int B;
    int array[][];
    int XHalf;
    int YHalf;
    int minimum_values[];
    
    private void readStuffFromDisk(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String[] d1 = br.readLine().split(" ");
            N = Integer.parseInt(d1[0]);
            B = Integer.parseInt(d1[1]);
            array = new int[B + 1][B + 1];
            minimum_values = new int[B * B];
            while ((line = br.readLine()) != null) {
                String[] data_array = line.split(" ");
                int x = Integer.parseInt(data_array[0]);
                int y = Integer.parseInt(data_array[1]);
                array[x][y] = 1;  
            }
        } catch (IOException ex) {
            Logger.getLogger(USACO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
        
    int CowsNumber(int x, int y){
        int bottomleft = 0;
        for(int i = 0; i < x + 1; i++){
            for (int j = 0; j < y + 1; j++){
                if (array[i][j] == 1) bottomleft += 1;
            }
        }
        int topleft = 0;
        for(int i = 0; i < x + 1; i++){
            for (int j = y + 1; j < B + 1; j++){
                if (array[i][j] == 1) topleft += 1;
            }
        }
        int bottomright = 0;
        for(int i = x + 1; i < B + 1; i++){
            for (int j = 0; j < y + 1; j++){
                if (array[i][j] == 1) bottomright += 1;
            }
        }
        int topright = 0;
        for(int i = x + 1; i < B + 1; i++){
            for (int j = y + 1; j < B + 1; j++){
                if (array[i][j] == 1) topright += 1;
            }
        }
        return Math.max(bottomright, Math.max(topright, Math.max(topleft, bottomleft)));
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
    
    int minNumber(){
        int counter = 0;
        for(int i = 0; i < B; i++){
            for(int j = 0; j < B; j++){
                minimum_values[counter] = CowsNumber(i, j);
                counter++;
            }
        }
        int minValue = minimum_values[0];
        for(int i = 0; i < B * B; i++){
            if(minimum_values[i] < minValue) minValue = minimum_values[i];
        }
        return minValue;
    }
    
    public static void main(String[] args) {
        USACO c = new USACO();
        c.readStuffFromDisk("balancing.in");
        int return_value = c.minNumber();
        c.saveStuffToDisk("balancing.out", c.minNumber());
    }
    
}
