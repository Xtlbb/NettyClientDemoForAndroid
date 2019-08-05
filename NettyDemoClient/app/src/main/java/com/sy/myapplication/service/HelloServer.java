package com.sy.myapplication.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer{ private int port;

        public HelloServer(int port)
        {
            super();
            this.port = port;
        }
        public void bind() throws InterruptedException
        {
            EventLoopGroup bossGruop=new NioEventLoopGroup();//用于服务器端接受客户端的连接
            EventLoopGroup workGroup=new NioEventLoopGroup();//用于网络事件的处理
            try
            {
                ServerBootstrap b=new ServerBootstrap();
                b.group(bossGruop, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>()
                {
                    @Override
                    protected void initChannel(SocketChannel arg0) throws Exception
                    {
                        arg0.pipeline().addLast(new SimpleServiceHandler());

                    }
                }).option(ChannelOption.SO_BACKLOG, 1024);//指定此套接口排队的最大连接个数
                ChannelFuture f=b.bind(port).sync();
                f.channel().closeFuture().sync();
            }
            finally
            {
                bossGruop.shutdownGracefully();
                workGroup.shutdownGracefully();
            }
        }
        public static void main(String[] args) throws InterruptedException
        {

        }
}
