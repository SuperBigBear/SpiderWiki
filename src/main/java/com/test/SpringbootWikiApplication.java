package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com/test/api", "com/test/config"})
public class SpringbootWikiApplication {

    public static void main(String[] args) {
        System.out.println("Hi ,SpiderWiki is running....");
        SpringApplication.run(SpringbootWikiApplication.class, args);
    }
}
