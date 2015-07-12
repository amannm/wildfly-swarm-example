package com.amannmalik.service.example.endpoint;

import com.wordnik.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amann.malik on 6/21/2015.
 */

@ApplicationPath("/")
public class ExampleResourceApplication extends Application {

    public ExampleResourceApplication() {
        BeanConfig config = new BeanConfig();

        config.setTitle("Example Resource API");
        config.setDescription("An example service employing various boilerplate features");
        config.setContact("amannmalik@gmail.com");
        config.setLicense("All Rights Reserved");
        config.setLicenseUrl("http://www.amannmalik.com/legal");

        config.setVersion("1");
        config.setBasePath("/");
        config.setResourcePackage("com.amannmalik.service.example.endpoint");

        config.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        resources.add(ExampleResource.class);

        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);

        return resources;
    }
}
