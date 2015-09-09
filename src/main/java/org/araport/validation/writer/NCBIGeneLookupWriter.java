package org.araport.validation.writer;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.araport.validation.DataSourceInfrastructureConfiguration;
import org.araport.validation.domain.NCBIGeneLookup;
import org.araport.validation.domain.Person;
import org.araport.validation.domain.TAIRLocusPublication;
import org.araport.validation.stat.ValidationStats;
import org.araport.validation.utils.FileUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component("ncbi_gene_lookup_writer")
@Import({ DataSourceInfrastructureConfiguration.class })
public class NCBIGeneLookupWriter {

	
	private static final Log log = LogFactory
			.getLog(PersonWriter.class);
	
	
	private static final String SQL_PATH = "/sql/staging/writer/ncbi_gene_info_lookup_insert.sql";
	private static final String SQL = FileUtils
			.getSqlFileContents(SQL_PATH);
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public ItemWriter<NCBIGeneLookup> ncbiGeneLookupWriter() {

		log.info("NCBI Gene Lookup Writer has started.");
		JdbcBatchItemWriter<NCBIGeneLookup> itemWriter = new JdbcBatchItemWriter<NCBIGeneLookup>();
		itemWriter.setSql(SQL);
		itemWriter.setDataSource(dataSource);
		itemWriter
				.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<NCBIGeneLookup>());
		
		return itemWriter;

	}
	
}
