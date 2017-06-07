package com.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dto.Member;
import com.board.service.MemberService;

@WebServlet("/join")
public class JoinMemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("joinForm.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="joinSuccess.jsp";
		String message=null;
		
		request.setCharacterEncoding("utf-8");
		
		Member member = new Member();
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));
		MemberService.getInstance().joinMember(member);
		
		message = "회원가입을 축하합니다.";
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
