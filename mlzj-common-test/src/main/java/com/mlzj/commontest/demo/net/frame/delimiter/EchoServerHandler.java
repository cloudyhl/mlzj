/*
 * Copyright 2012 The Netty Project
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
package com.mlzj.commontest.demo.net.frame.delimiter;

import com.mlzj.commontest.demo.net.frame.bean.RegisterEvent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.ChannelHandler.Sharable;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
@Sharable
public class EchoServerHandler extends ChannelHandlerAdapter {

    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
	    throws Exception {
	String body = (String) msg;
	System.out.println("This is " + ++counter + " times receive client : ["
		+ body + "]");
	body += "$_";
	ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
	ctx.writeAndFlush(echo);
    }



	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	cause.printStackTrace();
	ctx.close();// 发生异常，关闭链路
    }
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception{
		System.out.println(ctx);
		Channel channel = ctx.channel();
		channel.writeAndFlush(Unpooled.copiedBuffer("xxxxxxxx$_".getBytes()));
		ctx.writeAndFlush(Unpooled.copiedBuffer("xxxxxxxx$_".getBytes()));
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof RegisterEvent){
			System.out.println(true);
		}
		System.out.println(evt);
	}



}
