package com.nx.day16.tomcat.servlet;

import com.nx.day16.tomcat.data.Request;
import com.nx.day16.tomcat.data.Response;

/**
 * @author: wep
 * @Since: 2021/5/30 13:32
 */
public interface Servlet {

    void init() throws Exception;

    void destory() throws Exception;

    void service(Request req, Response rep) throws Exception;
}
