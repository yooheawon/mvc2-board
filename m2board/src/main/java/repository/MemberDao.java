package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

public class MemberDao implements IMemberDao {
////////////////////////////////////////////////////////////////////////////////////////////
	// 로그인
	@Override
	public Member selectMemberLogin(Connection conn, Member paramMember) throws SQLException {
		Member m = null;
		String sql = "SELECT * FROM member WHERE member_id = ? and member_pw=PASSWORD(?)";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			rs = stmt.executeQuery();
			//디버깅
			System.out.println("rs : "+rs );
			
			if (rs.next()) {
				m = new Member();
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPw(rs.getString("member_pw"));
				m.setMemberName(rs.getString("member_name"));
				m.setMemberAddr(rs.getString("member_addr"));
				m.setMemberDetailAddr(rs.getString("member_detailAddr"));
				m.setCreateDate(rs.getString("create_date"));
				m.setUpdateDate(rs.getString("update_date"));
			}
		} finally {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
		}
		//디버깅
		System.out.println("MemberDao : " + m);
		return m;
	}
////////////////////////////////////////////////////////////////////////////////////////////
	// 탈퇴
	@Override
	public int deleteMember(Connection conn, Member paramMember) throws Exception {
		// 리턴할 변수
		int deleteMember = 0;
		// 탈퇴 쿼리
		String sql ="DELETE FROM member WHERE member_id = ? and member_pw=PASSWORD(?)";
		PreparedStatement stmt =null;
		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			deleteMember = stmt.executeUpdate();
			System.out.println("deleteMemberDao : " + deleteMember);
			return deleteMember;
	}
			
////////////////////////////////////////////////////////////////////////////////////////////
	// 회원가입
@Override
	public int insertMember(Connection conn, Member paramMember) throws Exception {
		// 리턴값 
		int insertMember = 0;
		String sql = "INSERT INTO member \r\n"
				+ "(member_id, member_pw, member_name, member_addr, member_detailaddr, create_date, update_date) \r\n"
				+ "VALUES (?,PASSWORD(?),?,?,?,NOW(),NOW())";
		PreparedStatement stmt  = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			stmt.setString(3, paramMember.getMemberName());
			stmt.setString(4, paramMember.getMemberAddr());
			stmt.setString(5, paramMember.getMemberDetailAddr());
			insertMember = stmt.executeUpdate();
			// 디버깅
			System.out.println("insertMember : "+insertMember);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return insertMember;
	}
////////////////////////////////////////////////////////////////////////////////////////////
	// 회원정보 수정
	@Override
	public int updateMember(Connection conn, Member member) throws Exception {
		int updateMember = 0;
		String sql = "UPDATE member set member_name=?, member_addr=? , member_detailaddr=?, update_date=NOW() where member_id=? and member_pw=password(?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberName() );
			stmt.setString(2, member.getMemberAddr());
			stmt.setString(3, member.getMemberDetailAddr());
			stmt.setString(4, member.getMemberId() );
			stmt.setString(5, member.getMemberPw() );
			updateMember = stmt.executeUpdate();
			System.out.println("updateMember : "+updateMember);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return updateMember;
	}
}
