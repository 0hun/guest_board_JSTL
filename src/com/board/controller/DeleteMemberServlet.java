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

@WebServlet("/deleteMember")
public class DeleteMemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("confirmAdmin.jsp").forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		Member admin = MemberService.getInstance().selectMember("admin");
		boolean invalidPassowrd = false;
		if (password.equals(admin.getPwd())) {
			try {
				MemberService.getInstance().deleteMember(memberId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			invalidPassowrd=true;
		}
		request.setAttribute("invalidPassowrd", invalidPassowrd);
		request.getRequestDispatcher("deleteMember.jsp").forward(request,
				response);
	}
}
