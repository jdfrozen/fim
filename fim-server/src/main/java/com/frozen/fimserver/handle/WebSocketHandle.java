package com.frozen.fimserver.handle;

import com.frozen.fimserver.util.SessionSocketHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import org.springframework.util.StringUtils;

/**
 * @author: Frozen
 * @create: frozen-07 09:45
 * @description:
 **/
public class WebSocketHandle extends SimpleChannelInboundHandler<Object> {
    private WebSocketServerHandshaker handshaker;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive connect");
        SessionSocketHolder.add((NioSocketChannel) ctx.channel());
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof TextWebSocketFrame){
            String msgStr= ((TextWebSocketFrame)msg).text();
            if(!StringUtils.isEmpty(msgStr)){
                if(msgStr.contains("login")){
                    String[] strings = StringUtils.split(msgStr,":");
                    SessionSocketHolder.put(Long.valueOf(strings[1]), (NioSocketChannel) ctx.channel());
                }
            }
            System.out.println("收到消息："+msgStr);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(msgStr));
        }else if(msg instanceof BinaryWebSocketFrame){
            System.out.println("收到二进制消息："+((BinaryWebSocketFrame)msg).content().readableBytes());
            BinaryWebSocketFrame binaryWebSocketFrame=new BinaryWebSocketFrame(Unpooled.buffer().writeBytes("xxx".getBytes()));
            ctx.channel().writeAndFlush(binaryWebSocketFrame);
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        SessionSocketHolder.removeSet((NioSocketChannel) ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
    }
}
