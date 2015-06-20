package com.amannmalik.service.example;

import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSDeployment;

/**
 * Created by Amann on 6/10/2015.
 */
public class Application {

    public static void main(String[] args) throws Exception {

        //start container
        Container container = new Container();
        container.start();

        //deploy endpoint
        JAXRSDeployment appDeployment = new JAXRSDeployment(container);
        appDeployment.getArchive().addPackages(true, "com.amannmalik.service.example");
        container.deploy(appDeployment);

    }
}