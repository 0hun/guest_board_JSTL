package com.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dto.Member;
import com.board.service.MemberService;

@WebServlet("/updateMember")
public class UpdateMemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.sendRedirect("updateMessageForm.jsp");
		String memberId = request.getParameter("memberId");
		Member member = null;

		member = MemberService.getInstance().selectMember(memberId);
		request.setAttribute("member", member);
		request.getRequestDispatcher("updateMemberForm.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		Member member = new Member();
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));	
		try {
			MemberService.getInstance().updateMember(member);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("memberList?page="+request.getParameter("page"));

	}

}
