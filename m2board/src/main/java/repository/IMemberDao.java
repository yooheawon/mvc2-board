package repository;

import java.sql.Connection;
import java.sql.SQLException;

import vo.Member;

public interface IMemberDao {
	// 매개값 : id, pw
	// 반환값 : 세션에 저장될 Member의 정보 일부
	Member selectMemberLogin(Connection conn, Member paramMember) throws SQLException;
	int deleteMember(Connection conn, Member paramMember) throws Exception;
	int insertMember(Connection conn, Member paramMember) throws Exception;
	int updateMember(Connection conn, Member member) throws Exception;
	
}
