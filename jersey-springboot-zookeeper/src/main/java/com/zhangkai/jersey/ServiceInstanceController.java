package com.zhangkai.jersey;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/si")
public class ServiceInstanceController {

    @Inject
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name:bootZookeeper}")
    private String appName;

    @RequestMapping("/hi")
    public String hi() {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        return serviceInstance.getHost() + ":" + serviceInstance.getPort();
    }

    @RequestMapping(value = "/all", produces = { MediaType.APPLICATION_JSON })
    public List<ServiceInstance> getServices() {
        return discoveryClient.getInstances(appName);
    }

}
