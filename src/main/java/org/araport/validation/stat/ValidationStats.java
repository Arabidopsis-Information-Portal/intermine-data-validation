package org.araport.validation.stat;

public class ValidationStats {
	
	public static Counter tairPubLocusRecordCount = new Counter();
	
	
	public static String getStatistics() {
		StringBuilder result = new StringBuilder("Statistics: "
				+ "\n");

		result.append("Total TAIR Locus to Pub Record Count: "
				+  tairPubLocusRecordCount.getValue());
				

		if (result != null) {
			return result.toString();
		} else {
			return null;
		}
	}

}
