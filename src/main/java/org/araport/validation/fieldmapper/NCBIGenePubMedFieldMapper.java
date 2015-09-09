package org.araport.validation.fieldmapper;
import org.araport.validation.domain.NCBIGeneLookup;
import org.araport.validation.domain.NCBIPubMedGene;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class NCBIGenePubMedFieldMapper implements  FieldSetMapper<NCBIPubMedGene>{

	@Override
	public NCBIPubMedGene mapFieldSet(FieldSet fieldSet) throws BindException {
		NCBIPubMedGene item = new NCBIPubMedGene();
		
		item.setTaxonId(fieldSet.readString(0));
		item.setNcbiGeneId(fieldSet.readString(1));
		item.setPubMedId(fieldSet.readString(2));
		
		return item;
	}

}
