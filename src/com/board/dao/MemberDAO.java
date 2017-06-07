package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.board.db.util.JdbcUtil;
import com.board.dto.Member;

public abstract class MemberDAO {
	public int selectCount(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		stmt =conn.createStatement();
		rs = stmt.executeQuery("select count(*) from member");
		rs.next();
		int result = rs.getInt(1);
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
		return result;
		
	}
	public void updateMember(Connection conn,Member member) throws SQLException{
		PreparedStatement pstmt=null;
		String sql="update member "
				+ "set pwd=? "
				+ "where id=?";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,member.getPwd());
		pstmt.setString(2,member.getId());
		pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		
	}
	public void deleteMember(Connection conn,String id) throws SQLException{
		Statement stmt=null;
		String sql="delete from member where id='"+id+"'";
		stmt=conn.createStatement();
		stmt.executeUpdate(sql);
		JdbcUtil.close(stmt);
	
	}
	public Member selectMember(Connection conn,String id) throws SQLException{
		String sql="select * from member where id='"+id+"'";
		Statement stmt =null;
		ResultSet rs = null;
		stmt=conn.createStatement();
		rs = stmt.executeQuery(sql);
		Member member = null;
		if(rs.next()){
			member = new Member();
			member.setId(rs.getString("id"));
			member.setPwd(rs.getString("pwd"));
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
		return member;
	}

	
	
	
	public void insertMember(Connection conn,Member member) throws SQLException{
		PreparedStatement pstmt=null;
		String sql="insert into member(id,pwd) values(?,?) ";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,member.getId());
		pstmt.setString(2,member.getPwd());
		pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
	}
	public abstract List selectMemberList(Connection conn,int firstRow,int endRow) throws SQLException;
	

}
