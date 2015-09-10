declare namespace un="http://uniprot.org/uniprot";
for $source in doc("/work/temp/uniprot/3702_uniprot_sprot.xml")//un:gene
for $pubmed in doc("/work/temp/uniprot/3702_uniprot_sprot.xml")//un:dbReference
return
<uniprot>{
<name1>
{data($source/un:name[@type='ordered locus'])}
</name1>
}
</uniprot>