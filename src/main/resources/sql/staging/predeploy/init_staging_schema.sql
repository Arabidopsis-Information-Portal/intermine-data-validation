DROP SCHEMA IF EXISTS staging CASCADE;

CREATE SCHEMA staging;

CREATE TABLE staging.person (
first_name varchar(255),
last_name varchar(255)
);

CREATE TABLE staging.tair_locus_pub(
locus_agi_id varchar(255),
tair_pub_object_id varchar(255),
pubmed_id varchar(255),
year varchar(10)
);

CREATE TABLE staging.ncbi_gene_info_lookup(
taxon_id varchar(255),
gene_id varchar(255),
symbol varchar(255),
locus_agi_id varchar(255)
);

CREATE TABLE staging.ncbi_gene_pubmed(
taxon_id varchar(255),
gene_id varchar(255),
pubmed_id varchar(255)
);

CREATE TABLE staging.uniprot_swissprot (
	primary_identifier varchar(255),
	primary_accession varchar(255),
	uniprot_accession varchar(255),
	protein_synonym varchar(255),
	gene_primary_identifier varchar(255),
	gene_symbol varchar(255),
	tair_identifier varchar(255),
	pubmed_id text,
	dataset varchar(255)
	);
	
CREATE TABLE staging.uniprot_trembl (
	primary_identifier varchar(255),
	primary_accession varchar(255),
	uniprot_accession varchar(255),
	protein_synonym varchar(255),
	gene_primary_identifier varchar(255),
	gene_symbol varchar(255),
	tair_identifier varchar(255),
	pubmed_id text,
	dataset varchar(255)
	);