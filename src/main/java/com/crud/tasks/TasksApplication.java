package com.crud.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication { 	// for Heroku deployment
//public class TasksApplication extends SpringBootServletInitializer {  // remove for Heroku deployment

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

//	@Override	// remove for Heroku deployment configure() method
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//	    return application.sources(TasksApplication.class);
//    }
}
