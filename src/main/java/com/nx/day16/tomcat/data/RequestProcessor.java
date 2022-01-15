package com.nx.day16.tomcat.data;

import com.nx.day16.tomcat.servlet.HttpServlet;

import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

/**
 * @author: wep
 * @Since: 2021/5/30 14:10
 */
public class RequestProcessor extends Thread{

    private Socket socket;
    private Map<String,HttpServlet> servletMap;

    public RequestProcessor(Socket socket, Map<String, HttpServlet> servletMap) {
        this.socket = socket;
        this.servletMap = servletMap;
    }

    @Override
    public void run(){
        try{
            InputStream inputStream = socket.getInputStream();

            Request req = new Request(inputStream);
            Response rep = new Response(socket.getOutputStream());

            if(servletMap.get(req.getUrl()) == null){
                rep.outputHtml(req.getUrl());
            }else{
                HttpServlet servlet = servletMap.get(req.getUrl());
                servlet.service(req,rep);
            }
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
