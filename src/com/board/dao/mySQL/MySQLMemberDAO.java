package com.board.dao.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.dao.MemberDAO;
import com.board.db.util.JdbcUtil;
import com.board.dto.Member;

public class MySQLMemberDAO extends MemberDAO{

	@Override
	public List<Member> selectMemberList(Connection conn, int firstRow, int endRow)
			throws SQLException {
		
		List<Member> memberList = new ArrayList<Member>();
		String sql="select * from member limit ?,?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, firstRow-1);
		pstmt.setInt(2, endRow - firstRow+1);
		
		rs =  pstmt.executeQuery();
		
		while(rs.next()){
			Member member = new Member();
			member.setId(rs.getString("id"));
//			member.setName(rs.getString("name"));\
			member.setPwd(rs.getString("pwd"));
			
			memberList.add(member);
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
		return memberList;
	}

}
