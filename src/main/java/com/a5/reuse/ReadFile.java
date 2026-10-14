package com.a5.reuse;
import java.io.*;
import java.util.ArrayList;
public class ReadFile {
 public ArrayList<String> readFile(String path){
 ArrayList<String> list = new ArrayList<String>();   
    try {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;
            while ((line = br.readLine())!= null) {
                list.add(line);
            }
            br.close();
    }catch (IOException e){
        throw new RuntimeException(e);
    }
    return list;
 }
}
