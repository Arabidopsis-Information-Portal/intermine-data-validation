package org.araport.validation;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ConnectionCustomizer;

public class ConnectionPoolLogger implements ConnectionCustomizer{

	private static final Logger log = Logger
			.getLogger(ConnectionPoolLogger.class);
	
	private int activeConnections = 0;
    private int acquiredConnections = 0;

    @Override
	public void onAcquire(Connection c, String pdsIdt)
			throws Exception {
    	
    	log.info("onAcquire: Connection acquired from database : " + c
                + " [" + pdsIdt + "]");
        acquiredConnections++;
        log.info("onAcquire: Total Open Connections in Pool : " + acquiredConnections);
		
	}

	@Override
	public void onDestroy(Connection c, String pdsIdt)
			throws Exception {
		
		log.info("onDestroy: Connection closed with database : " + c + " ["
                + pdsIdt + "]");
        acquiredConnections--;
        log.info("onDestroy: Total Open Connections in Pool : " + acquiredConnections);
		
	}

	@Override
	public void onCheckOut(Connection c, String pdsIdt)
			throws Exception {
		log.info("onCheckOut: Connection from pool provide to application : "
                + c + " [" + pdsIdt + "]");
        activeConnections++;
      
        log.info("onCheckOut: Total Active Connections in Pool : " + activeConnections);
		
	}

	@Override
	public void onCheckIn(Connection c, String pdsIdt)
			throws Exception {
		
		log.info("onCheckIn: Connection returned to pool from application : "
                + c + " [" + pdsIdt + "]");
        activeConnections--;
        log.info("onCheckIn: Total Active Connections in Pool : " + activeConnections);
		
	}

}
