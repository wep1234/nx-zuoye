package com.nx.customIoc.bean.xml;

import com.nx.customIoc.bean.factory.registry.BeanDefinitionRegistry;
import com.nx.customIoc.bean.utils.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * @author: wep
 * @Since: 2021/5/10 21:21
 */
public class XmlBeandefintionReader {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeandefintionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(InputStream inputStream){
        Document document = DocumentReader.createDocument(inputStream);
        DefaultBeanDefinitionDocumentReader documentReader = new DefaultBeanDefinitionDocumentReader(beanDefinitionRegistry);
        documentReader.registerBeanDefinitions(document.getRootElement());
    }
}
