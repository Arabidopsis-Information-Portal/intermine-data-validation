declare namespace un="http://uniprot.org/uniprot";
for $entry in doc("/work/temp/uniprot/3702_uniprot_sprot.xml")//un:entry
for $source in doc("/work/temp/uniprot/3702_uniprot_sprot.xml")//un:gene
for $pubmed in doc("/work/temp/uniprot/3702_uniprot_sprot.xml")//un:dbReference
return
<uniprot>{
<entry>
<protein>
</protein>
<gene>
<primaryIdentifier>
{data($source/un:name[@type='ordered locus'])}
</primaryIdentifier>
<symbol>
{data($source/un:name[@type='primary'])}
</symbol>
</gene>
</entry>
}
</uniprot>
