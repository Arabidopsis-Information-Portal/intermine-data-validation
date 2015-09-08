package org.araport.validation;

import javax.sql.DataSource;

public interface InfrastructureConfiguration {

		public abstract DataSource dataSource();
}
