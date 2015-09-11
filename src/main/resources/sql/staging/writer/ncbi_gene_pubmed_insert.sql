INSERT INTO staging.ncbi_gene_pubmed
	(taxon_id, gene_id, pubmed_id)
VALUES 
	(:taxonId, :ncbiGeneId,:pubMedId);
