package org.araport.validation.fieldmapper;
import org.araport.validation.domain.NCBIGeneLookup;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class NCBIGeneLookupFieldMapper implements  FieldSetMapper<NCBIGeneLookup>{

	@Override
	public NCBIGeneLookup mapFieldSet(FieldSet fieldSet) throws BindException {
		NCBIGeneLookup item = new NCBIGeneLookup();
		
		item.setTaxonId(fieldSet.readString(0));
		item.setNcbiGeneId(fieldSet.readString(1));
		item.setSymbol(fieldSet.readString(2));
		item.setLocusAGIId(fieldSet.readString(3));
		
		return item;
	}

}
