package com.hod.project.hive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProjectHiveApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectHiveApiApplication.class, args);
    }

}
