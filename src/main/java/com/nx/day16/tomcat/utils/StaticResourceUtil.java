package com.nx.day16.tomcat.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author: wep
 * @Since: 2021/5/30 13:37
 */
public class StaticResourceUtil {

    /**
     * 获取静态资源的绝对路径
     * @param path
     * @return
     */
    public static String getAbsoultePath(String path) {
        String absoultePath = StaticResourceUtil.class.getResource("/").getPath();
        return absoultePath.replaceAll("\\\\","/")+path;
    }

    /**
     * 读取静态资源文件输入流，通过输出流输出
     * @param is
     * @param ops
     */
    public static void outputStaticResource(InputStream is, OutputStream ops) throws IOException {
        int count = 0;
        while (count == 0){
            count =is.available();
        }
        int resourceSize = count;
        ops.write(HttpProtocolUtil.getHttpHeader200(resourceSize).getBytes());

        //已经读取的长度
        long written = 0;
        //计划每次缓冲的长度
        int byteSize = 1024;
        byte[] bytes = new byte[byteSize];

        while (written < resourceSize){
            //剩余未读长度不足一个1024长度，按剩余长度读取
            if((written + byteSize) > resourceSize){
                byteSize = (int)(resourceSize - written);
                bytes = new byte[byteSize];
            }
            is.read(bytes);
            ops.write(bytes);
            ops.flush();
            written += byteSize;
        }
    }

}
