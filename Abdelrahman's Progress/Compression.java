/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.io.*;
import java.util.zip.*;


/**
 *
 * @author moham
 */
public class Compression {

    public static void compress(File source , File destination) throws IOException{
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination);
        GZIPOutputStream gzos = new GZIPOutputStream(fos);
        int read;
        while((read = fis.read(buffer)) != -1){
            gzos.write(buffer, 0, read);
        }
        gzos.finish();
        gzos.close();
        fos.close();
        fis.close();
        
    }
    public static void decompress(File source , File destination) throws IOException{
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination);
        GZIPInputStream gzis = new GZIPInputStream(fis);
        int read;
        while((read = gzis.read(buffer)) != -1){
            fos.write(buffer, 0, read);
        }
        gzis.close();
        fos.close();
        fis.close();
    }
    public static void main(String[] args){
        File source = new File("D:\\Fall 2022\\Data Structure\\Project\\Test.txt");
        File destination = new File("D:\\Fall 2022\\Data Structure\\Project\\Test.txt");
        try{
            decompress(source, destination);
        }
        catch(IOException e){
            System.out.print(e);
        }
    }
    
}
