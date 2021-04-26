package com.learn.client.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * @author liu
 * @version 1.0
 * @description
 * @createDate 2021/4/26
 */
public class ChannelWrite {

    public static void main(String[] args) throws IOException {
        byteArrayWrite("byte数组3写入{}");
        filesWrite("byte数组4写入{}");
    }

    /**
     * byteBuffer
     * @param content
     * @throws IOException
     */
    private static void byteArrayWrite(String content) throws IOException {
        File file = new File("/Users/liuhaibo/Documents/testData/String.txt");
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        FileChannel fileChannel = null;
        try {
            //不覆盖写入
            fileChannel= new FileOutputStream(file,true).getChannel();
            ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());
            fileChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert fileChannel != null;
            fileChannel.close();
        }
    }

    /**
     * files Write
     */
    private static void filesWrite(String content) {
        File file = new File("/Users/liuhaibo/Documents/testData/String.txt");
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        try {
            Files.write(file.toPath(), content.getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
