package org.araport.validation.tasklet;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.araport.validation.DataSourceInfrastructureConfiguration;
import org.araport.validation.dao.GeneralDao;
import org.araport.validation.dao.impl.GeneralDaoImpl;
import org.araport.validation.utils.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Import({ DataSourceInfrastructureConfiguration.class, GeneralDaoImpl.class })
public class StagingSchemaInitTasklet implements Tasklet {

	private static final Log log = LogFactory
			.getLog(StagingSchemaInitTasklet.class);

	private static final String SCHEMA_INIT_SQL_PATH = "/sql/staging/predeploy/init_staging_schema.sql";
	private static final String SCHEMA_INIT_SQL = FileUtils
			.getSqlFileContents(SCHEMA_INIT_SQL_PATH);

	@Autowired
	Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	DataSource dataSource;

	private GeneralDao generalDao;

	@Override
	public RepeatStatus execute(StepContribution step, ChunkContext context)
			throws Exception {

		log.info("Deploying Staging Schema SQL:" + SCHEMA_INIT_SQL);
		log.info("Deploying Staging Schema...");

		generalDao.executeSQL(SCHEMA_INIT_SQL);

		return RepeatStatus.FINISHED;

	}

	@PostConstruct
	public void setDao() {

		this.generalDao = new GeneralDaoImpl();
		this.generalDao.setDataSource(dataSource);

	}

}
