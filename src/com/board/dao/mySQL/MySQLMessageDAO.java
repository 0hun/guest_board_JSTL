package com.board.dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.dao.MessageDAO;
import com.board.db.util.JdbcUtil;
import com.board.dto.Member;
import com.board.dto.Message;

public class MySQLMessageDAO extends MessageDAO{

	@Override
	public void insertMessage(Connection conn, Message msg) throws SQLException {
		PreparedStatement pstmt = null;
		String sql="insert into message(guestName,password,message)"
				+"values(?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, msg.getGuestName());
		pstmt.setString(2, msg.getPassword());
		pstmt.setString(3, msg.getMessage());
		pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
	}

	@Override
	public List<Message> selectMessageList(Connection conn, int firstRow, int endRow)
			throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql="select * from memssage"
					+"order by id desc limit ?,?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, firstRow-1);
		pstmt.setInt(2, endRow-firstRow+1);
		
		rs = pstmt.executeQuery();
		
		List<Message> messageList = new ArrayList<Message>();
		while(rs.next()){
			Message msg= new Message();
			msg.setGuestName(rs.getString("guestName"));
			msg.setId(rs.getInt("id")+"");
			msg.setMessage(rs.getString("message"));
			msg.setPassword(rs.getString("password"));
			
			messageList.add(msg);
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
		return messageList;
	}
	
}
