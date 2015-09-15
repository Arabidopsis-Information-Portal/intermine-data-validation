INSERT INTO staging.uniprot_swissprot
	(primary_identifier, primary_accession, uniprot_accession, protein_synonym, uniprot_name, canonical_isoform_accession, isoform_accession, isoform_identifier ,gene_primary_identifier, gene_symbol, tair_identifier, pubmed_id, dataset)
VALUES 
	(:primaryIdentifier, :primaryAccession, :uniprotAccession, :proteinSynonym, :uniprotName, :canonicalIsoformAccession, :isoformAccession, :isoformIdentifier, :genePrimaryIdentifier, :geneSymbol, :tairIdentifier, :pubMedId, :dataSet);
	
