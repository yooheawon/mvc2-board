package vo;

public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberAddr;
	private String memberDetailAddr;
	private String createDate;
	private String updateDate;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public String getMemberDetailAddr() {
		return memberDetailAddr;
	}
	public void setMemberDetailAddr(String memberDetailAddr) {
		this.memberDetailAddr = memberDetailAddr;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberAddr=" + memberAddr + ", memberDetailAddr=" + memberDetailAddr + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}

}