package io.wisoft.eventsourcing.nosql.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NoSQLQueryApplication {

  public static void main(String[] args) {
    SpringApplication.run(NoSQLQueryApplication.class, args);
  }
}
