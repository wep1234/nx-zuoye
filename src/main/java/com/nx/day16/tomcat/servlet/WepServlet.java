package com.nx.day16.tomcat.servlet;

import com.nx.day16.tomcat.data.Request;
import com.nx.day16.tomcat.data.Response;
import com.nx.day16.tomcat.utils.HttpProtocolUtil;

import java.io.IOException;

/**
 * @author: wep
 * @Since: 2021/5/30 14:00
 */
public class WepServlet extends HttpServlet {
    @Override
    public void doGet(Request req, Response rep) {
        String content = "<h1>hello boys </h1>";
        try {
            rep.output(HttpProtocolUtil.getHttpHeader200(content.getBytes().length)+content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(Request req, Response rep) {
        String content = "<h1>Wep post </h1>";
        try {
            rep.output(HttpProtocolUtil.getHttpHeader200(content.getBytes().length)+content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destory() throws Exception {

    }
}
