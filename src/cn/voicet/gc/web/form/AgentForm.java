package cn.voicet.gc.web.form;

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
	private String agent;
	private int callio=1;
	private String telnum;
	private String telnumLab="主叫号码：";
	
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
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
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
	public String getTelnumLab() {
		return telnumLab;
	}
	public void setTelnumLab(String telnumLab) {
		this.telnumLab = telnumLab;
	}
}
