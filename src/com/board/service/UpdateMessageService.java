package com.board.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.board.dao.MessageDAO;
import com.board.dao.MessageDAOProvider;
import com.board.db.loader.ConnectionProvider;
import com.board.dto.Message;

public class UpdateMessageService {
	private static UpdateMessageService instance=
			new UpdateMessageService();
	private UpdateMessageService(){}
	public static UpdateMessageService getInstance(){
		return instance;
	}
	
	
	public Message getMessage(int messageId) throws SQLException{
		MessageDAO messageDao=
				MessageDAOProvider.getInstance().getMessageDAO();
		
		Connection conn= ConnectionProvider.getInstance().getConnection();				
		Message message=messageDao.selectMessage(conn, messageId+"");
		return message;
	}
	
	public void updateMessage(Message message) throws SQLException{
		MessageDAO messageDao=
				MessageDAOProvider.getInstance().getMessageDAO();
		Connection conn = ConnectionProvider.getInstance().getConnection();
		messageDao.updateMessage(conn, message);
	}
}










