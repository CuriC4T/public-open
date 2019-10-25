package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//import python.Crawling;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		//Crawling crw = new Crawling();
		//GetWeather dd = new GetWeather();
	//dd.searchWeather("20190504", "20190507");
		ApplicationContext ctx=SpringApplication.run(Application.class, args);
	//DatabaseConnection a = new DatabaseConnection();
	//a.getPrice("20190107");
	}
}
