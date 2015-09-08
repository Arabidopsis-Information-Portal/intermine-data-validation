package org.araport.validation.writer;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.araport.validation.DataSourceInfrastructureConfiguration;
import org.araport.validation.domain.Person;
import org.araport.validation.utils.FileUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component("person_writer")
@Import({ DataSourceInfrastructureConfiguration.class })
public class PersonWriter {

	private static final Log log = LogFactory
			.getLog(PersonWriter.class);
	
	
	private static final String SQL_PATH = "/sql/staging/writer/person_insert.sql";
	private static final String SQL = FileUtils
			.getSqlFileContents(SQL_PATH);
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public ItemWriter<Person> personWriter() {

		log.info("Person Writer.");
		JdbcBatchItemWriter<Person> itemWriter = new JdbcBatchItemWriter<Person>();
		itemWriter.setSql(SQL);
		itemWriter.setDataSource(dataSource);
		itemWriter
				.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		return itemWriter;

	}
}
