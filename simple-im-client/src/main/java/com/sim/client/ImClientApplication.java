package com.sim.client;

import com.sim.client.config.ServerConfig;
import com.sim.client.handler.input.InputThread;
import com.sim.client.handler.msg.ClientMsgDecoder;
import com.sim.client.handler.msg.ImClientMsgHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Slf4JLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * @author xiaoshun.cxs
 * 2020/12/15
 **/
@SpringBootApplication
public class ImClientApplication implements CommandLineRunner {
    @Autowired
    private ServerConfig serverConfig;

    public static void main(String[] args) {
        SpringApplication.run(ImClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InternalLoggerFactory.setDefaultFactory(Slf4JLoggerFactory.INSTANCE);

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.AUTO_READ, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(
                                    new LoggingHandler(LogLevel.DEBUG),
                                    new ClientMsgDecoder(),
                                    new LineBasedFrameDecoder(4096, true, true),
                                    new ImClientMsgHandler()
                            );
                        }
                    });

            ChannelFuture f = b.connect(serverConfig.getHost(), serverConfig.getPort()).sync();
            new Thread(new InputThread(f)).start();

            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }
}
