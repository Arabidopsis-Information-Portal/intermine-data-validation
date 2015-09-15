declare namespace un="http://uniprot.org/uniprot";
for $entry in doc("/usr/local/projdata/0611/projects/AIP/ThaleMineData/uniprot/uniprot/current/3702_uniprot_sprot.xml")//un:entry
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
<uniprotName>
{data($entry/un:name)}
</uniprotName>
<canonicalIsoform>
{data($entry//un:isoform[un:sequence[@type='displayed']]/un:id)}
</canonicalIsoform>
<isoform>
{data($entry//un:isoform[un:sequence[@type='described']]/un:id)}
</isoform>
<genePrimaryIdentifier>
{data($entry/un:gene/un:name[@type='ordered locus'])}
</genePrimaryIdentifier>
<geneSymbol>
{data($entry/un:gene/un:name[@type='primary'])}
</geneSymbol>
<pubMedId>
{data($entry//un:citation/un:dbReference[@type='PubMed']/@id)}
</pubMedId>
<organism>
{data($entry//un:organism/un:dbReference[@type='NCBI Taxonomy']/@id)}
</organism>
<tairIdentifier>
{data($entry//un:source/un:dbReference[@type='TAIR']/@id)}
</tairIdentifier>
<dataset>
{data($entry/@dataset)}
</dataset>
</uniprot_entry>