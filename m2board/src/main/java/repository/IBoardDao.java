package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import vo.Board;

public interface IBoardDao {
	Map<String, Object> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws Exception ;
	int selectBoardCnt(Connection conn, int rowPerPage) throws Exception;
	List<Board> selectBoardOne(Connection conn, int boardNo) throws Exception;
}
