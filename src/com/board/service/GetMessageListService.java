package com.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.board.dao.MessageDAO;
import com.board.dao.MessageDAOProvider;
import com.board.db.loader.ConnectionProvider;
import com.board.db.util.JdbcUtil;
import com.board.dto.Message;
import com.board.dto.MessageListView;

public class GetMessageListService {
	private static GetMessageListService instance =
			new GetMessageListService();

	public static GetMessageListService getInstance() {
		return instance;
	}

	private GetMessageListService() {
	}

	private static final int MESSAGE_COUNT_PER_PAGE = 5;

	public MessageListView getMessageList(int pageNumber)
			throws ServiceException {
		Connection conn = null;
		int currentPageNumber = pageNumber;
		try {
			conn = ConnectionProvider.getInstance().getConnection();
			MessageDAO messageDao =
					MessageDAOProvider.getInstance().getMessageDAO();

			int messageTotalCount = messageDao.selectCount(conn);

			List<Message> messageList = null;
			int firstRow = 0;
			int endRow = 0;
			if (messageTotalCount > 0) {
				firstRow =
						(pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;
				messageList =
						messageDao.selectMessageList(conn, firstRow, endRow);
			} else {
				currentPageNumber = 0;
				messageList = Collections.emptyList();
			}
			return new MessageListView(messageList,
					messageTotalCount, currentPageNumber,
					MESSAGE_COUNT_PER_PAGE, firstRow, endRow);
		} catch (SQLException e) {
			throw new ServiceException("메세지 목록 구하기 실패: "
					+ e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
