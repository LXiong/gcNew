package cn.voicet.gc.form;

public class BlackForm {
	
	private int bid;
	private String telnum;
	private String peeknum;
	private String noteinfo;

	private String pageflag;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getTelnum() {
		if(null != telnum)
			return telnum;
		else
			return "";
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getPeeknum() {
		return peeknum;
	}
	public void setPeeknum(String peeknum) {
		this.peeknum = peeknum;
	}
	public String getNoteinfo() {
		return noteinfo;
	}
	public void setNoteinfo(String noteinfo) {
		this.noteinfo = noteinfo;
	}
	public String getPageflag() {
		return pageflag;
	}
	public void setPageflag(String pageflag) {
		this.pageflag = pageflag;
	}
}
