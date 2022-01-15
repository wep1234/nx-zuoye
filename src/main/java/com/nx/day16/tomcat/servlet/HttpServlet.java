package com.nx.day16.tomcat.servlet;

import com.nx.day16.tomcat.data.Request;
import com.nx.day16.tomcat.data.Response;

/**
 * @author: wep
 * @Since: 2021/5/30 13:57
 */
public abstract class HttpServlet implements Servlet {


    @Override
    public void service(Request req, Response rep) throws Exception {
        if("GET".equalsIgnoreCase(req.getMethod())){
            doGet(req,rep);
        }else{
            doPost(req,rep);
        }
    }

    public abstract void doGet(Request req, Response rep);

    public abstract void doPost(Request req, Response rep);
}
