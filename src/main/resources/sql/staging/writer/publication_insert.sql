INSERT INTO staging.tair_locus_pub
	(locus_agi_id, tair_pub_object_id, pubmed_id, year)
VALUES 
	(:locusAGIId, :pubMedId, :tairObjectId, :year);
