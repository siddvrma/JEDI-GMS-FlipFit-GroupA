package com.flipkart;

import com.flipkart.config.FlipFitConfig;
import com.flipkart.controller.AdminController;
import com.flipkart.controller.CustomerController;
import com.flipkart.controller.FlipFitController;
import com.flipkart.controller.GymOwnerController;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlipFitAppMenu extends Application<FlipFitConfig> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlipFitAppMenu.class);
    public static void main(String[] args) throws Exception {
        new FlipFitAppMenu().run("server");
    }

    @Override
    public void initialize(Bootstrap<FlipFitConfig> bootstrap) {
    }

    @Override
    public void run(FlipFitConfig configuration, Environment environment) {
        LOGGER.info("Registering REST resources");
        System.out.println("HERE");
        environment.jersey().register(new AdminController());
        environment.jersey().register(new CustomerController());
        environment.jersey().register(new GymOwnerController());
        environment.jersey().register(new FlipFitController());
    }
}
