package org.araport.validation.dao;

import javax.sql.DataSource;

public interface GeneralDao {

	public void executeSQL(String sql);
	
	public void setDataSource(DataSource datasource);
	
}
