package com.amannmalik.service.example;

/**
 * Created by Amann on 6/10/2015.
 */
public class ServerAddress {

    public final String hostname;
    public final int port;

    protected ServerAddress(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public String toString() {
        return hostname + ":" + port;
    }
}