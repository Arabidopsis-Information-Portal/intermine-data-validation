package org.araport.validation.processor;

import org.apache.commons.lang3.StringUtils;
import org.araport.validation.Constansts;
import org.araport.validation.domain.NCBIGeneLookup;
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
public class NCBIGeneLookupProcessor implements
		ItemProcessor<NCBIGeneLookup, NCBIGeneLookup> {

	private static final Logger log = LoggerFactory
			.getLogger(NCBIGeneLookupProcessor.class);

	@Autowired
	Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public NCBIGeneLookup process(NCBIGeneLookup item) throws Exception {

		ValidationStats.ncbiGeneLookupInputRecordCount.increment();

		String lookupTaxonId = environment.getProperty("taxonId");

		log.info("Target Lookup Taxon Id:" + lookupTaxonId);
		
		String taxonId = item.getTaxonId();

		log.info("Input NCBI Gene Lookup Input Record : " + item);

		/*
		if (!isLookupTaxon(taxonId, lookupTaxonId)) {

			log.info("NCBI input gene lookup taxonId Id Is Not Target Lookup Taxon. Skipping processing this line. "
					+ item + "; TaxonId: " + taxonId);
			ValidationStats.ncbiGeneNotTargetLookupTaxonRecordCount.increment();
			return null;

		} **/

		ValidationStats.ncbiGeneLookupProcessedRecordCount.increment();

		log.info("Output NCBI Gene Lookup Record: " + item);

		log.info("Current Statistics:"
				+ ValidationStats.getNCBIGeneLookupStatistics());

		return item;

	}

	private boolean isValidLine(final String[] elements) {
		boolean result = false;

		if (elements == null || elements.length == 0) {
			result = false;

			return result;
		}

		if (elements.length != 3) {
			result = false;
		}

		return result;
	}

	private boolean isLookupTaxon(final String taxonId, String lookupTaxonId) {

		boolean result = false;

		if (taxonId.equals(lookupTaxonId)) {
			result = true;
		}

		return result;
	}

	private boolean isValidElement(final String element) {

		boolean result = true;

		if (StringUtils.isBlank(element)) {
			result = false;
		}

		return result;
	}

	private boolean isValidEntry(final String taxonId, final String geneId) {

		boolean result = true;

		if (isValidElement(taxonId) && isValidElement(geneId)) {
			result = false;
		}

		return result;
	}

}
