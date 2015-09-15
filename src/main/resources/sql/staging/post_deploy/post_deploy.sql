CREATE INDEX locus_agi_id_tair_locus_pub_idx
ON staging.tair_locus_pub(locus_agi_id);

CREATE INDEX tair_pub_object_id_tair_locus_pub_idx
ON staging.tair_locus_pub(tair_pub_object_id);

CREATE INDEX pubmed_id_tair_locus_pub_idx
ON staging.tair_locus_pub(pubmed_id);

CREATE INDEX primary_identifier_uniprot_swissprot_idx
ON staging.uniprot_swissprot(primary_identifier);

CREATE INDEX pubmed_id_uniprot_swissprot_idx
ON staging.uniprot_swissprot(pubmed_id);

CREATE INDEX primary_identifier_uniprot_trembl_idx
ON staging.uniprot_trembl(primary_identifier);

CREATE INDEX pubmed_id_uniprot_trembl_idx
ON staging.uniprot_trembl(pubmed_id);

CREATE INDEX dataset_uniprot_swissprot_idx
ON staging.uniprot_swissprot(dataset);

CREATE INDEX dataset_uniprot_trembl_idx
ON staging.uniprot_trembl(dataset);