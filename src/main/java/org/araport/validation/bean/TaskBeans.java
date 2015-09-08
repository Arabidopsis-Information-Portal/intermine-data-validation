package org.araport.validation.bean;

import org.araport.validation.tasklet.PostDeployTasklet;
import org.araport.validation.tasklet.StagingSchemaInitTasklet;
import org.springframework.context.annotation.Bean;

public class TaskBeans {

	@Bean
    StagingSchemaInitTasklet stagingSchemaInit(){
    	return new StagingSchemaInitTasklet();
    }
    
	@Bean
	PostDeployTasklet postDeployTask(){
		return new PostDeployTasklet();
	}
}
