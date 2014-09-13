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
	
	private String account;
	
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
