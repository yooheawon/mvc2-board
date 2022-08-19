package service;

import vo.Member;

public interface IMemberService {
	// 로그인
	Member getMemberLogin(Member paramMember);
	// 탈퇴
	int removeMember(Member paramMember) throws Exception;
	// 회원가입
	int addMember(Member paramMember) throws Exception;
	// 회원수정
	int motifyMember(Member paramMember) throws Exception;
}
