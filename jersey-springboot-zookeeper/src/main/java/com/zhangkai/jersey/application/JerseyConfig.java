package com.zhangkai.jersey.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.zhangkai.jersey.resource.DemoResource;

/**
 * 
 * @author and0429
 *
 */
//@Named
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(DemoResource.class);
    }
}
