package com.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dto.Message;
import com.board.service.UpdateMessageService;

@WebServlet("/updateMessage")
public class UpdateMessageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("updateMessageForm.jsp");
		String messageId=request.getParameter("messageId");
		int message_id=Integer.parseInt(messageId);
		Message message=null;
		try {
			message=
	UpdateMessageService.getInstance().getMessage(message_id);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("updateMessageForm.jsp")
		.forward(request, response);		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Message message=new Message();
		message.setGuestName(request.getParameter("guestName"));
		message.setId((request.getParameter("messageId")));
		message.setMessage(request.getParameter("message"));
		message.setPassword(request.getParameter("password"));
		
		try {
			UpdateMessageService.getInstance()
			.updateMessage(message);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		response.sendRedirect("messageList?page="+request.getParameter("page"));
		
	}

}










