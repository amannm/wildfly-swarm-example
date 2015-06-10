package com.amannmalik.service.example;

import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSDeployment;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Name;

/**
 * Created by Amann on 6/10/2015.
 */
public class Application {

    public static void main(String[] args) throws Exception {

        //start container
        Container container = new Container();
        container.start();

        //example use of dnsjava. will cause a NullPointerException which is a result of a missing sun.net.dns.ResolverConfiguration class (the second, and most relied upon resolving strategy used after explicit run params)
        Name[] defaultSearchPaths = Lookup.getDefaultSearchPath();

        //deploy endpoint
        JAXRSDeployment appDeployment = new JAXRSDeployment(container);
        appDeployment.getArchive().addPackages(true, "com.amannmalik.service.example");
        container.deploy(appDeployment);

    }
}