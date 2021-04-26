package com.learn.client.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author liu
 * @version 1.0
 * @description
 * @createDate 2021/4/26
 */
public class ByteStreamRead {


    public static void main(String[] args) throws IOException {
        readOneByOne();
        readArray();
    }

    /**
     * 一个个读 ，读不了汉语
     * @throws IOException
     */
    private static void readOneByOne() throws IOException {
        File file = new File("/Users/liuhaibo/Documents/testData/String.txt");
        if (!file.exists()) {
            return;
        }
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(file);
            int l=0;
            StringBuilder res = new StringBuilder();
            while ((l = stream.read())!=-1) {
                res.append((char) l);
            }
            System.out.println(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert stream != null;
            stream.close();
        }
    }

    /**
     * 使用数组接收，本质上也是一个个接收
     */
    private static void readArray() throws IOException {
        File file = new File("/Users/liuhaibo/Documents/testData/String.txt");
        if (!file.exists()) {
            return;
        }
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(file);
            byte[] data = new byte[1024];
            int l=0;
            StringBuilder res = new StringBuilder();
            while ((l = stream.read(data))!=-1) {
                res.append(new String(data, 0, l));
            }
            System.out.println(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert stream != null;
            stream.close();
        }
    }
}
