package com.board.db.loader;

import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DBCPInit extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			String drivers = config.getInitParameter("jdbcDriver");
			StringTokenizer st = new StringTokenizer(drivers, ",");
			while (st.hasMoreTokens()) {
				String jdbcDriver = st.nextToken();
				Class.forName(jdbcDriver); //jdbc Driver
			}
			
			Class.forName("org.apache.commons.dbcp.PoolingDriver"); //DBCP Driver
			System.out.println("DBMS 로딩완료...!");
		} catch (Exception e) {
			throw new ServletException(e);
		}
		

	}

}
