package org.araport.validation.processor;

import org.apache.commons.lang3.StringUtils;
import org.araport.validation.Constansts;
import org.araport.validation.domain.NCBIGeneLookup;
import org.araport.validation.domain.NCBIPubMedGene;
import org.araport.validation.domain.ReaderLine;
import org.araport.validation.stat.ValidationStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

@PropertySources(value = { @PropertySource("classpath:/config/data_config.properties") })
public class NCBIGenePubMedProcessor implements
		ItemProcessor<NCBIPubMedGene, NCBIPubMedGene> {

	private static final Logger log = LoggerFactory
			.getLogger(NCBIGenePubMedProcessor.class);

	@Autowired
	Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public NCBIPubMedGene process(NCBIPubMedGene item) throws Exception {

		ValidationStats.ncbiGeneLookupInputRecordCount.increment();
				
		log.info("Input NCBI Gene PubMed Input Record : " + item);

		ValidationStats.ncbiGeneLookupProcessedRecordCount.increment();

		log.info("Output NCBI Gene PubMed Record: " + item);

		log.info("Current Statistics:"
				+ ValidationStats.getNCBIGenePubMedStatistics());

		return item;

	}

}
