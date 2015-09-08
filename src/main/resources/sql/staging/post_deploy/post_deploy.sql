CREATE INDEX locus_agi_id_tair_locus_pub_idx
ON staging.tair_locus_pub(locus_agi_id);

CREATE INDEX tair_pub_object_id_tair_locus_pub_idx
ON staging.tair_locus_pub(tair_pub_object_id);

CREATE INDEX pubmed_id_tair_locus_pub_idx
ON staging.tair_locus_pub(pubmed_id);