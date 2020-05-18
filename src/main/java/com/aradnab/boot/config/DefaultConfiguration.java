package com.aradnab.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

public class DefaultConfiguration {
    @Autowired
    Environment environment;
    public void somePlaceInTheCode() {
        // Port
        environment.getProperty("server.port");
        // Local address
//        InetAddress.getLocalHost().getHostAddress();
//        InetAddress.getLocalHost().getHostName();

        // Remote address
        String hostAddress = InetAddress.getLoopbackAddress().getHostAddress();
        String hostName = InetAddress.getLoopbackAddress().getHostName();
        System.out.println("Host Address = "+hostAddress);
        System.out.println("Host Name = "+hostName);
    }
}
