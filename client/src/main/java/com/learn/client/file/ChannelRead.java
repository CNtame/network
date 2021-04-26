package com.learn.client.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author liu
 * @version 1.0
 * @description 通道加缓存
 * FileChannel：从文件中读写数据
 * DatagramChannel:能通过UDP读写网络中的数据。
 * SocketChannel:能通过TCP读写网络中的数据。
 * ServerSocketChannel：
 *
 * ByteBuffer ShortBuffer IntBuffer LongBuffer
 * FloatBuffer DoubleBuffer CharBuffer MappedByteBuffer
 * @createDate 2021/4/26
 */
public class ChannelRead {


    public static void main(String[] args) throws IOException {
        fileByteBufferPractice();
        charBufferPractice();
    }

    /**
     * byteBuffer FileChannel
     *
     */
    private static void fileByteBufferPractice() throws IOException {
        File file = new File("/Users/liuhaibo/Documents/testData/String.txt");
        if (!file.exists()) {
            System.out.println("没有此文件");
            return;
        }
        FileChannel fileChannel = null;
        try {
            fileChannel = new FileInputStream(file).getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            StringBuilder res = new StringBuilder();
            while ( fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                //数组
                byte[] data = new byte[byteBuffer.remaining()];
                byteBuffer.get(data);
                System.out.println(new String(data));
                byteBuffer.clear();
            }
            System.out.println(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert fileChannel != null;
            fileChannel.close();

        }
    }

    private static void charBufferPractice() throws IOException {
        File file = new File("/Users/liuhaibo/Documents/testData/String.txt");
        if (!file.exists()) {
            System.out.println("没有此文件");
            return;
        }
        FileChannel fileChannel = null;

        try {
            fileChannel = new FileInputStream(file).getChannel();
            CharBuffer cBuffer = CharBuffer.allocate(1024);
            ByteBuffer bBuffer = ByteBuffer.allocate(1024);
            //处理中文乱码
            Charset gbk = StandardCharsets.UTF_8;
            CharsetDecoder dbkDecoder = gbk.newDecoder();
            StringBuilder res = new StringBuilder();
            while ( fileChannel.read(bBuffer) != -1) {
                bBuffer.flip();
                dbkDecoder.decode(bBuffer, cBuffer,false);
                bBuffer.clear();
                res.append(new String(cBuffer.array(), 0, cBuffer.position()));
                /*
                compact()方法将所有未读的数据拷贝到Buffer起始处。
                然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，
                设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据。
                 */
                //cBuffer.compact();
                cBuffer.clear();
            }
            System.out.println(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert fileChannel != null;
            fileChannel.close();
        }
    }
}
