package com.sy.myapplication.handler;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
/**
 * 创建时间： 2017/10/13 0013.
 * 编写人：Tina
 * 邮箱：1208156801@qq.com
 * 功能描述：
 */
public class SimpleClientHandler  extends ChannelInboundHandlerAdapter
{
//    String  a = "{\"cmd\":\"login\",\"data\":{\"workerid\":\"1\",\"workername\":\"aa\",\"picture\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512130621503&di=5ab598e8e5bad4ee4a7b99410af96d73&imgtype=0&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2014-10-04%2F020149337.jpg\",\"star\":\"5\"}}\r\n";

    String  a = "{\"cmd\":\"login\",\"data\":{\"workerid\":\"1\",\"workername\":\"aa\",\"picture\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512475218457&di=12edef499a79736208f6362c42fb08ff&imgtype=0&src=http%3A%2F%2Fwww.jf258.com%2Fuploads%2F2015-05-15%2F190408873.jpg\",\"star\":\"5\"}}\r\n";
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("SimpleClientHandler.channelRead");
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        System.out.println("Server said:" + new String(result1));

//        String response = "我收到lllll!\r\n";
//        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
//        encoded.writeBytes(response.getBytes());
//        ctx.write(encoded);
//        ctx.flush();
//        result.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }


    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        String response = "{\"cmd\":\"login\",\"data\":{\"userid\":\"1\",\"username\":\"aa\"}}\r\n";
        ByteBuf encoded = ctx.alloc().buffer(a.getBytes().length);
        encoded.writeBytes(a.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }
}
