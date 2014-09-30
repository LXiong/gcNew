package cn.voicet.gc.form;

public class SubTelForm {
	private String[] subteltxt;

	private int telid;
	private String telnum;

	private String pageflag;
	public String getPageflag() {
		return pageflag;
	}
	public void setPageflag(String pageflag) {
		this.pageflag = pageflag;
	}
	
	public String[] getSubteltxt() {
		return subteltxt;
	}
	public void setSubteltxt(String[] subteltxt) {
	this.subteltxt = subteltxt;
	}
	public int getTelid() {
		return telid;
	}
	public void setTelid(int telid) {
		this.telid = telid;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
}
