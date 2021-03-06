package com.learn.serve.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

/**
 * @author liu
 * @version 1.0
 * @description
 * @createDate 2021/4/11
 */
public class NettyDiscardServer {


    org.slf4j.Logger logger = LoggerFactory.getLogger(NettyDiscardServer.class);

    private final int serverPort;
    ServerBootstrap b = new ServerBootstrap();
    public NettyDiscardServer(int port) {
        this.serverPort = port;
    }

    public void runServer() {
        //创建反应器线程组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        try {
            //1 设置反应器线程组
            b.group(bossLoopGroup, workerLoopGroup);
            //2 设置nio类型的通道
            b.channel(NioServerSocketChannel.class);
            //3 设置监听端口
            b.localAddress(serverPort);
            //4 设置通道的参数
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            //5 装配子通道流水线
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                //有连接到达时会创建一个通道”
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                    // 流水线管理子通道中的Handler处理器
                    // 向子通道流水线添加一个handler处理器
                    ch.pipeline().addLast(new NettyDiscardHandler());
                }
            });
            // 6 开始绑定服务器
            // 通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = b.bind().sync();
            logger.info(" 服务器启动成功，监听端口: " +
                    channelFuture.channel().localAddress());
            // 7 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //int port = NettyDemoConfig.SOCKET_SERVER_PORT;
        int port = 18899;
        new NettyDiscardServer(port).runServer();
    }
}


