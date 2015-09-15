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
	
create view staging.uniprot_pub as 
SELECT
distinct pt.primary_identifier,
regexp_split_to_table(
pt.pubmed_id,
E' ')
	AS pubmed_id
FROM
	staging.uniprot_input_source pt;
	
	
CREATE VIEW staging.target_protein_pub as
SELECT
	distinct
	pt.primaryidentifier,
	p.pubmedid,
	d.name entity_dataset_name
FROM
protein pt
JOIN bioEntitiespublications bp
ON
bp.bioentities = pt.id 
JOIN publication p
ON
	p.id = bp.publications
join 
bioentitiesdatasets bd
on bd.bioentities = pt.id
join dataset d
on d.id = bd.datasets;


CREATE VIEW staging.target_gene_pub as
SELECT
	distinct
	g.primaryidentifier,
	p.pubmedid,
	d.name entity_dataset_name
FROM
gene g
JOIN bioEntitiespublications bp
ON
bp.bioentities = g.id 
JOIN publication p
ON
	p.id = bp.publications
join 
bioentitiesdatasets bd
on bd.bioentities = g.id
join dataset d
on d.id = bd.datasets;


CREATE VIEW staging.target_transcripts_pub as
SELECT
	distinct
	m.primaryidentifier,
	p.pubmedid,
	d.name entity_dataset_name
FROM
mrna m
JOIN bioEntitiespublications bp
ON
bp.bioentities = m.id 
JOIN publication p
ON
	p.id = bp.publications
join 
bioentitiesdatasets bd
on bd.bioentities = m.id
join dataset d
on d.id = bd.datasets;


CREATE VIEW staging.target_protein as
select 
distinct
pt.primaryidentifier, d.name entity_dataset_name  from 
protein pt
join 
bioentity b
on pt.id = b.id
join 
bioentitiesdatasets bd
on bd.bioentities = b.id
join dataset d
on d.id = bd.datasets;

CREATE VIEW staging.target_protein_uniprot as
select 
distinct
pt.primaryidentifier, d.name entity_dataset_name  from 
protein pt
join 
bioentity b
on pt.id = b.id
join 
bioentitiesdatasets bd
on bd.bioentities = b.id
join dataset d
on d.id = bd.datasets
where 
d.name in ('TrEMBL data set', 'Swiss-Prot data set');

create view staging.not_existing_uniprot_records as
select
distinct 
V.primary_identifier,
s.gene_primary_identifier,
s.dataset
from(
select 
distinct
s.primary_identifier
from 
staging.uniprot_input_source s
except
select 
distinct
s.primaryidentifier
from
staging.target_protein_uniprot s ) V
join
staging.uniprot_raw_source s
on s.primary_identifier = V.primary_identifier;


create view staging.not_existing_uniprot_pub_records as
select 
distinct
V.primary_identifier,
V.pubmed_id,
s.gene_primary_identifier,
s.dataset
from (
select
distinct
s.primary_identifier,
s.pubmed_id
from staging.uniprot_pub s
except
select
distinct
s.primaryidentifier,
s.pubmedid
from
staging.target_protein_pub s ) V
join
staging.uniprot_raw_source s
on s.primary_identifier = V.primary_identifier;
