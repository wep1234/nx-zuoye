package com.nx.day16.tomcat;

import com.nx.day16.tomcat.data.Request;
import com.nx.day16.tomcat.data.RequestProcessor;
import com.nx.day16.tomcat.servlet.HttpServlet;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author: wep
 * @Since: 2021/5/30 13:21
 */
public class Bootstarp {

    ThreadPoolExecutor threadPoolExecutor = createThreadPool();

    private ThreadPoolExecutor createThreadPool() {
        int corePoolSize = 20;
        int maximumPoolSize = 50;
        long keepAliveTime = 100L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(50);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );
    }

    private int port = 8088;

    private Map<String,HttpServlet> servletMap = new HashMap();

    public void start() throws Exception{
        loadServlet();

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("tomcat 已经启动 port:"+port);
        while(true){
            Socket socket = serverSocket.accept();
            RequestProcessor requestProcessor = new RequestProcessor(socket,servletMap);
            threadPoolExecutor.execute(requestProcessor);
        }
    }


    public static void main(String[] args) {
        Bootstarp bootstarp = new Bootstarp();
        try {
            bootstarp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 加载解析web.xml,初始化Servlet
     */
    private void loadServlet() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("web.xml");
        SAXReader saxReader = new SAXReader();

        try{
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();

            List<Element> selectNodes = rootElement.selectNodes("//servlet");
            for(Element element : selectNodes){
                Element servletnameElement = (Element)element.selectSingleNode("servlet-name");
                String servletName = servletnameElement.getStringValue();
                Element servletclassElement = (Element)element.selectSingleNode("servlet-class");
                String  servletClass = servletclassElement.getStringValue();
                // 根据servlet-name的值找到url-pattern
                Element servletMapping = (Element)rootElement.selectSingleNode("/web-app/servlet-mapping[servlet-name='" + servletName + "']");
                String urlPattern = servletMapping.selectSingleNode("url-pattern").getStringValue();
                servletMap.put(urlPattern,(HttpServlet)Class.forName(servletClass).newInstance());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
