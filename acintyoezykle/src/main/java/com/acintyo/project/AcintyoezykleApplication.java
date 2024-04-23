package com.acintyo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = 
{@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE)})

public class AcintyoezykleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcintyoezykleApplication.class, args);
	
	}

}
