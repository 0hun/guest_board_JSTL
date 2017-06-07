package com.board.dto.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.dao.MemberDAO;
import com.board.db.util.JdbcUtil;
import com.board.dto.Member;


public class OracleMemberDAO extends MemberDAO{

	@Override
	public List selectMemberList(Connection conn, int firstRow, int endRow)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		String sql = "select * from ("
	               + "select rownum rnum, id, pwd from ("
	                  + "select * from member m order by m.id desc"
	                  + ") where rownum <= ?"
	               + ") where rnum >= ?";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, endRow);
		pstmt.setInt(2, firstRow);
		rs=pstmt.executeQuery();
		List<Member> memberList = new ArrayList<Member>();
		while(rs.next()){
			Member member = new Member();
			member.setId(rs.getString("id"));
			member.setPwd(rs.getString("pwd"));
			memberList.add(member);
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
		return memberList;
	}

}
