package service;

import javax.xml.ws.Endpoint;

public class Service {
    private static final String URL = "http://localhost:8080/metaData";
    public static void main(String[] args) {

        System.out.println("Starting server...");
        Endpoint.publish(URL, new MetaDataExtractor());
        System.out.println("Server started");
    }
}