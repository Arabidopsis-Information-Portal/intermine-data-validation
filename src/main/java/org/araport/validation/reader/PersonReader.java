package org.araport.validation.reader;

import org.araport.validation.ApplicationJobConfig;
import org.araport.validation.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component("person_reader")
@PropertySources(value = { @PropertySource("classpath:/in/data_source.properties")
})
public class PersonReader {

	private static final Logger log = LoggerFactory
			.getLogger(PersonReader.class);

	@Autowired
	Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Bean
	public ItemReader<Person> personReader() {
	        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
	        reader.setResource(new FileSystemResource(environment.getProperty("person.path")));
	        reader.setLineMapper(new DefaultLineMapper<Person>() {{
	            setLineTokenizer(new DelimitedLineTokenizer() {{
	                setNames(new String[] { "firstName", "lastName" });
	            }});
	            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
	                setTargetType(Person.class);
	            }});
	        }});
	        return reader;
	    }
	
}
