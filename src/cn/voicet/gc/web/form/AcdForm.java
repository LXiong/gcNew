package cn.voicet.gc.web.form;

import java.util.Date;

public class AcdForm {
	private String cts;	//统计服务器别名
	
	String curDate = String.format("%tF", new Date());
	private String sdt=curDate;	//统计开始日期
	private String edt=curDate;	//统计结束日期
	
	private String[] acdtxt;
	private int grpid;
	private int taskid;
	
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
	public String[] getAcdtxt() {
		return acdtxt;
	}
	public void setAcdtxt(String[] acdtxt) {
		this.acdtxt = acdtxt;
	}
	public int getGrpid() {
		return grpid;
	}
	public void setGrpid(int grpid) {
		this.grpid = grpid;
	}
	public int getTaskid() {
		return taskid;
	}
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
}
