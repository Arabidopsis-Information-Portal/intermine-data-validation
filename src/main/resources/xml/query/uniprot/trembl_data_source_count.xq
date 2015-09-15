declare namespace un="http://uniprot.org/uniprot";
for $entry in doc("/usr/local/projdata/0611/projects/AIP/ThaleMineData/uniprot/uniprot/current/3702_uniprot_trembl.xml")//un:uniprot
return
<count1>
{count($entry//un:entry)}
</count1>
