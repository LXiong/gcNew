package cn.voicet.gc.form;


public class AgentForm {
	private String sdt;	//统计开始日期
	private String edt;	//统计结束日期
	/*
	 * agttxt[] addeidt,account,telnum,agtname,email
	 */
	private String[] agttxt;	
	
	private int agtid;
	
	//agent answer query
	private int callio=2;	//默认：不限,1:呼入,0:呼出
	private String telnum;
	
	private int tid;
	private int ttid;
	
	private String pageflag;
	
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
	public String getPageflag() {
		return pageflag;
	}
	public void setPageflag(String pageflag) {
		this.pageflag = pageflag;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
