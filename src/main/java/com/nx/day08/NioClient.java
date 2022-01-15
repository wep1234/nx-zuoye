package com.nx.day08;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author wep
 * @since 2021/05/09
 *
 */
public class NioClient {
    private static final int PORT = 8090;

    private Selector selector;

    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient();
        client.initClinet();
        client.connect();
    }

    /**
     * 获得一个socket通道
     * @throws IOException
     *
     */
    private void initClinet() throws IOException {
        //获得一个socket通道
        SocketChannel channel = SocketChannel.open();
        //设置非阻塞
        channel.configureBlocking(false);
        //获得一个通道管理器
        this.selector = Selector.open();
        channel.connect(new InetSocketAddress("127.0.0.1",PORT));
        //将通道管理器与通道绑定，并注册connect事件
        channel.register(selector,SelectionKey.OP_CONNECT);
    }

    public void connect() throws IOException {
        while (true){
            selector.select();
            Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                //删除已选的key，以防重复处理
                it.remove();
                //连接事件发生
                if(key.isConnectable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    //如果正在连接则完成连接
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    //设置非阻塞
                    channel.configureBlocking(false);
                    //给服务端发消息
                    ByteBuffer buffer = ByteBuffer.wrap("Hello World".getBytes());
                    channel.write(buffer);
                    //注册读事件
                    channel.register(this.selector,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    read(key);
                }
            }
        }
    }

    /**
     * 处理服务端发过来的信息
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int len = channel.read(byteBuffer);
        if(len != -1){
            System.out.println("客户端收到消息："+new String(byteBuffer.array(),0,len));
        }
    }
}
