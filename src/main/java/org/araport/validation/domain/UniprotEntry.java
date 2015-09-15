package org.araport.validation.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessOrder;  
import javax.xml.bind.annotation.XmlAccessorOrder;  

@XmlRootElement(name = "uniprot_entry")
public class UniprotEntry {
	
	private String primaryIdentifier;
	private String primaryAccession;
	private String uniprotAccession;
	private String uniprotName;
	private String canonicalIsoformAccession;
	private String isoformAccession;
	private String isoformIdentifier;
	private String proteinSynonym;
	private String genePrimaryIdentifier;
	private String geneSymbol;
	private String tairIdentifier;
	private String pubMedId;
	private String dataSet;
	
	
	public UniprotEntry(){
		
	}
	
	@XmlElement(name = "proteinPrimaryIdentifier")
	public String getPrimaryIdentifier() {
		return primaryIdentifier;
	}
	public void setPrimaryIdentifier(String primaryIdentifier) {
		this.primaryIdentifier = primaryIdentifier;
	}
	
	@XmlElement(name = "primaryAccession")
	public String getPrimaryAccession() {
		return primaryAccession;
	}
	public void setPrimaryAccession(String primaryAccession) {
		this.primaryAccession = primaryAccession;
	}
	
	@XmlElement(name = "uniprotAccession")
	public String getUniprotAccession() {
		return uniprotAccession;
	}
	public void setUniprotAccession(String uniprotAccession) {
		this.uniprotAccession = uniprotAccession;
	}
	
	@XmlElement(name = "proteinSynonym")
	public String getProteinSynonym() {
		return proteinSynonym;
	}
	
	public void setProteinSynonym(String proteinSynonym) {
		this.proteinSynonym = proteinSynonym;
	}
	
	@XmlElement(name = "genePrimaryIdentifier")
		public String getGenePrimaryIdentifier() {
		return genePrimaryIdentifier;
	}
	
	public void setGenePrimaryIdentifier(String genePrimaryIdentifier) {
		this.genePrimaryIdentifier = genePrimaryIdentifier;
	}
	
	@XmlElement(name = "geneSymbol")
	public String getGeneSymbol() {
		return geneSymbol;
	}
	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}
	
	@XmlElement(name = "tairIdentifier")
	public String getTairIdentifier() {
		return tairIdentifier;
	}
	public void setTairIdentifier(String tairIdentifier) {
		this.tairIdentifier = tairIdentifier;
	}
	
	@XmlElement(name = "pubMedId")
	public String getPubMedId() {
		return pubMedId;
	}
	
	public void setPubMedId(String pubMedId) {
		this.pubMedId = pubMedId;
	}
	
	@XmlElement(name = "dataset")
	public String getDataSet() {
		return dataSet;
	}
	public void setDataSet(String dataSet) {
		this.dataSet = dataSet;
	}
	
	@XmlElement(name = "uniprotName")
	public String getUniprotName() {
		return uniprotName;
	}
	
	public void setUniprotName(String uniprotName) {
		this.uniprotName = uniprotName;
	}

	@XmlElement(name = "canonicalIsoform")
	public String getCanonicalIsoformAccession() {
		return canonicalIsoformAccession;
	}

	public void setCanonicalIsoformAccession(String canonicalIsoformAccession) {
		this.canonicalIsoformAccession = canonicalIsoformAccession;
	}

	@XmlElement(name = "isoform")
	public String getIsoformAccession() {
		return isoformAccession;
	}

	public void setIsoformAccession(String isoformAccession) {
		this.isoformAccession = isoformAccession;
	}
	
	public String getIsoformIdentifier() {
		return isoformIdentifier;
	}

	public void setIsoformIdentifier(String isoformIdentifier) {
		this.isoformIdentifier = isoformIdentifier;
	}

	@Override
	public String toString() {
		return "UniprotEntry [primaryIdentifier=" + primaryIdentifier
				+ ", primaryAccession=" + primaryAccession
				+ ", uniprotAccession=" + uniprotAccession + ", uniprotName="
				+ uniprotName + ", canonicalIsoformAccession="
				+ canonicalIsoformAccession + ", isoformAccession="
				+ isoformAccession + ", isoformIdentifier=" + isoformIdentifier
				+ ", proteinSynonym=" + proteinSynonym
				+ ", genePrimaryIdentifier=" + genePrimaryIdentifier
				+ ", geneSymbol=" + geneSymbol + ", tairIdentifier="
				+ tairIdentifier + ", pubMedId=" + pubMedId + ", dataSet="
				+ dataSet + "]";
	}

	
}
