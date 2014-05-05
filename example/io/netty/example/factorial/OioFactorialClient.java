package io.netty.example.factorial;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.oio.OioSocketChannel;

public class OioFactorialClient {
	private final String host;
	private final int port;
	private final int count;

	public OioFactorialClient(String host, int port, int count) {
		this.host = host;
		this.port = port;
		this.count = count;
	}

	public void run() throws Exception {
		EventLoopGroup group = new OioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
					.channel(OioSocketChannel.class)
					.handler(new FactorialClientInitializer(count));

			// Make a new connection.
			ChannelFuture f = b.connect(host, port).sync();

			// Get the handler instance to retrieve the answer.
			FactorialClientHandler handler =
					(FactorialClientHandler) f.channel().pipeline().last();

			// Print out the answer.
			System.err.format(
					"=======================\nFactorial of %,d is: %,d\n====================\n", count, handler.getFactorial());
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		// Print usage if no argument is specified.
//        if (args.length != 3) {
//            System.err.println(
//                    "Usage: " + FactorialClient.class.getSimpleName() +
//                    " <host> <port> <count>");
//            return;
//        }

		// Parse options.
		String host = "localhost";// args[0];
		int port = 8080;// Integer.parseInt(args[1]);
		int count = 20;// Integer.parseInt(args[2]);
		if (count <= 0) {
			throw new IllegalArgumentException("count must be a positive integer.");
		}

		new OioFactorialClient(host, port, count).run();
	}
}
