package org.araport.validation.bean;

import org.araport.validation.domain.Person;
import org.araport.validation.domain.TAIRLocusPublication;
import org.araport.validation.processor.PersonItemProcessor;
import org.araport.validation.processor.TAIRPublicationItemProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;

public class ProcessorBeans {

	@Bean
	public ItemProcessor<Person, Person> personProcessor() {
		return new PersonItemProcessor();
	}

	@Bean
	public ItemProcessor<TAIRLocusPublication, TAIRLocusPublication> tairPublicationProcessor() {

		return new TAIRPublicationItemProcessor();
	}

}
