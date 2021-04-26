package com.learn.client.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author liu
 * @version 1.0
 * @description
 * @createDate 2021/4/26
 */
public class FileChannelCopy {

    public static void main(String[] args) throws IOException {
        String filePath = "/Users/liuhaibo/Documents/testData/StringNIO.txt";
        //common(filePath);
        //nio(filePath);
        //nio2(filePath);
        String filePath2 = "/Users/liuhaibo/Documents/testData/StringNIO2.txt";
        nio4(filePath2);
    }

    /**
     * 通道复制
     * 利用通道完成文件的复制（非直接缓冲区）
     */
    private static void common(String targetPath) {
        FileInputStream input = null;
        FileOutputStream out = null;
        FileChannel iChannel = null;
        FileChannel oChannel = null;
        try {
            input = new FileInputStream("/Users/liuhaibo/Documents/testData/String.txt");
            out = new FileOutputStream(targetPath);
            iChannel = input.getChannel();
            oChannel = out.getChannel();
            //②分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            while (iChannel.read(buf) != -1) {
                buf.flip();
                oChannel.write(buf);
                buf.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(iChannel!=null){
                try {
                    iChannel.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oChannel != null) {
                try {
                    oChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("复制成功");
        }
    }

    /**
     * channel transferTo
     * 通过特殊方法transferTo()和transferFrom()，将两个通道直连
     * 通道之间的数据传输(直接缓冲区)
     * @param targetPath
     * @throws IOException
     */
    private static void nio(String targetPath) throws IOException {
        FileOutputStream out = null;
        FileInputStream in = null;
        File file = new File("/Users/liuhaibo/Documents/testData/String.txt");
        try {
            if (file.exists()) {
                file.delete();
            }
            in = new FileInputStream(file);
            out = new FileOutputStream(targetPath);
            FileChannel inChannel = in.getChannel();
            FileChannel outChannel = out.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert out != null;
            out.close();
            in.close();
            System.out.println("复制成功");

        }
    }


    /**
     *
     * @param targetPath
     */
    private static void nio2(String targetPath) {
        File file = new File(targetPath);

        try {
            if(file.exists()){
                file.delete();
            }
            Files.copy(Paths.get("/Users/liuhaibo/Documents/testData/String.txt"), file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("复制成功");
        }
    }

    /**
     * 使用直接缓冲区完成文件的复制(内存映射文件)
     */
    private static void nio4(String targetPath) throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/liuhaibo/Documents/testData/String.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(targetPath), StandardOpenOption.WRITE,
                StandardOpenOption.CREATE, StandardOpenOption.READ);

        MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        byte[] data = new byte[inBuffer.limit()];
        inBuffer.get(data);
        outBuffer.put(data);
        inChannel.close();
        outChannel.close();
    }
}
