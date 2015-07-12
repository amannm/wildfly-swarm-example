package com.amannmalik.service.example;

import com.amannmalik.service.example.endpoint.ExampleResourceApplication;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.datasources.Datasource;
import org.wildfly.swarm.datasources.DatasourceDeployment;
import org.wildfly.swarm.datasources.DriverDeployment;
import org.wildfly.swarm.jaxrs.JAXRSDeployment;

/**
 * Created by Amann on 6/10/2015.
 */
public class Application {

    public static void main(String[] args) throws Exception {

        //start container
        Container container = new Container();
        container.start();

        //deploy driver
        DriverDeployment driverDeployment = new DriverDeployment(container, "net.sourceforge.jtds:jtds:1.3.1", "jtds");
        container.deploy(driverDeployment);

        //deploy datasource
        Datasource datasource = new Datasource("myDataSource").connectionURL("jdbc:jtds:sqlserver://example.test.com:1433/Report;domain=MYDOMAIN").driver("jtds").authentication("username", "password");
        DatasourceDeployment dsDeployment = new DatasourceDeployment(container, datasource);
        container.deploy(dsDeployment);

        //deploy endpoint
        JAXRSDeployment appDeployment = new JAXRSDeployment(container);
        appDeployment.setApplication(ExampleResourceApplication.class);
        appDeployment.getArchive().addPackages(true, "com.amannmalik.service.example");
        appDeployment.staticContent("/", "public");
        container.deploy(appDeployment);

    }
}