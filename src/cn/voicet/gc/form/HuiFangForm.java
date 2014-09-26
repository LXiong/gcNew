package cn.voicet.gc.form;

public class HuiFangForm {
	
	private int tid;
	private String tname;
	private int kind;
	
	private int ttid;
	private int hfqk;		//回访情况
	private String hfname;	//回访姓名	
	private int sex = 1;	//性别,默认1:男
	private int ywbwdc;		//异网本网调查
	private int manyid = 1;		//满意度,默认1:满意
	private String phonemodel;	//手机品牌型号
	private int jkfl;			//机卡分离
	private String gkd;			//购卡点
	private String remark;		//备注
	//
	private int jffs;			//缴费方式
	private String customjy;	//客户建议
	private int succfail = 1;	//呼叫结果,默认1:成功
	private String hfzjh;		//回访证件号
	private String hfstel;		//回访二次电话
	private String address;		//回访联系地址，客户联系地址
	private String email;		//邮箱地址
	//
	private String tccon;		//套餐内容
	private String qxtccon;		//需取消套餐内容
	private String yxfaildecause;	//营销失败原因
	private int yxsuccfail = 1; 	//是否营销成功,默认1:成功	
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getTtid() {
		return ttid;
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
	public void setTtid(int ttid) {
		this.ttid = ttid;
	}
	public int getHfqk() {
		return hfqk;
	}
	public void setHfqk(int hfqk) {
		this.hfqk = hfqk;
	}
	public String getHfname() {
		return hfname;
	}
	public void setHfname(String hfname) {
		this.hfname = hfname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getYwbwdc() {
		return ywbwdc;
	}
	public void setYwbwdc(int ywbwdc) {
		this.ywbwdc = ywbwdc;
	}
	public int getManyid() {
		return manyid;
	}
	public void setManyid(int manyid) {
		this.manyid = manyid;
	}
	public String getPhonemodel() {
		return phonemodel;
	}
	public void setPhonemodel(String phonemodel) {
		this.phonemodel = phonemodel;
	}
	public int getJkfl() {
		return jkfl;
	}
	public void setJkfl(int jkfl) {
		this.jkfl = jkfl;
	}
	public String getGkd() {
		return gkd;
	}
	public void setGkd(String gkd) {
		this.gkd = gkd;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getJffs() {
		return jffs;
	}
	public void setJffs(int jffs) {
		this.jffs = jffs;
	}
	public String getCustomjy() {
		return customjy;
	}
	public void setCustomjy(String customjy) {
		this.customjy = customjy;
	}
	public int getSuccfail() {
		return succfail;
	}
	public void setSuccfail(int succfail) {
		this.succfail = succfail;
	}
	public String getHfzjh() {
		return hfzjh;
	}
	public void setHfzjh(String hfzjh) {
		this.hfzjh = hfzjh;
	}
	public String getHfstel() {
		return hfstel;
	}
	public void setHfstel(String hfstel) {
		this.hfstel = hfstel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTccon() {
		return tccon;
	}
	public void setTccon(String tccon) {
		this.tccon = tccon;
	}
	public String getQxtccon() {
		return qxtccon;
	}
	public void setQxtccon(String qxtccon) {
		this.qxtccon = qxtccon;
	}
	public String getYxfaildecause() {
		return yxfaildecause;
	}
	public void setYxfaildecause(String yxfaildecause) {
		this.yxfaildecause = yxfaildecause;
	}
	public int getYxsuccfail() {
		return yxsuccfail;
	}
	public void setYxsuccfail(int yxsuccfail) {
		this.yxsuccfail = yxsuccfail;
	}
}
