INSERT INTO staging.ncbi_gene_info_lookup
	(taxon_id, gene_id, symbol, locus_agi_id)
VALUES 
	(:taxonId, :ncbiGeneId, :symbol, :locusAGIId);
	
