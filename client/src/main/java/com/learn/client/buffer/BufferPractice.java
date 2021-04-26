package com.learn.client.buffer;

import java.nio.ByteBuffer;

/**
 * @author liu
 * @version 1.0
 * @description
 * @createDate 2021/4/26
 */
public class BufferPractice {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        System.out.println("容量：" + byteBuffer.capacity() + "，position：" + byteBuffer.position() +
                ",limit:" + byteBuffer.limit());

        //clear  把position设为0，删除mark。limit设置为capacity， 一般在把数据写入Buffer前调用。
        byteBuffer.clear();
        for (int i = 0; i < byteBuffer.limit(); i++) {
            byteBuffer.put((byte) i);
        }
        //写完数据，需要开始读的时候，将position复位到0，并将limit设为当前position。
        byteBuffer.flip();
        for (int i = 0; i < byteBuffer.limit(); i++) {
            byteBuffer.mark();
            System.out.print(byteBuffer.get() + " ");
        }
        System.out.println();
        System.out.println("position:" + byteBuffer.position());

        //到remark位置
        byteBuffer.reset();
        System.out.println("reset()后position:" + byteBuffer.position());
        while (byteBuffer.hasRemaining()) {
            System.out.print(byteBuffer.get() + " ");
        }
        System.out.println();

        //rewind  把position设为0，limit不变，一般在把数据重写入Buffer前调用。
        byteBuffer.rewind();
        System.out.println("rewind重新写入数据");
        while (byteBuffer.hasRemaining()) {
            byteBuffer.put((byte) 1);
        }
        //写完数据，需要开始读的时候，将limit设为当前position；将position复位到0。读取已经写入的数据
        byteBuffer.flip();
        System.out.println("读取数据");
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.print(byteBuffer.get() + " ");
        }
        System.out.println();
        System.out.println("position:" + byteBuffer.position());



    }
}
