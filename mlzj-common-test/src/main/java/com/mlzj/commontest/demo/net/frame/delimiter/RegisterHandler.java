package com.mlzj.commontest.demo.net.frame.delimiter;

import com.mlzj.commontest.demo.net.frame.bean.RegisterEvent;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author yhl
 * @date 2020/5/6
 */
public class RegisterHandler extends ChannelHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof RegisterEvent) {
            System.out.println(true);
        }
        System.out.println(evt);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.fireUserEventTriggered(new RegisterEvent("123.11.23.33", "test"));
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        System.out.println(11111);
        ctx.fireChannelRegistered();
    }
}