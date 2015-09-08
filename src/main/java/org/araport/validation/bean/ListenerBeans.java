package org.araport.validation.bean;

import org.araport.validation.listener.LogProcessListener;
import org.springframework.context.annotation.Bean;

public class ListenerBeans {

	 
    @Bean
	public LogProcessListener logProcessListener() {
		return new LogProcessListener();
	}
	
}
