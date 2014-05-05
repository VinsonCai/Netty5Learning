package io.netty.example.factorial;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.oio.OioServerSocketChannel;

public class OioFactorialServer {
	private final int port;

	public OioFactorialServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new OioEventLoopGroup();
		EventLoopGroup workerGroup = new OioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(OioServerSocketChannel.class)
					.childHandler(new FactorialServerInitializer());

			b.bind(port).sync().channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
//        if (args.length > 0) {
//            port = Integer.parseInt(args[0]);
//        } else {
		port = 8080;
//        }
		new OioFactorialServer(port).run();
	}
}
