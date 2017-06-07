package com.board.dao;

import com.board.dao.mySQL.MySQLMemberDAO;
import com.board.dto.oracle.OracleMemberDAO;

public class MemberDAOProvider {
	
	private static MemberDAOProvider memberDAOProvider= new MemberDAOProvider();
	
	private MemberDAOProvider(){
	
	}
	
	public static MemberDAOProvider getInstance(){
		return memberDAOProvider;
	}
	
	
	private MemberDAO memberDAO = null;
	private String dbms=null;
	
	void setDbms(String dbms){
		this.dbms=dbms;
	}
	
	public MemberDAO getMemberDAO(){
		if(this.dbms.equals("oracle")){
			memberDAO = new OracleMemberDAO();
		}else if(this.dbms.equals("mysql")){
			memberDAO = new MySQLMemberDAO();
		}else if(this.dbms.equals("mssql")){
//			memberDAO = new MsSqlMemberDAO();
		}else{
			
		}
		return memberDAO;
	}
}
