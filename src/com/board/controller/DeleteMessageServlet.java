package com.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.service.DeleteMessageService;
import com.board.service.InvalidMessagePasswordException;
import com.board.service.MessageNotFoundException;
import com.board.service.ServiceException;

@WebServlet("/deleteMessage")
public class DeleteMessageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("confirmDeletion.jsp")
		.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
		request.setCharacterEncoding("utf-8");
				
		int message_id = Integer.parseInt(request.getParameter("messageId"));
		String password=request.getParameter("password");
		boolean invalidPassowrd=false;
		try {
			DeleteMessageService.getInstance().deleteMessage(message_id, password);
		} catch (ServiceException e) {		
			e.printStackTrace();
		} catch (InvalidMessagePasswordException e) {
			invalidPassowrd=true;
		} catch (MessageNotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("invalidPassowrd", invalidPassowrd);
		request.getRequestDispatcher("deleteMessage.jsp")
		.forward(request, response);		
	}	
}







