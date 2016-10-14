package com.charles.dal.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import javax.sql.DataSource;


public class ProcedureCallImple implements ProcedureCall {
	
	private DataSource dataSource;
	
	
	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public void reflesh() throws Exception {
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			CallableStatement proc = null;
			proc = conn.prepareCall("{call PROCEDURE_REFLESH()}");
			proc.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

}
