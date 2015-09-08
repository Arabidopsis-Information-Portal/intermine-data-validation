package org.araport.validation.reader;

import org.araport.validation.domain.ReaderLine;
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

@Component("ncbi_gene_lookup_reader")
@PropertySources(value = { @PropertySource("classpath:/in/data_source.properties")
})
public class NCBIGeneLookupReader {
	private static final Logger log = LoggerFactory
			.getLogger(NCBIGeneLookupReader.class);
	
	@Autowired
	Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Bean
	public ItemReader<ReaderLine> tairLocusPublicationReader() {
		
	        FlatFileItemReader<ReaderLine> reader = new FlatFileItemReader<ReaderLine>();
	        reader.setResource(new FileSystemResource(environment.getProperty("gene.pubmed.lookup.path")));
	        
	        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer() {{
                setNames(new String[] { "line" });
            }};
            
                
	        reader.setLineMapper(new DefaultLineMapper<ReaderLine>() {{
	            setLineTokenizer(lineTokenizer);
	            setFieldSetMapper(new BeanWrapperFieldSetMapper<ReaderLine>() {{
	                setTargetType(ReaderLine.class);
	            }});
	        }});
	        
	        return reader;
	    }
	
}
