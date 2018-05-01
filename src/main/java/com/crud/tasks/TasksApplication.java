package com.crud.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class TasksApplication { 	//extends SpringBootServletInitializer {  // removed for Heroku deployment

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

    /*@Override	// removed for Heroku deployment
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(TasksApplication.class);
    }*/
}
