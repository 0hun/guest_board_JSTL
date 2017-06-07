package com.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dto.MessageListView;
import com.board.service.GetMessageListService;
import com.board.service.ServiceException;


@WebServlet("/messageList")
public class MessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNumberStr = request.getParameter("page");
		int pageNumber = 1;
		if (pageNumberStr != null) {
			pageNumber = Integer.parseInt(pageNumberStr);
		}

		GetMessageListService messageListService = GetMessageListService.getInstance();
		
		MessageListView viewData=null;
		try {
			viewData = messageListService.getMessageList(pageNumber);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		if(viewData.getMessageList().isEmpty()){
			pageNumber--;
			if(pageNumber<=0){
				pageNumber=1;
			}
		}
			
			
			
		request.setAttribute("viewData",viewData);
		request.setAttribute("pageNumber",pageNumber);
		request.getRequestDispatcher("list.jsp").forward(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
