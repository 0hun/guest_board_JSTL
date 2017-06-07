package com.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.board.dao.MemberDAO;
import com.board.dao.MemberDAOProvider;
import com.board.db.loader.ConnectionProvider;
import com.board.db.util.JdbcUtil;
import com.board.dto.Member;
import com.board.dto.MemberListView;

//import core.log.exception.ServiceException;


public class MemberService {
	private static final int MEMBER_COUNT_PER_PAGE = 5;
	private static MemberService instance = new MemberService();

	private MemberService() {
	};

	public static MemberService getInstance() {
		return instance;
	}

	// 회원가입, 로그인, 로그아웃
	private Connection con = null;
	private MemberDAO memberDAO = MemberDAOProvider.getInstance()
			.getMemberDAO();

	// 회원가입
	public void joinMember(Member member) {
		try {
			con = ConnectionProvider.getInstance().getConnection();
			memberDAO.insertMember(con, member);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}
	}

	// 로그인
	public Member loginMember(String id, String pwd) {
		Member member = null;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			member = memberDAO.selectMember(con, id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}
		return member;

	}

	public Member selectMember(String id) {
		Member member = null;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			member = memberDAO.selectMember(con, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}
		return member;
	}

	/*
	 * 관리자 서비스 메소드
	 */
	public MemberListView selectMemberList(int pageNumber) throws ServiceException {

		int currentPageNumber = pageNumber;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			int memberTotalCount = memberDAO.selectCount(con);

			List<Member> memberList = null;
			int firstRow = 0;
			int endRow = 0;
			if (memberTotalCount > 0) {
				firstRow = (pageNumber - 1) * MEMBER_COUNT_PER_PAGE + 1;
				endRow = firstRow + MEMBER_COUNT_PER_PAGE - 1;
				memberList = memberDAO.selectMemberList(con, firstRow, endRow);
			} else {
				currentPageNumber = 0;
				memberList = Collections.emptyList();
			}
			return new MemberListView(memberList, memberTotalCount,
					currentPageNumber, MEMBER_COUNT_PER_PAGE, firstRow, endRow);
		} catch (SQLException e) {
			throw new ServiceException("회원 목록 구하기 실패 ", e);
		} finally {
			JdbcUtil.close(con);
		}
	}

	public void updateMember(Member member) throws SQLException {
		con = ConnectionProvider.getInstance().getConnection();
		memberDAO.updateMember(con, member);
		JdbcUtil.close(con);

	}

	public void deleteMember(String id) throws SQLException {
		con = ConnectionProvider.getInstance().getConnection();
		memberDAO.deleteMember(con, id);
		JdbcUtil.close(con);

	}

}
