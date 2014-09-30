package cn.voicet.gc.form;

public class TaskForm {
	private int tid;		//任务ID
	private String tname;	//任务名称
	private int kind;		//队列业务类型(0:标准，1:回访1，2:回访2，3:回访3)
	private int state;		//任务状态
	private String taskinfo;
	
	private String telnum;
	private int ttid;
	private int ddstate=20;	//调度状态
	private int start=1;	//起始序号
	private int callret=20;	//呼叫结果
	
	private String pageflag;
	
	private String[] cts;
	
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
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getTaskinfo() {
		return taskinfo;
	}
	public void setTaskinfo(String taskinfo) {
		this.taskinfo = taskinfo;
	}
	public int getTtid() {
		return ttid;
	}
	public void setTtid(int ttid) {
		this.ttid = ttid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String[] getCts() {
		return cts;
	}
	public void setCts(String[] cts) {
		this.cts = cts;
	}
	public int getDdstate() {
		return ddstate;
	}
	public void setDdstate(int ddstate) {
		this.ddstate = ddstate;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCallret() {
		return callret;
	}
	public void setCallret(int callret) {
		this.callret = callret;
	}
	public String getPageflag() {
		return pageflag;
	}
	public void setPageflag(String pageflag) {
		this.pageflag = pageflag;
	}
}
