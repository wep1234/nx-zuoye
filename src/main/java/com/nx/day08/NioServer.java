package com.nx.day08;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wep
 * @since 2021/05/09
 *
 */
public class NioServer {

    private static final int PORT = 8090;
    private Selector selector;

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer();
        server.initServer();
        server.listen();
    }

    private void initServer() throws IOException{
        //创建一个selector
        this.selector = Selector.open();
        //创建serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //bind一个端口
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //将channel注册selector上并注册accept事件
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
    }

    private void listen() throws IOException{
        System.out.println("server start");
        while (true){
            selector.select();
            //获取就绪的channel
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                //如果是accept事件
                if(selectionKey.isAcceptable()){
                    //强转为ServerSocketChannel
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = ssc.accept();
                    System.out.println("接收客户端连接："+socketChannel.getRemoteAddress());
                    socketChannel.configureBlocking(false);
                    //注册读事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    //如果是读事件 强转为SocketChannel
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //创建buffer用来读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //将数据读入buffer中
                    int length = socketChannel.read(buffer);
                    if(length > 0){
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String content = new String(bytes,"UTF-8").replace("\r\n","");
                        System.out.println("收到消息："+content);
                        //给客户发消息
                        ByteBuffer sendBuffer = ByteBuffer.wrap("Hello Client".getBytes());
                        socketChannel.write(sendBuffer);
                    }else{
                        System.out.println("客户端关闭");
                        selectionKey.cancel();
                    }
                }
            }
        }
    }
}
