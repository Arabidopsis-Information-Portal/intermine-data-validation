package org.araport.validation.reader;

import org.araport.validation.domain.UniprotEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

@Component("uniprot_swissprot_reader")
@PropertySources(value = { @PropertySource("classpath:/in/data_source.properties")
})
public class UniprotSwissProtXMLReader {

	private static final Logger log = LoggerFactory
			.getLogger(UniprotSwissProtXMLReader.class);

	@Autowired
	Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private Unmarshaller uniprotEntryMarshaller;
	
	@Bean
	public ItemReader<UniprotEntry> uniprotSwissProtReader() {
		StaxEventItemReader<UniprotEntry> reader = new StaxEventItemReader<UniprotEntry>();
		reader.setResource(new FileSystemResource(environment.getProperty("uniprot_sprot.data.path")));
		reader.setFragmentRootElementName("uniprot_entry");
		reader.setUnmarshaller(uniprotEntryMarshaller);

		return reader;
	}
}
