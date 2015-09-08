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