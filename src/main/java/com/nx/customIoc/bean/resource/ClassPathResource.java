package com.nx.customIoc.bean.resource;

import java.io.InputStream;

/**
 * @author: wep
 * @Since: 2021/5/10 21:23
 */
public class ClassPathResource implements Resource {

    private String location;

    public ClassPathResource(String location) {
        this.location = location;
    }

    @Override
    public InputStream getInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }
}
