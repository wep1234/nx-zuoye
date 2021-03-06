package com.nx.day02;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 加载本机上的class文件
 */
public class SelfClassLoader extends ClassLoader{
    private String path;
    private String clName;


    public SelfClassLoader(String path, String clName) {
        this.path = path;
        this.clName = clName;
    }


    @Override
    public Class findClass(String name) {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {
        name = path+name.replace(".","\\")+".class";
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ((i=in.read())!=-1){
                out.write(i);
            }
        }catch (Exception e){

        }finally {
            //关闭流
        }
        return out.toByteArray();

    }
}
