package com.appvizory.houzing.qpidmsg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by elflord on 2/18/18.
 */
@EnableJms
@EnableScheduling
@SpringBootApplication
public class HelloWorldQpidSpringBoot {

    public static void main(String args[]){
        SpringApplication.run(HelloWorldQpidSpringBoot.class);
    }
}
