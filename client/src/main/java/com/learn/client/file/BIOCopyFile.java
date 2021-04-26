package com.learn.client.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author liu
 * @version 1.0
 * @description
 * @createDate 2021/4/26
 */
public class BIOCopyFile {

    public static void main(String[] args) throws IOException {
        String targetPath = "/Users/liuhaibo/Documents/testData/StringCopy.txt";
        copyFile(targetPath);
    }

    private static void copyFile(String targetPath) throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            //如果文件不存在 会自动新建
            inputStream = new FileInputStream("/Users/liuhaibo/Documents/testData/String.txt");
            outputStream = new FileOutputStream(targetPath);
            byte[] data = new byte[1024];
            while (inputStream.read(data) != -1) {
                outputStream.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert inputStream != null;
            inputStream.close();
            assert outputStream != null;
            outputStream.close();

        }
    }
}
