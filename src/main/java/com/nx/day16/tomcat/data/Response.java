package com.nx.day16.tomcat.data;

import com.nx.day16.tomcat.utils.HttpProtocolUtil;
import com.nx.day16.tomcat.utils.StaticResourceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: wep
 * @Since: 2021/5/30 13:32
 */
public class Response {

    private OutputStream ops;

    public Response(){

    }

    public Response(OutputStream ops) {
        this.ops = ops;
    }

    public void output(String content) throws IOException{
        ops.write(content.getBytes());
    }

    public void outputHtml(String path) throws IOException{
        //获取静态资源文件的绝对路径
        String absouteResourcePath = StaticResourceUtil.getAbsoultePath(path);

        File file = new File(absouteResourcePath);

        if(file.exists() && file.isFile()){
            //读取静态资源文件，输出
            StaticResourceUtil.outputStaticResource(new FileInputStream(file),ops);
        }else{
            output(HttpProtocolUtil.getHttpHeader404());
        }
    }
}
