package org.araport.validation;

import org.apache.log4j.Logger;
import org.araport.validation.stat.ValidationStats;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationLauncher {

	private static final Logger log = Logger
			.getLogger(ApplicationLauncher.class);
	
    public static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);
        
        log.info("Validation application started.");
        log.info("Validation application completed.");
        
        log.info(ValidationStats.getStatistics());
    }
}
