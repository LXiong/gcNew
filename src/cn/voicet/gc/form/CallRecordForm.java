package cn.voicet.gc.form;

public class CallRecordForm {
	private String cts;	//统计服务器别名
	
	private String sdt;	//统计开始日期
	private String edt;	//统计结束日期

	//calltxt size:6
	private String[] calltxt={"","","2","","",""};
	
	public String getCts() {
		return cts;
	}
	public void setCts(String cts) {
		this.cts = cts;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEdt() {
		return edt;
	}
	public void setEdt(String edt) {
		this.edt = edt;
	}
	public String[] getCalltxt() {
		return calltxt;
	}
	public void setCalltxt(String[] calltxt) {
		this.calltxt = calltxt;
	}
}
