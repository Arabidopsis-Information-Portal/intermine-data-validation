INSERT INTO staging.uniprot_trembl
	(primary_identifier, primary_accession, uniprot_accession, protein_synonym, gene_primary_identifier, gene_symbol, tair_identifier, pubmed_id, dataset)
VALUES 
	(:primaryIdentifier, :primaryAccession, :uniprotAccession, :proteinSynonym, :genePrimaryIdentifier, :geneSymbol, :tairIdentifier, :pubMedId, :dataSet);
	
