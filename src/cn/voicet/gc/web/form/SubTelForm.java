package cn.voicet.gc.web.form;

public class SubTelForm {
	private String[] subteltxt;

	//delete
	private String cts="cts100";
	private int telid;

	public String[] getSubteltxt() {
		return subteltxt;
	}
	public void setSubteltxt(String[] subteltxt) {
		this.subteltxt = subteltxt;
	}
	public String getCts() {
		return cts;
	}
	public void setCts(String cts) {
		this.cts = cts;
	}
	public int getTelid() {
		return telid;
	}
	public void setTelid(int telid) {
		this.telid = telid;
	}
}
