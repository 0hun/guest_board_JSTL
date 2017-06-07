package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.board.db.util.JdbcUtil;
import com.board.dto.Member;
import com.board.dto.Message;

public abstract class MessageDAO {
	public int selectCount(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		stmt =conn.createStatement();
		rs = stmt.executeQuery("select count(*) from message");
		rs.next();
		int result = rs.getInt(1);
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
		return result;
		
	}
	public void updateMessage(Connection conn,Message msg) throws SQLException{
		PreparedStatement pstmt=null;
		String sql="update message "
				+ "set guestName=?,"
				+ "password=? ,"
				+ "message =? "
				+ "where id=?";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,msg.getGuestName());
		pstmt.setString(2,msg.getPassword());
		pstmt.setString(3,msg.getMessage());
		pstmt.setInt(4,Integer.parseInt(msg.getId()));
		pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		
	}
	public void deleteMessage(Connection conn,String messageId) throws SQLException{
		Statement stmt=null;
		String sql="delete from message where id="+messageId;
		stmt=conn.createStatement();
		stmt.executeUpdate(sql);
		JdbcUtil.close(stmt);
	
	}
	public Message selectMessage(Connection conn,String messageId) throws SQLException{
		String sql="select * from message where id=?";
		PreparedStatement pstmt =null;
		ResultSet rs = null;

		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(messageId));
		rs=pstmt.executeQuery();
		
		Message msg = null;
		if(rs.next()){
			msg=new Message();
			msg.setGuestName(rs.getString("guestName"));
			msg.setId(rs.getInt("id")+"");
			msg.setMessage(rs.getString("message"));
			msg.setPassword(rs.getString("password"));
		}
		
		
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		return msg;
	}

	
	
	
	public abstract void insertMessage(Connection conn,Message msg) throws SQLException;
	public abstract List selectMessageList(Connection conn,int firstRow,int endRow) throws SQLException;
	

}
