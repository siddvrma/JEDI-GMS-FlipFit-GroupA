package com.flipkart;

import com.flipkart.config.FlipFitConfig;
import com.flipkart.controllers.FlipFitController;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FlipFitAppMenu extends Application<FlipFitConfig> {

    public static void main(String[] args) throws Exception {
        new FlipFitAppMenu().run("server");
    }

    @Override
    public void initialize(Bootstrap<FlipFitConfig> bootstrap) {
        // Additional initialization code if needed
    }

    @Override
    public void run(FlipFitConfig configuration, Environment environment) {
        environment.jersey().register(new FlipFitController());
    }
}
