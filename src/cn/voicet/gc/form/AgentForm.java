package cn.voicet.gc.form;

import java.util.Date;

public class AgentForm {
	String curDate = String.format("%tF", new Date());
	private String sdt=curDate;	//统计开始日期
	private String edt=curDate;	//统计结束日期
	/*
	 * agttxt[] addeidt,account,telnum,agtname,email
	 */
	private String[] agttxt;	
	
	private int agtid;
	
	//agent answer query
	private int callio=0;
	private String telnum;
	
	private int tid;
	private int ttid;
	
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
	public String[] getAgttxt() {
		return agttxt;
	}
	public void setAgttxt(String[] agttxt) {
		this.agttxt = agttxt;
	}
	public int getAgtid() {
		return agtid;
	}
	public void setAgtid(int agtid) {
		this.agtid = agtid;
	}
	public int getCallio() {
		return callio;
	}
	public void setCallio(int callio) {
		this.callio = callio;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getTtid() {
		return ttid;
	}
	public void setTtid(int ttid) {
		this.ttid = ttid;
	}
}
