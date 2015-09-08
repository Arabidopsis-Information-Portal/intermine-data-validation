package org.araport.validation.domain;

public class TAIRLocusPublication {
	
	private String locusAGIId;
	private String pubMedId;
	private String tairObjectId;
	private String year;
	
	public TAIRLocusPublication(){
		
	}
	
	public TAIRLocusPublication(String locusAGIId, String pubMedId
			) {
		super();
		this.locusAGIId = locusAGIId;
		this.pubMedId = pubMedId;
		
	}
	
	public TAIRLocusPublication(String locusAGIId, String pubMedId,
			String tairObjectId, String year) {
		super();
		this.locusAGIId = locusAGIId;
		this.pubMedId = pubMedId;
		this.tairObjectId = tairObjectId;
		this.year = year;
	}
	
	public String getLocusAGIId() {
		return locusAGIId;
	}
	public void setLocusAGIId(String locusAGIId) {
		this.locusAGIId = locusAGIId;
	}
	public String getPubMedId() {
		return pubMedId;
	}
	public void setPubMedId(String pubMedId) {
		this.pubMedId = pubMedId;
	}
	public String getTairObjectId() {
		return tairObjectId;
	}
	public void setTairObjectId(String tairObjectId) {
		this.tairObjectId = tairObjectId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "TAIRLocusPublication [locusAGIId=" + locusAGIId + ", pubMedId="
				+ pubMedId + ", tairObjectId=" + tairObjectId + ", year="
				+ year + "]";
	}
	
	
}
