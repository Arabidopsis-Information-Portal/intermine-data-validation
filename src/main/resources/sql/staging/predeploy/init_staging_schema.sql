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
	uniprot_name varchar(255),
	canonical_isoform_accession varchar(255),
	isoform_accession varchar(255),
	isoform_identifier varchar(255),
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
	uniprot_name varchar(255),
	canonical_isoform_accession varchar(255),
	isoform_accession varchar(255),
	isoform_identifier varchar(255),
	protein_synonym varchar(255),
	gene_primary_identifier varchar(255),
	gene_symbol varchar(255),
	tair_identifier varchar(255),
	pubmed_id text,
	dataset varchar(255)
	);

create view staging.uniprot_raw_source as
select
pt.primary_identifier,
pt.primary_accession,
pt.uniprot_accession,
pt.uniprot_name,
pt.canonical_isoform_accession,
pt.isoform_accession,
pt.isoform_identifier,
pt.protein_synonym,
pt.gene_primary_identifier,
pt.gene_symbol,
pt.tair_identifier,
pt.pubmed_id,
pt.dataset
from 
staging.uniprot_swissprot pt
UNION
select
pt.primary_identifier,
pt.primary_accession,
pt.uniprot_accession,
pt.uniprot_name,
pt.canonical_isoform_accession,
pt.isoform_accession,
pt.isoform_identifier,
pt.protein_synonym,
pt.gene_primary_identifier,
pt.gene_symbol,
pt.tair_identifier,
pt.pubmed_id,
pt.dataset
from 
staging.uniprot_trembl pt;
	
CREATE
	view staging.uniprot_canonical as (
SELECT
	distinct pt.primary_identifier,
	pt.primary_accession,
	pt.uniprot_accession,
	pt.uniprot_name,
	pt.protein_synonym,
	pt.gene_primary_identifier,
	pt.gene_symbol,
	pt.tair_identifier,
	pt.pubmed_id,
	pt.canonical_isoform_accession,
	pt.isoform_accession,
	pt.isoform_identifier,
	pt.dataset
FROM
	staging.uniprot_raw_source pt
)
	;
	
	
	CREATE VIEW staging.uniprot_isoform AS
	WITH source as (
SELECT
	distinct pt.primary_identifier,
	regexp_split_to_table(
	pt.isoform_accession,
	E' ')
	AS isoform_id
FROM
	staging.uniprot_raw_source pt)
	,
	source_stg2 as(
SELECT
	distinct pt.primary_identifier,
	pt.primary_accession,
	pt.uniprot_accession,
	pt.uniprot_name,
	pt.protein_synonym,
	pt.gene_primary_identifier,
	pt.gene_symbol,
	pt.tair_identifier,
	pt.pubmed_id,
	pt.canonical_isoform_accession,
	pt.isoform_accession,
	pt.isoform_identifier,
	s.isoform_id,
	position(
	'-' IN s.isoform_id)
	,
	substring(
	s.isoform_id
FROM
	position(
	'-' IN s.isoform_id)
	+1)
	isoform_suffix,
	pt.dataset
FROM
	staging.uniprot_raw_source pt JOIN source s
		ON
		s.primary_identifier = pt.primary_identifier
WHERE
	pt.isoform_accession IS NOT null)
SELECT
	distinct case 
		when (s.isoform_suffix IS NOT null or length(s.isoform_suffix) 	<> 0)
			then cast(s.primary_identifier as text) || '-' || cast (s.isoform_suffix as text)
		else 
			cast(s.primary_identifier as text)
	end as primary_identifier,
	s.primary_identifier as parent_uniprot_primary_identifier,
	s.isoform_id as primary_accession,
	s.uniprot_accession,
	s.uniprot_name,
	s.protein_synonym,
	s.gene_primary_identifier,
	s.gene_symbol,
	s.tair_identifier,
	s.pubmed_id,
	s.canonical_isoform_accession,
	s.isoform_accession,
	s.isoform_identifier,
	s.isoform_id,
	s.dataset
FROM
	source_stg2 s;
	
create view staging.uniprot_input_source as 
select 
pt.primary_identifier,
pt.primary_accession,
pt.uniprot_accession,
pt.uniprot_name,
pt.protein_synonym,
pt.gene_primary_identifier,
pt.gene_symbol,
pt.tair_identifier,
pt.pubmed_id,
pt.dataset,
true as is_canonical
from 
staging.uniprot_canonical pt
UNION
select 
pt.primary_identifier,
pt.primary_accession,
pt.uniprot_accession,
pt.uniprot_name,
pt.protein_synonym,
pt.gene_primary_identifier,
pt.gene_symbol,
pt.tair_identifier,
pt.pubmed_id,
pt.dataset,
false as is_canonical
from 
staging.uniprot_isoform pt;
	


