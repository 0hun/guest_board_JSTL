package com.board.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.board.dao.MessageDAO;
import com.board.dao.MessageDAOProvider;
import com.board.db.loader.ConnectionProvider;
import com.board.db.util.JdbcUtil;
import com.board.dto.Message;

public class WriteMessageService {
	private static WriteMessageService instance =
			new WriteMessageService();

	public static WriteMessageService getInstance() {
		return instance;
	}

	private WriteMessageService() {
	}

	public void write(Message message) throws ServiceException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getInstance().getConnection();
			MessageDAO messageDao =
					MessageDAOProvider.getInstance().getMessageDAO();
						
			messageDao.insertMessage(conn, message);
			
			
		} catch (SQLException e) {
			throw new ServiceException(
					"메세지 등록 실패: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
