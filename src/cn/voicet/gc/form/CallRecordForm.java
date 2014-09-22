package cn.voicet.gc.form;

import java.util.Date;

public class CallRecordForm {
	private String cts;	//统计服务器别名
	
	String curDate = String.format("%tF", new Date());
	private String sdt = curDate;	//统计开始日期
	private String edt = curDate;	//统计结束日期

	//calltxt size:8
	private String[] calltxt={sdt,edt,"","","1","0","0",""};
	
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
