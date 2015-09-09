package org.araport.validation.reader;

import org.araport.validation.domain.NCBIGeneLookup;
import org.araport.validation.domain.NCBIPubMedGene;
import org.araport.validation.domain.ReaderLine;
import org.araport.validation.domain.TAIRLocusPublication;
import org.araport.validation.fieldmapper.NCBIGeneLookupFieldMapper;
import org.araport.validation.fieldmapper.NCBIGenePubMedFieldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
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

@Component("ncbi_gene_pubmed_reader")
@PropertySources(value = { @PropertySource("classpath:/in/data_source.properties")
})
public class NCBIGenePubMedReader {
	private static final Logger log = LoggerFactory
			.getLogger(NCBIGenePubMedReader.class);
	
	@Autowired
	Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;
	
	private String [] comments = {"#"};
	
	@Bean
	public ItemReader<NCBIPubMedGene> ncbiGenePubMedReader() {
		
	        FlatFileItemReader<NCBIPubMedGene> reader = new FlatFileItemReader<NCBIPubMedGene>();
	        
	        reader.setResource(new FileSystemResource(environment.getProperty("gene.pubmed.data.path")));
	        
	        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
	                   
            lineTokenizer.setDelimiter("\t");
            DefaultLineMapper<NCBIPubMedGene> lineMapper =    new DefaultLineMapper<NCBIPubMedGene>();
            lineMapper.setLineTokenizer(lineTokenizer);
            lineMapper.setFieldSetMapper(new NCBIGenePubMedFieldMapper());
            reader.setLineMapper(lineMapper);
	        reader.setLinesToSkip(1);
	        reader.setComments(comments);
	       //.setMaxItemCount(10);
	              
	        return reader;
	    }
	
}
