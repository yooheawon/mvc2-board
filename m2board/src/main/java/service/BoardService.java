package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import repository.BoardDao;
import repository.IBoardDao;
import vo.Board;

public class BoardService implements IBoardService {
	// 반환값 : List<Board>, int lastPage
	@Override
	public Map<String, Object> getBoardList(int rowPerPage, int currentPage) throws Exception {
		Map<String, Object> map = null;
		
		int beginRow = (currentPage-1)*rowPerPage;
		int lastPage = 0;
		
		// 메소드 생성
		IBoardDao boardDao = new BoardDao();
		
		Connection conn = null;
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			// list를 가진 map 객체
			map = boardDao.selectBoardListByPage(conn, rowPerPage, beginRow);
			
			// 마지막페이지
			lastPage = boardDao.selectBoardCnt(conn, rowPerPage);
			
			lastPage = lastPage/rowPerPage;
			
			if(lastPage % rowPerPage !=0) {
				lastPage++;
			}
			
			map.put("lastPage", lastPage);
			System.out.println("lastPage");
			// 디버깅
			if (map != null) {
				System.out.println("map에 페이지 입력");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}finally {
			if (conn != null) {
				conn.close();
			}
		}
		return map;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// board 상세보기
	@Override
	public List<Board> selectBoardOne(int boardNo) throws Exception {
		Connection conn = null;
		List<Board> list = null; 
		
		try {
			conn = DBUtil.getConnection();
			list = new BoardDao().selectBoardOne(conn, boardNo);
			conn.setAutoCommit(false);
			if (list == null) {
				System.out.println("list null");
				throw new Exception();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return list;
	}

}
