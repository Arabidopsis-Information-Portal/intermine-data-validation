package org.araport.validation.domain;

public class NCBIGeneLookup {
	
	private String taxonId;
	private String ncbiGeneId;
	private String locusAGIId;
	
	
	public NCBIGeneLookup(){
		
	}
	 
	public NCBIGeneLookup(String taxonId, String ncbiGeneId, String locusAGIId) {
		super();
		this.taxonId = taxonId;
		this.ncbiGeneId = ncbiGeneId;
		this.locusAGIId = locusAGIId;
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
	public String getLocusAGIId() {
		return locusAGIId;
	}
	public void setLocusAGIId(String locusAGIId) {
		this.locusAGIId = locusAGIId;
	}

	@Override
	public String toString() {
		return "NCBIGeneLookup [taxonId=" + taxonId + ", ncbiGeneId="
				+ ncbiGeneId + ", locusAGIId=" + locusAGIId + "]";
	}
	

}
