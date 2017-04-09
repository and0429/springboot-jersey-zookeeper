package com.zhangkai.jersey.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;


/**
 * 
 * @author and0429
 *
 */
@Path("/")
@Named
public class DemoResource {

    @Inject
    private DiscoveryClient discoveryClient;
    
    @Value("{spring.application.name:bootZookeeper}")
    private String appName;
    
    @Path("hi")
    @GET
    public String hi(){
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        return serviceInstance.getHost() + ":" + serviceInstance.getPort();
    }
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServiceInstance> getServices(){
        return discoveryClient.getInstances(appName);
    }
}
