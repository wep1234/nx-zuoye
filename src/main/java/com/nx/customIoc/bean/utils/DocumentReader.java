package com.nx.customIoc.bean.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author: wep
 * @Since: 2021/5/10 21:26
 */
public class DocumentReader {

    public static Document createDocument(InputStream is){
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
