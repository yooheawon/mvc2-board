package service;

import java.util.List;
import java.util.Map;

import vo.Board;

public interface IBoardService {
	// 반환값 : List<Board>, int lastPage
	Map<String, Object> getBoardList(int rowPerPage, int currentPage) throws Exception;
	List<Board> selectBoardOne(int boardNo) throws Exception;
}
