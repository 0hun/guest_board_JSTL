package com.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dto.Message;
import com.board.service.ServiceException;
import com.board.service.WriteMessageService;

@WebServlet("/writeMessage")
public class WriteMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("writeMessageForm.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
	
		Message message = new Message();
		
		message.setPassword(request.getParameter("password"));
		message.setMessage(request.getParameter("message"));
		message.setGuestName(request.getParameter("guestName"));
		message.setId(request.getParameter("messageId"));
		
		
		WriteMessageService writeService = WriteMessageService.getInstance();
		
		try {
			writeService.write(message);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("messageList");
	}

}







