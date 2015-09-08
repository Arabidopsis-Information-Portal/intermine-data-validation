package org.araport.validation.processor;

import org.araport.validation.domain.Person;
import org.araport.validation.domain.TAIRLocusPublication;
import org.araport.validation.stat.ValidationStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class TAIRPublicationItemProcessor implements ItemProcessor<TAIRLocusPublication, TAIRLocusPublication> {

    private static final Logger log = LoggerFactory.getLogger(TAIRPublicationItemProcessor.class);

    @Override
    public TAIRLocusPublication process(final TAIRLocusPublication item) throws Exception {
        final String locusId = item.getLocusAGIId().toUpperCase();
        final String pubMedId = item.getPubMedId().toUpperCase();
        final String tairPubObjectId = item.getTairObjectId();
        final String year = item.getYear();

        final TAIRLocusPublication transformedItem = new TAIRLocusPublication(locusId, pubMedId, tairPubObjectId, year);

        log.info("Converting (" + item + ") into (" + transformedItem + ")");
        
        ValidationStats.tairPubLocusRecordCount.increment();

        return transformedItem;
    }

}
