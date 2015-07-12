package com.amannmalik.service.example.endpoint;

import com.amannmalik.service.example.data.ExampleDao;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.serverError;

/**
 * Created by Amann on 6/10/2015.
 */

@Api(value = "/api", description = "An Example API")
@Path("/api")
public class ExampleResource {

    @Inject
    private ExampleDao data;

    @Resource(lookup = "jboss/ee/concurrency/executor/default")
    private ManagedExecutorService mes;

    @ApiOperation(value = "Get an example response")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Example response was successfully returned"),
            @ApiResponse(code = 500, message = "An internal server error occurred")
    })
    @Path("/operation")
    @GET
    @Produces("application/json")
    public Response example() {
        String messageText;
        try {
            messageText = data.read();
        } catch (Exception ex) {
            return serverError().build();
        }
        mes.submit(() -> {
            System.out.println(messageText);
        });
        String responseText = Json.createObjectBuilder().add("message", messageText).build().toString();
        return ok(responseText).build();
    }
}

