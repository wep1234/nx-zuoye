package com.nx.day02;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 加载网络上的class文件
 */
public class NetworkClassLoader extends ClassLoader{
    private String ip;
    private String clName;
    private static String basePath = "com.naixue.";

    public NetworkClassLoader(String ip,String clName){
        this.ip = ip;
        this.clName = clName;
    }

    @Override
    public Class findClass(String name){
        byte[] b = loadClassData(name);
        return  defineClass(name,b,0,b.length);
//        if (Objects.equals("Cat", name)) {
//            return  defineClass(name,b,0,b.length);
//        }
//        return  defineClass(basePath+name,b,0,b.length);
    }

    private byte[] loadClassData(String name) {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            String urlPath = ip+name.replace(".","\\")+".class";
            URL url = new URL(urlPath);
            byte[] buff = new byte[1024*4];
            int len = -1;
            in = url.openStream();
            out = new ByteArrayOutputStream();
            while ( (len = in.read(buff))!=-1){
                out.write(buff,0,len);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
