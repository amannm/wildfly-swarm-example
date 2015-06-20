package com.amannmalik.service.example;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Amann on 6/10/2015.
 */

@Path("/")
public class Endpoint {

    @Resource(lookup = "java:jboss/ee/concurrency/executor/default")
    private ManagedExecutorService mes;

    @GET
    public String example() {
        mes.submit(() -> {
            System.out.println("Hey");
        });
        return "Hello World";
    }
}

