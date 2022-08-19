package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Board;

public class BoardDao implements IBoardDao {

	// board 목록
	@Override
	public Map<String, Object> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws Exception {
		// 리턴값
		Map<String, Object> map = new HashMap<String, Object>();
		List<Board> list = new ArrayList<Board>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT board_no boardNo,title, writer, contents, create_date createDate, board_read boardRead  FROM board ORDER BY board_no DESC LIMIT ?,?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs=stmt.executeQuery();
			System.out.println("dao rs : " + rs);
			
			while(rs.next()) {
				Board b = new Board();
				b.setBoardNo(rs.getInt("boardNo"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setContents(rs.getString("contents"));
				b.setCreateDate(rs.getString("createDate"));
				b.setBoardRead(rs.getInt("boardRead"));
				
				
				list.add(b);
				// 디버깅
				System.out.println(list);
			}
			map.put("list", list);
		}finally {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
		}
		return map;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 라스트 페이지를 구하기 위한 총 카우트 구하기 

	@Override
	public int selectBoardCnt(Connection conn, int rowPerPage) throws Exception {
		// 리턴값
		int count = 0;
		String sql = "SELECT COUNT(*) FROM board";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			count = rs.getInt("COUNT(*)");
		}
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		
		return count;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 상품 자세히 보기
	@Override
	public List<Board> selectBoardOne(Connection conn, int boardNo) throws Exception {
		List<Board> list = null;
		Board board = null;
		String sql ="SELECT * FROM board WHERE board_no =?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				board = new Board();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContents(rs.getString("contents"));
				board.setCreateDate(rs.getString("create_date"));
				board.setBoardRead(rs.getInt("board_read"));
				
				list.add(board);
			}
		}finally {
			if (rs != null) { rs.close(); } 
			if (stmt != null) { stmt.close(); }
		}
		return list;
	}


}
