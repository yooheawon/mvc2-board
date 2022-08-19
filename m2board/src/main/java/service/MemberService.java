package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DBUtil;
import repository.MemberDao;
import vo.Member;

public class MemberService implements IMemberService {
	// 로그인
	@Override
	public Member getMemberLogin(Member paramMember) {
		Connection conn = null;
		Member m = null;
		try {
			conn = DBUtil.getConnection();
			// 자동커밋 방지
			conn.setAutoCommit(false);
			
			// DAO 호출
			MemberDao memberDao = new MemberDao();
			m = memberDao.selectMemberLogin(conn, paramMember);
			System.out.println("m : "+m);
			
			// 디버깅
			if (m != null) {
				System.out.println("로그인 성공");
			} else {
				System.out.println("로그인 실패");
				throw new Exception();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println(m);
		return m;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 회원탈퇴
	@Override
	public int removeMember(Member paramMember) throws Exception {
		int removeMember = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 커밋방지
			conn.setAutoCommit(false);
			
			MemberDao memberDao = new MemberDao();
			removeMember = memberDao.deleteMember(conn, paramMember);
			if (removeMember != 0) {
				System.out.println("삭제");
				conn.commit();
			}else {
				System.out.println("삭제실패");
				// 삭제 실패 예외발생!
				throw new Exception();
			}
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
		return removeMember;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 회원가입
	@Override
	public int addMember(Member paramMember) throws Exception {
		int addMember = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			MemberDao memberDao = new MemberDao();
			addMember = memberDao.insertMember(conn, paramMember);
			// 디버깅
			if (addMember == 0) {
				System.out.println("go away~");
				throw new Exception();
			}else {
				System.out.println("wellcome");
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
			if ( conn != null) {
				conn.close();
			}
		}
		return addMember;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 회원수정
@Override
public int motifyMember(Member paramMember) throws Exception {
	int motifyMember = 0;
	Connection conn = null;
	
	try {
		conn =DBUtil.getConnection();
		conn.setAutoCommit(false); // 오토커밋 방지
		MemberDao memberDao = new MemberDao();
		motifyMember = memberDao.updateMember(conn, paramMember);
		
		if (motifyMember != 0) {
			//디버깅
			System.out.println(" motifyMember 회원수정 성공 : "+motifyMember);
			conn.commit();
		}else {
			// 디버깅
			System.out.println(" motifyMember 회원수정 실패 : "+motifyMember);
			throw new Exception();
		}
	} catch (Exception e) {
		e.printStackTrace();
		try {
			conn.rollback();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}finally {
		if (conn != null) {
			conn.close();
		}
	}

	return motifyMember;
}
}
