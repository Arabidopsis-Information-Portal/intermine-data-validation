CREATE VIEW staging.swiss_uniprot_isoform AS
	WITH source as (
SELECT
	distinct pt.primary_identifier,
	regexp_split_to_table(
	pt.isoform_accession,
	E' ')
	AS isoform_id
FROM
	staging.uniprot_swissprot pt)
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
	isoform_suffix
FROM
	staging.uniprot_swissprot pt JOIN source s
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
	s.isoform_id
FROM
	source_stg2 s
	
	

