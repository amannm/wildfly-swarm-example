package com.amannmalik.service.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Amann on 6/10/2015.
 */

@Path("/")
public class Endpoint {

    @GET
    public String example() {
        return "Hello World";
    }
}

