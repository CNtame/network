package com.learn.client.file;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author liu
 * @version 1.0
 * @description
 * @createDate 2021/4/26
 */
public class CharStreamRead {

    public static void main(String[] args) throws IOException {
        readOneByOne();
        readArray();

        bufferReader();
    }

    /**
     * 一个个读取
     */
    public static void readOneByOne() throws IOException {
        File file = new File("/Users/liuhaibo/Documents/testData/String.txt");
        if(!file.exists()){
            return;
        }
        InputStreamReader reader = null;
        StringBuilder res = new StringBuilder();
        try {
            reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            int l=0;
            while ((l = reader.read())!= -1) {
                res.append((char) l);
            }
            System.out.println(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert reader != null;
            reader.close();
        }
    }

    /**
     * char数组读取
     */
    public static void readArray() throws IOException {
        File file = new File("/Users/liuhaibo/Documents/testData/String.txt");
        if(!file.exists()){
            return;
        }
        InputStreamReader reader = null;
        StringBuilder res = new StringBuilder();
        try {
            reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            char[] data = new char[1024];
            int l=0;
            while ((l = reader.read(data))!= -1) {
                res.append(data, 0, l);
            }
            System.out.println(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert reader != null;
            reader.close();
        }
    }

    /**
     * 字符缓冲流
     */
    public static void bufferReader() throws IOException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader("/Users/liuhaibo/Documents/testData/String.txt");
            bufferedReader = new BufferedReader(fileReader);
            char[] data = new char[1024];
            int l=0;
            StringBuilder res = new StringBuilder();
            while ((l = bufferedReader.read(data)) != -1) {
                res.append(data, 0, l);
            }
            System.out.println(res.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert bufferedReader != null;
            bufferedReader.close();
            fileReader.close();
        }
    }
}
