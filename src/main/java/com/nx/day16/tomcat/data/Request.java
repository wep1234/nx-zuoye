package com.nx.day16.tomcat.data;

import lombok.Data;

import java.io.InputStream;

/**
 * @author: wep
 * @Since: 2021/5/30 13:24
 */
@Data
public class Request {
    /**
     * 请求方式
     */
    private String method;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 输入流
     */
    private InputStream is;

    public Request(){

    }


    public Request(InputStream is) throws Exception{
        this.is = is;

        int count = 0;
        while (count == 0){
            count = is.available();
        }
        byte[] bytes = new byte[count];
        is.read(bytes);

        String inputStr = new String(bytes);
        // 获取第一行请求头信息 例如：GET / HTTP/1.1
        String firstLineStr = inputStr.split("\\n")[0];

        String[] arrys = firstLineStr.split(" ");

        this.method = arrys[0];
        this.url = arrys[1];

        System.out.println("method:"+method);
        System.out.println("url:"+url);
    }
}
