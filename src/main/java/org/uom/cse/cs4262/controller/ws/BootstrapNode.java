package org.uom.cse.cs4262.controller.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chanaka Lakmal
 * @date 5/11/2017
 * @since 1.0
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class BootstrapNode extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BootstrapNode.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootstrapNode.class);
    }
}