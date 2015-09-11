package org.araport.validation.xml;

import org.araport.validation.domain.UniprotEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

@Component("uniprot_entry_unmarchaller")
public class UniprotUnMarshaller {

	@Bean
	public Unmarshaller uniprotEntryMarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(UniprotEntry.class);
		
		return marshaller;
	}
	
}
