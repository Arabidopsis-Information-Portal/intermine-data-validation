package org.araport.validation.domain;

public class NCBIPubMedGene {

	private String taxonId;
	private String ncbiGeneId;
	private String pubMedId;
	
	public NCBIPubMedGene(){
		
	}
		
	public NCBIPubMedGene(String taxonId, String ncbiGeneId, String pubMedId) {
		super();
		this.taxonId = taxonId;
		this.ncbiGeneId = ncbiGeneId;
		this.pubMedId = pubMedId;
	}

	public String getTaxonId() {
		return taxonId;
	}
	public void setTaxonId(String taxonId) {
		this.taxonId = taxonId;
	}
	public String getNcbiGeneId() {
		return ncbiGeneId;
	}
	public void setNcbiGeneId(String ncbiGeneId) {
		this.ncbiGeneId = ncbiGeneId;
	}
	public String getPubMedId() {
		return pubMedId;
	}
	public void setPubMedId(String pubMedId) {
		this.pubMedId = pubMedId;
	}

	@Override
	public String toString() {
		return "NCBIPubMedGene [taxonId=" + taxonId + ", ncbiGeneId="
				+ ncbiGeneId + ", pubMedId=" + pubMedId + "]";
	}
	
	
	
}
