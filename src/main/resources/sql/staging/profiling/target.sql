select * from protein pt
where pt.primaryidentifier = '14310_ARATH'

select * from protein pt
where pt.primaryidentifier = 'P48347-1'


select * from protein pt
where pt.primaryidentifier = '14310_ARATH-2'

select * from protein pt
where pt.primaryidentifier like '%14310_ARATH%'

select pt.primaryidentifier, d.name from 
protein pt
join 
bioentity b
on pt.id = b.id
join 
bioentitiesdatasets bd
on bd.bioentities = b.id
join dataset d
on d.id = bd.datasets
where pt.isuniprotcanonical = true


select pt.primaryidentifier from 
protein pt
join 
bioentity b
on pt.id = b.id
join 
bioentitiesdatasets bd
on bd.bioentities = b.id
join dataset d
on d.id = bd.datasets
where pt.isuniprotcanonical = true and d.name = 'Swiss-Prot data set';

select count(*) from 
protein pt
join 
bioentity b
on pt.id = b.id
join 
bioentitiesdatasets bd
on bd.bioentities = b.id
join dataset d
on d.id = bd.datasets
where pt.isuniprotcanonical = true and d.name = 'Swiss-Prot data set';

--
--Swiss-Prot data set
--TrEMBL data set

SELECT 
	distinct
    pt.primary_identifier,
    regexp_split_to_table(pt.isoform_identifier, E' ') AS isoform_id
FROM staging.uniprot_swissprot pt


SELECT
count(	g.primaryidentifier)
FROM
	gene g JOIN bioEntitiespublications bp
		ON
		bp.bioentities = g.id JOIN publication p
		ON
		p.id = bp.publications
		where p.title is null
		
		
		
	SELECT
	distinct
	pt.primaryidentifier,
		p.pubmedid
FROM
	 protein pt
	 join
	 bioEntitiespublications bp
		ON
		bp.bioentities = pt.id JOIN publication p
		ON
		p.id = bp.publications
		
	select count(*) from (	
		SELECT
	distinct
	pt.primaryidentifier,
		p.pubmedid
FROM
	 protein pt
	 join
	 bioEntitiespublications bp
		ON
		bp.bioentities = pt.id JOIN publication p
		ON
		p.id = bp.publications ) V