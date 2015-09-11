package org.araport.validation.stat;

public class ValidationStats {
	
	public static Counter tairPubLocusRecordCount = new Counter();
	
	public static Counter ncbiGeneLookupInputRecordCount = new Counter();
	
	public static Counter ncbiGeneLookupProcessedRecordCount = new Counter();
	
	public static Counter ncbiGeneLookupSkippedRecordCount = new Counter();
	
	public static Counter ncbiGeneNotTargetLookupTaxonRecordCount = new Counter();
	
	public static Counter ncbiGenePubMedInputRecordCount = new Counter();
	
	public static Counter ncbiGenePubMedInputProcessedRecordCount = new Counter();
	
	public static Counter ncbiGenePubMedInputSkippedRecordCount = new Counter();
	
	public static Counter ncbiGenePubMedGeneNotTargetLookupTaxonRecordCount = new Counter();
	
	public static Counter uniprotSwissProtInputRecordCount = new Counter();
	
	public static Counter uniprotSwissProtProcessedRecordCount = new Counter();
	
	public static Counter uniprotSwissProtSkippedRecordCount = new Counter();
	
	public static Counter uniprotTremblInputRecordCount = new Counter();
	
	public static Counter uniprotTremblProcessedRecordCount = new Counter();
	
	public static Counter uniprotTremblSkippedRecordCount = new Counter();
	
	
	public static String getStatistics() {
		StringBuilder result = new StringBuilder("Overall Statistics: "
				+ "\n");

		result.append("Total TAIR Locus to Pub Record Count: "
				+  tairPubLocusRecordCount.getValue() + "\n");
		
		result.append(getNCBIGeneLookupStatistics());
				
		return result.toString();
	}
	
	public static String getNCBIGeneLookupStatistics() {
		
		StringBuilder result = new StringBuilder("Statistics: "
				+ "\n");
		
		result.append("Total NCBI Gene Lookup Record Input Count: "
				+   ncbiGeneLookupInputRecordCount.getValue() + "\n");
		
		result.append("Total NCBI Gene Lookup Not Target Lookup Taxon Count (Skipped): "
				+   ncbiGeneNotTargetLookupTaxonRecordCount.getValue() + "\n");
		
		result.append("Total Gene Lookup NCBI Processed Record Count: "
				+   ncbiGeneLookupProcessedRecordCount.getValue() + "\n");
		
		result.append("Total Gene Lookup NCBI Skipped/Error Record Count: "
				+   ncbiGeneLookupSkippedRecordCount.getValue() + "\n");
		
		return result.toString();
		
	}
	
public static String getNCBIGenePubMedStatistics() {
		
		StringBuilder result = new StringBuilder("Statistics: "
				+ "\n");
		
		result.append("Total NCBI Gene PubMed Record Input Count: "
				+   ncbiGenePubMedInputRecordCount.getValue() + "\n");
		
		result.append("Total NCBI Gene PubMed  Not Target Lookup Taxon Count (Skipped): "
				+   ncbiGenePubMedGeneNotTargetLookupTaxonRecordCount.getValue() + "\n");
		
		result.append("Total NCBI Gene PubMed  Processed Record Count: "
				+   ncbiGenePubMedInputProcessedRecordCount.getValue() + "\n");
		
		result.append("Total NCBI Gene PubMed Skipped/Error Record Count: "
				+   ncbiGenePubMedInputSkippedRecordCount.getValue() + "\n");
		
		return result.toString();
		
	}


	public static String getUniprotSwissProtStatistics() {

		StringBuilder result = new StringBuilder("Statistics: " + "\n");

		result.append("Total UniprotSwissProt Record Input Count: "
				+ uniprotSwissProtInputRecordCount.getValue() + "\n");

		result.append("Total UniprotSwissProt  Processed Record Count: "
				+ uniprotSwissProtProcessedRecordCount.getValue() + "\n");

		result.append("Total UniprotSwissProt Skipped/Error Record Count: "
				+ uniprotSwissProtSkippedRecordCount.getValue() + "\n");

		return result.toString();

	}
	
	
	public static String getUniprotTremblStatistics() {
		
		StringBuilder result = new StringBuilder("Statistics: "
				+ "\n");
		
		result.append("Total UniprotTrembl Record Input Count: "
				+   uniprotTremblInputRecordCount.getValue() + "\n");
		
		result.append("Total UniprotTrembl  Processed Record Count: "
				+   uniprotTremblProcessedRecordCount.getValue() + "\n");
		
		result.append("Total UniprotTrembl Skipped/Error Record Count: "
				+   uniprotTremblSkippedRecordCount.getValue() + "\n");
		
		return result.toString();
		
	}
}
