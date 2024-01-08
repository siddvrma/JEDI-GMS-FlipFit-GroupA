package com.flipkart.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class FlipFitController {
    @GET
    public String sayHello() {
        return "Hello, World!";
    }
}
