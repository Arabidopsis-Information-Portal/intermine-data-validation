package org.araport.validation;

import java.beans.PropertyVetoException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySources(value = { @PropertySource("classpath:/target.db.properties")
			})

public class DataSourceInfrastructureConfiguration implements
		InfrastructureConfiguration {

	@Autowired
	Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;

	
	@Bean(name = "targetDataSource", destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment
				.getProperty("target.batch.jdbc.driver"));
		dataSource.setUrl(environment.getProperty("target.batch.jdbc.url"));
		dataSource.setUsername(environment
				.getProperty("target.batch.jdbc.user"));
		dataSource.setPassword(environment
				.getProperty("target.batch.jdbc.password"));
		return dataSource;
	}
	
	@Bean(name = "dataSource", destroyMethod = "close")
	@Primary
	public DataSource poolingDataSource() throws PropertyVetoException {
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass(environment
					.getProperty("target.batch.jdbc.driver"));
			dataSource.setJdbcUrl(environment.getProperty("target.batch.jdbc.url"));
			dataSource.setUser(environment.getProperty("target.batch.jdbc.user"));
			dataSource.setPassword(environment
					.getProperty("target.batch.jdbc.password"));
			dataSource.setInitialPoolSize(5);
			dataSource.setAcquireIncrement(5);
			dataSource.setIdleConnectionTestPeriod(1);
			dataSource.setMaxPoolSize(20);
			dataSource.setMaxIdleTimeExcessConnections(20);
			dataSource.setMaxIdleTime(30);
			dataSource.setMaxConnectionAge(30);
			dataSource.setTestConnectionOnCheckout(true);
			dataSource.setTestConnectionOnCheckin(true);
			dataSource.setPreferredTestQuery("SELECT 1");
			dataSource.setAcquireRetryAttempts(0);
			dataSource.setConnectionCustomizerClassName("org.araport.validation.ConnectionPoolLogger");
			
			return dataSource;
	}

}
