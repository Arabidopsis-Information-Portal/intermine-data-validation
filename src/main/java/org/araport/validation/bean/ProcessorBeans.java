package org.araport.validation.bean;

import org.araport.validation.domain.NCBIGeneLookup;
import org.araport.validation.domain.NCBIPubMedGene;
import org.araport.validation.domain.Person;
import org.araport.validation.domain.ReaderLine;
import org.araport.validation.domain.TAIRLocusPublication;
import org.araport.validation.processor.NCBIGeneLookupProcessor;
import org.araport.validation.processor.NCBIGenePubMedProcessor;
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

	
	@Bean
	ItemProcessor<NCBIGeneLookup, NCBIGeneLookup> ncbiGeneLookupProcessor(){
		return new NCBIGeneLookupProcessor();
	}
	
	@Bean
	ItemProcessor<NCBIPubMedGene, NCBIPubMedGene> ncbiGenePubMedProcessor(){
		return new NCBIGenePubMedProcessor();
	}
	
}
