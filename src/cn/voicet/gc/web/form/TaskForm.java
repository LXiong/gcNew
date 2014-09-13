package cn.voicet.gc.web.form;

public class TaskForm {
	private int tid;		//任务ID
	private String tname;	//任务名称
	private String ani;		//主叫号码
	private int kind;		//队列业务类型(0:标准，1:回访1，2:回访2，3:回访3)
	private int maxline;	//最大外呼线路数(0:表示不限制)
	private String overflowto;	//业务组忙时转向分机号码
	
	private String telnum;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getAni() {
		return ani;
	}
	public void setAni(String ani) {
		this.ani = ani;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public int getMaxline() {
		return maxline;
	}
	public void setMaxline(int maxline) {
		this.maxline = maxline;
	}
	public String getOverflowto() {
		return overflowto;
	}
	public void setOverflowto(String overflowto) {
		this.overflowto = overflowto;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
}
