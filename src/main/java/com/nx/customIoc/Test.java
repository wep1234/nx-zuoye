package com.nx.customIoc;

import com.nx.customIoc.bean.factory.support.DefaultListableBeanFactory;
import com.nx.customIoc.bean.resource.ClassPathResource;
import com.nx.customIoc.bean.resource.Resource;
import com.nx.customIoc.bean.xml.XmlBeandefintionReader;
import com.nx.customIoc.po.Role;
import com.nx.customIoc.po.User;

import java.io.InputStream;

/**
 * @author: wep
 * @Since: 2021/5/10 20:23
 */
public class Test {

    public static void main(String[] args) {
        String location = "bean.xml";
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeandefintionReader xmlBeandefintionReader = new XmlBeandefintionReader(beanFactory);
        Resource resource = new ClassPathResource(location);
        InputStream is = resource.getInputStream();
        xmlBeandefintionReader.loadBeanDefinitions(is);

//        User user = (User) beanFactory.getBean("user");
//        System.out.println("获取user对象"+user);


        Role role = (Role) beanFactory.getBean("role");
        System.out.println("获取role对象"+role);
    }
}
