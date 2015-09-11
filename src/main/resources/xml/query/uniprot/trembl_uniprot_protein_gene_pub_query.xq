declare namespace un="http://uniprot.org/uniprot";
for $entry in doc("/usr/local/projdata/0611/projects/AIP/ThaleMineData/uniprot/uniprot/current/3702_uniprot_trembl.xml")//un:entry
return
<uniprot_entry>
<primaryAccession>
{data($entry/un:accession[position()=1])}
</primaryAccession>
<uniprotAccession>
{data($entry/un:accession[position()=1])}
</uniprotAccession>
<proteinSynonym>
{data($entry/un:accession[position()=2])}
</proteinSynonym>
<proteinPrimaryIdentifier>
{data($entry/un:name)}
</proteinPrimaryIdentifier>
<genePrimaryIdentifier>
{data($entry/un:gene/un:name[@type='ordered locus'])}
</genePrimaryIdentifier>
<geneSymbol>
{data($entry/un:gene/un:name[@type='primary'])}
</geneSymbol>
<pubMedId>
{data($entry//un:dbReference[@type='PubMed']/@id)}
</pubMedId>
<organism>
{data($entry//un:organism/un:dbReference[@type='NCBI Taxonomy']/@id)}
</organism>
<tairIdentifier>
{data($entry//un:source/un:dbReference[@type='TAIR']/@id)}
</tairIdentifier>
<dataset>
Trembl
</dataset>
</uniprot_entry>
