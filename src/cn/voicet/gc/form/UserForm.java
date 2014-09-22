package cn.voicet.gc.form;

public class UserForm {
	private String account;		
	private String password;	
	private String rolename;
	private int roleid;
	private String oldpwd;
	private String newpwd;
	
	private String product;
	private String customer;
	private String provider;
	
	private String mac;
	private String vercode;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		if(mac.length()>0)
		{
			this.mac = mac;
		}
		else
		{
			this.mac=null;
		}
		
	}
	public String getVercode() {
		return vercode;
	}
	public void setVercode(String vercode) {
		this.vercode = vercode;
	}
}
