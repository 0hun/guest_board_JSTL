package com.board.dto.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.dao.MessageDAO;
import com.board.db.util.JdbcUtil;
import com.board.dto.Message;

public class OracleMessageDAO extends MessageDAO{

	@Override
	public void insertMessage(Connection conn, Message msg) throws SQLException {
		PreparedStatement pstmt = null;
		String sql ="insert into message(id,guestName,message, password)"
				+ " values(msgId_seq.nextval,?,?,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, msg.getGuestName());
		pstmt.setString(2, msg.getMessage());
		pstmt.setString(3, msg.getPassword());
		
		pstmt.executeUpdate();
	}

	@Override
	public List selectMessageList(Connection conn, int firstRow, int endRow)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select id, guestName,password,message from("
				+ " select rownum rnum,id,guestName,password,message from("
				+ " select * from message m order by m.id desc "
				+ ") where rownum <=? "
				+ ") where rnum >=? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, endRow);
		pstmt.setInt(2, firstRow);
		rs = pstmt.executeQuery();
		List<Message> msgList = new ArrayList<Message>();
		while(rs.next()){
			Message msg = new Message();
			msg.setGuestName(rs.getString("guestName"));
			msg.setId(rs.getInt("id")+"");
			msg.setMessage(rs.getString("message"));
			msg.setPassword(rs.getString("password"));
			msgList.add(msg);
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
		
		
		return msgList;
	}

}
