package org.araport.validation.processor;

import org.apache.commons.lang3.StringUtils;
import org.araport.validation.domain.Person;
import org.araport.validation.domain.UniprotEntry;
import org.araport.validation.stat.ValidationStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class UniprotEntryTrembItemProcessor implements ItemProcessor<UniprotEntry, UniprotEntry> {

    private static final Logger log = LoggerFactory.getLogger(UniprotEntryTrembItemProcessor.class);

    @Override
    public UniprotEntry process(final UniprotEntry item) throws Exception {
    	
    	ValidationStats.uniprotTremblInputRecordCount.increment();
    	
    	final String primaryIdentifier = trimAttributeValue(item
				.getPrimaryIdentifier());
		final String primaryAccession = trimAttributeValue(item
				.getPrimaryAccession());
		final String uniprotAccession = trimAttributeValue(item
				.getUniprotAccession());
		final String proteinSynonym = trimAttributeValue(item
				.getProteinSynonym());

		final String uniprotName = trimAttributeValue(item.getUniprotName());
		final String canonicalIsoformAccession = trimAttributeValue(item
				.getCanonicalIsoformAccession());
		final String isoformAccession = trimAttributeValue(item
				.getIsoformAccession());

		final String genePrimaryIdentifier = trimAttributeValue(item
				.getGenePrimaryIdentifier());
		final String geneSymbol = trimAttributeValue(item.getGeneSymbol());
		final String tairIdentifier = trimAttributeValue(item
				.getTairIdentifier());
		final String pubMedId = trimAttributeValue(item.getPubMedId());
		final String dataSet = trimAttributeValue(item.getDataSet());

		String isoformIdentifier = null;

		if (StringUtils.isNotBlank(isoformAccession)
				&& StringUtils.isNotBlank(primaryIdentifier)
				) {
			isoformIdentifier = generateIsoformIdentifier(primaryIdentifier,
					isoformAccession);
		}

		log.info("Primary Identifer: " + primaryIdentifier
				+ "Primary Accession: " + primaryAccession
				+ "; Isoform Identifier: " + isoformIdentifier);

		final UniprotEntry transformedItem = new UniprotEntry();
		transformedItem.setPrimaryIdentifier(primaryIdentifier);
		transformedItem.setPrimaryAccession(primaryAccession);

		transformedItem.setUniprotAccession(uniprotAccession);
		transformedItem.setProteinSynonym(proteinSynonym);
		transformedItem.setUniprotName(uniprotName);
		transformedItem.setCanonicalIsoformAccession(canonicalIsoformAccession);
		transformedItem.setIsoformAccession(isoformAccession);
		transformedItem.setIsoformIdentifier(isoformIdentifier);

		transformedItem.setGenePrimaryIdentifier(genePrimaryIdentifier);
		transformedItem.setGeneSymbol(geneSymbol);
		transformedItem.setTairIdentifier(tairIdentifier);
		transformedItem.setPubMedId(pubMedId);
		transformedItem.setDataSet(dataSet);
      	
        log.info("Converting (" + item + ") into (" + transformedItem + ")");
        
        ValidationStats.uniprotTremblProcessedRecordCount.increment();
        
        log.info("Current Statistics:"
				+ ValidationStats.getUniprotTremblStatistics());

        return transformedItem;
    }

    
private String trimAttributeValue(final String attribute){
    	
    	String result = attribute;
    	
    	if (!StringUtils.isBlank( attribute)){
    		result.trim();
    	}else
    	{
    		result = null;
    	}
    	
    	return result;
    	
    }


private String generateIsoformIdentifier(final String primaryIdentifier,
		final String primaryAccession) {

	StringBuilder builder = new StringBuilder(primaryIdentifier);

	if (!StringUtils.isBlank(primaryAccession)) {
		String[] token = primaryAccession.split("\\-");

		if (token.length == 2) {
			builder.append("-").append(token[1]);
		}
	}
	return builder.toString();
}
}
