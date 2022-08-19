package vo;

import java.sql.Connection;
import java.util.List;

public class Board {
	private int boardNo;
	private String title;
	private String writer;
	private String contents;
	private String createDate;
	private int boardRead;
	private int nice;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getBoardRead() {
		return boardRead;
	}
	public void setBoardRead(int boardRead) {
		this.boardRead = boardRead;
	}
	public int getNice() {
		return nice;
	}
	public void setNice(int nice) {
		this.nice = nice;
	}
	public Board(int boardNo, String title, String writer, String contents, String createDate, int read, int nice) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.createDate = createDate;
		this.boardRead = boardRead;
		this.nice = nice;
	}
	
	public Board() {
		super();
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", title=" + title + ", writer=" + writer + ", contents=" + contents
				+ ", createDate=" + createDate + ", read=" + boardRead + ", nice=" + nice + "]";
	}
	
}
