package cn.voicet.gc.web.form;

public class TaskForm {
	private int tid;		//任务ID
	private String tname;	//任务名称
	private int kind;		//队列业务类型(0:标准，1:回访1，2:回访2，3:回访3)
	private String taskinfo;
	
	private String telnum;
	private int ttid;
	
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
}
