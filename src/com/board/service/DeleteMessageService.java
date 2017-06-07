package com.board.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.board.dao.MessageDAO;
import com.board.dao.MessageDAOProvider;
import com.board.db.loader.ConnectionProvider;
import com.board.db.util.JdbcUtil;
import com.board.dto.Message;


public class DeleteMessageService {

	private static DeleteMessageService instance = new DeleteMessageService();

	public static DeleteMessageService getInstance() {
		return instance;
	}

	private DeleteMessageService() {
	}

	public void deleteMessage(int messageId, String password)
			throws ServiceException, InvalidMessagePasswordException,
			MessageNotFoundException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getInstance().getConnection();
			conn.setAutoCommit(false);

			MessageDAO messageDao = MessageDAOProvider.getInstance()
					.getMessageDAO();
			Message message = messageDao.selectMessage(conn, messageId+"");
			if (message == null) {
				throw new MessageNotFoundException("메세지가 없습니다.:" + messageId);
			}
			if (!message.hasPassword()) {
				throw new InvalidMessagePasswordException();
			}
			if (!message.getPassword().equals(password)) {
				throw new InvalidMessagePasswordException();
			}
			messageDao.deleteMessage(conn, messageId+"");

			conn.commit();
		} catch (SQLException ex) {
			JdbcUtil.rollback(conn);
			throw new ServiceException(
					"삭제 처리 중 에러가 발생했습니다.:" + ex.getMessage(), ex);
		} catch (InvalidMessagePasswordException ex) {
			JdbcUtil.rollback(conn);
			throw ex;
		} catch (MessageNotFoundException ex) {
			JdbcUtil.rollback(conn);
			throw ex;
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
				}
				JdbcUtil.close(conn);
			}
		}
	}
}
