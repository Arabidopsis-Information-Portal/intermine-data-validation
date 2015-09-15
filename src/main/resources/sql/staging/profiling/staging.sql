select * from staging.uniprot_swissprot pt
where pt.isoform_accession is null;


select count(distinct pt.primary_identifier) from staging.uniprot_swissprot pt
where pt.isoform_accession is null;


select count(*) from staging.uniprot_swissprot pt
where pt.isoform_accession is not null;

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
pt.canonical_isoform_accession,
pt.isoform_accession
from staging.uniprot_swissprot pt
where pt.isoform_accession is null

select count(*) from staging.swiss_uniprot_input_source s
where s.dataset = 'Swiss-Prot';


select * from staging.uniprot_swissprot pt
where pt.isoform_accession is not null and pt.isoform_identifier is not null;


WITH source as (
SELECT 
	distinct
    pt.primary_identifier,
    regexp_split_to_table(pt.isoform_identifier, E' ') AS isoform_id
FROM staging.uniprot_swissprot pt)

select
distinct
pt.primary_identifier,
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
s.isoform_id
from staging.uniprot_swissprot pt
join 
source s
on s.primary_identifier = pt.primary_identifier
where pt.isoform_accession is not null


WITH source as (
SELECT 
	distinct
    pt.primary_identifier,
    regexp_split_to_table(pt.isoform_accession, E' ') AS isoform_id,
    FROM staging.uniprot_swissprot pt)
,

source_stg2 as(

select
distinct
pt.primary_identifier,
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
position('-' in s.isoform_id),
substring(s.isoform_id from position('-' in s.isoform_id) +1) isoform_suffix
from staging.uniprot_swissprot pt
join 
source s
on s.primary_identifier = pt.primary_identifier
where pt.isoform_accession is not null and pt.primary_identifier = '14311_ARATH')

select 
distinct
case 
	when 
		(s.isoform_suffix is not null || length(s.isoform_suffix) <> 0)
		then
		s.primary_identifier || s.isoform_suffix
	else
		s.primary_identifier
end as primary_identifier,
s.primary_identifier as org,
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
from source_stg2 s


SELECT COUNT(*) FROM staging.swiss_prot_uniprot_canonical

SELECT COUNT(*) FROM staging.swiss_uniprot_isoform

select count(*) from staging.swiss_uniprot_input_source;
