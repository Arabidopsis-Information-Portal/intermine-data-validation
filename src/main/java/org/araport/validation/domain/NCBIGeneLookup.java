package org.araport.validation.domain;

public class NCBIGeneLookup {

	private String taxonId;
	private String ncbiGeneId;
	private String symbol;
	private String locusAGIId;

	public NCBIGeneLookup() {

	}

	public NCBIGeneLookup(String taxonId, String ncbiGeneId, String locusAGIId) {
		super();
		this.taxonId = taxonId;
		this.ncbiGeneId = ncbiGeneId;
		this.locusAGIId = locusAGIId;
	}

	public NCBIGeneLookup(String taxonId, String ncbiGeneId, String symbol,
			String locusAGIId) {
		super();
		this.taxonId = taxonId;
		this.ncbiGeneId = ncbiGeneId;
		this.symbol = symbol;
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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "NCBIGeneLookup [taxonId=" + taxonId + ", ncbiGeneId="
				+ ncbiGeneId + ", locusAGIId=" + locusAGIId + "]";
	}

}
