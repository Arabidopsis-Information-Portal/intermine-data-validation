package org.araport.validation.reader;

import org.araport.validation.domain.Person;
import org.araport.validation.domain.TAIRLocusPublication;
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

@Component("tair_publication_reader")
@PropertySources(value = { @PropertySource("classpath:/in/data_source.properties")
})
public class TAIRPublicationReader {

	private static final Logger log = LoggerFactory
			.getLogger(TAIRPublicationReader.class);

	@Autowired
	Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Bean
	public ItemReader<TAIRLocusPublication> tairLocusPublicationReader() {
	        FlatFileItemReader<TAIRLocusPublication> reader = new FlatFileItemReader<TAIRLocusPublication>();
	        reader.setResource(new FileSystemResource(environment.getProperty("tair.publication.path")));
	        
	        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer() {{
                setNames(new String[] { "locusAGIId", "tairObjectId", "pubMedId", "year" });
            }};
            
            lineTokenizer.setDelimiter("\t");
            
            lineTokenizer.setNames(new String[] { "locusAGIId", "tairObjectId", "pubMedId", "year" });
            
	        reader.setLineMapper(new DefaultLineMapper<TAIRLocusPublication>() {{
	            setLineTokenizer(lineTokenizer);
	            setFieldSetMapper(new BeanWrapperFieldSetMapper<TAIRLocusPublication>() {{
	                setTargetType(TAIRLocusPublication.class);
	            }});
	        }});
	        return reader;
	    }
	
	
}
