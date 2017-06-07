package com.board.dao;

import com.board.dao.mySQL.MySQLMessageDAO;
import com.board.dto.oracle.OracleMessageDAO;

public class MessageDAOProvider {
	
	private static MessageDAOProvider messageDAOProvider= new MessageDAOProvider();
	
	private MessageDAOProvider(){
	
	}
	
	public static MessageDAOProvider getInstance(){
		return messageDAOProvider;
	}
	
	
	private MessageDAO messageDAO = null;
	private String dbms=null;
	
	void setDbms(String dbms){
		this.dbms=dbms;
	}
	
	public MessageDAO getMessageDAO(){
		if(this.dbms.equals("oracle")){
			messageDAO = new OracleMessageDAO();
		}else if(this.dbms.equals("mysql")){
			messageDAO = new MySQLMessageDAO();
		}else if(this.dbms.equals("mssql")){
//			messageDAO = new MsSqlMessageDAO();
		}else{
			
		}
		return messageDAO;
	}
}
