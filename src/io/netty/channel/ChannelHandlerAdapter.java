/*
 * Copyright 2013 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.netty.channel;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.net.SocketAddress;

/**
 * Skelton implementation of a {@link ChannelHandler}.
 */
public class ChannelHandlerAdapter implements ChannelHandler {
	private static final InternalLogger logger = InternalLoggerFactory.getInstance(ChannelHandlerAdapter.class);

	// Not using volatile because it's used only for a sanity check.
	boolean added;

	/**
	 * Return {@code true} if the implementation is {@link Sharable} and so can be added to different
	 * {@link ChannelPipeline}s.
	 */
	public boolean isSharable() {
		return getClass().isAnnotationPresent(Sharable.class);
	}

	/**
	 * Do nothing by default, sub-classes may override this method.
	 */
	@Skip
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// NOOP
	}

	/**
	 * Do nothing by default, sub-classes may override this method.
	 */
	@Skip
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// NOOP
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward to the next {@link ChannelHandler}
	 * in the {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.fireExceptionCaught(cause);
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireChannelRegistered()} to forward to the next {@link ChannelHandler} in the
	 * {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		ctx.fireChannelRegistered();
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireChannelActive()} to forward to the next {@link ChannelHandler} in the
	 * {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.fireChannelActive();
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireChannelInactive()} to forward to the next {@link ChannelHandler} in the
	 * {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.fireChannelInactive();
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward to the next {@link ChannelHandler} in the
	 * {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("ctx.fireChannelRead(msg);" + msg);
		ctx.fireChannelRead(msg);
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireChannelReadComplete()} to forward to the next {@link ChannelHandler} in
	 * the {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.fireChannelReadComplete();
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireUserEventTriggered(Object)} to forward to the next {@link ChannelHandler}
	 * in the {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		ctx.fireUserEventTriggered(evt);
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireChannelWritabilityChanged()} to forward to the next {@link ChannelHandler}
	 * in the {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		ctx.fireChannelWritabilityChanged();
	}

	/**
	 * Calls {@link ChannelHandlerContext#bind(java.net.SocketAddress, ChannelPromise)} to forward to the next
	 * {@link ChannelHandler} in the {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		ctx.bind(localAddress, promise);
	}

	/**
	 * Calls {@link ChannelHandlerContext#connect(SocketAddress, SocketAddress, ChannelPromise)} to forward to the next
	 * {@link ChannelHandler} in the {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void connect(
			ChannelHandlerContext ctx,
			SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		ctx.connect(remoteAddress, localAddress, promise);
	}

	/**
	 * Calls {@link ChannelHandlerContext#disconnect(ChannelPromise)} to forward to the next {@link ChannelHandler} in
	 * the {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		ctx.disconnect(promise);
	}

	/**
	 * Calls {@link ChannelHandlerContext#close(ChannelPromise)} to forward to the next {@link ChannelHandler} in the
	 * {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		ctx.close(promise);
	}

	/**
	 * Calls {@link ChannelHandlerContext#read()} to forward to the next {@link ChannelHandler} in the
	 * {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		ctx.read();
	}

	/**
	 * Calls {@link ChannelHandlerContext#write(Object)} to forward to the next {@link ChannelHandler} in the
	 * {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		ctx.write(msg, promise);
	}

	/**
	 * Calls {@link ChannelHandlerContext#flush()} to forward to the next {@link ChannelHandler} in the
	 * {@link ChannelPipeline}.
	 * 
	 * Sub-classes may override this method to change behavior.
	 */
	@Skip
	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}
