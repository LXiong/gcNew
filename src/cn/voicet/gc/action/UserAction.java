package cn.voicet.gc.action;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.UserDao;
import cn.voicet.gc.form.UserForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("userAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class UserAction extends BaseAction implements ModelDriven<UserForm>{
	private static Logger log = Logger.getLogger(UserAction.class);
	@Resource(name=UserDao.SERVICE_NAME)
	private UserDao userDao;
	private UserForm userForm = new UserForm();
	
	public UserForm getModel() {
		return userForm;
	}
	
	public String ajaxlogin(){
		log.info("account:"+userForm.getAccount()+", password:"+userForm.getPassword()+", vercode:"+userForm.getVercode());
		JSONObject json = new JSONObject();
		if (request.getSession().getAttribute("vts")==null) {
			DotSession ds = new DotSession();
			request.getSession().setAttribute("vts", ds);
		}
		DotSession ds=DotSession.getVTSession(request);
		
		Map<String, Object> map = userDao.userLogin(userForm);
		log.info("user login map: "+map);
		//
		userDao.queryCTSList(ds);
		log.info("ctsList:"+ds.ctsList);
		
		//
		ds.username=(String) map.get("username");
		ds.roleName = (String) map.get("rolename");
		ds.agttelnum = (String) map.get("telnum");
		ds.roleID=(String) map.get("roleid");
		//
		ds.password = userForm.getPassword();
		ds.account=userForm.getAccount();
		//
		log.info("rand:"+request.getSession().getAttribute("rand"));
		log.info("roleID:"+ds.roleID);
		//
		if(null!=ds.roleID && !ds.roleID.equals("0"))
		{
			json.put("status", "ok");
			log.info("login complete");
		}
		if(null!=request.getSession().getAttribute("rand") && !userForm.getVercode().trim().equals((String) request.getSession().getAttribute("rand")))
		{
			json.put("status", "vercodeerror");
		}
		log.info("json:"+json);
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}

	public String home(){
		log.info("has login");
		return "show_home";
	}
	
	/**
	 * update password
	 * @return
	 * @throws IOException
	 */
	public String updatePwd() throws IOException
	{
		DotSession ds=DotSession.getVTSession(request);
		log.info("oldpwd:"+userForm.getOldpwd()+", newpwd:"+userForm.getNewpwd());
		
		boolean bool = userDao.updateUserPassword(ds, userForm);
		log.info("bool:"+bool);
		if(bool)
		{
			ds.password = userForm.getNewpwd();
			log.info("update after password:"+ds.password);
		}
		response.getWriter().print(bool);
		return null;
	}
	
	/**  */
	public String logout() {
		DotSession ds=DotSession.getVTSession(request);
		ds.clear();
		request.getSession().invalidate();
		return null;
	}
	
	/**
	 * save global cts
	 * @return
	 */
	public String saveCTS()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("current cts:"+cts);
		ds.curCTS = cts;
		return null;
	}
	
	public String cts;
	private String clientname;
	private String ctsname;
	public String getCts() {
		return cts;
	}
	public void setCts(String cts) {
		this.cts = cts;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getCtsname() {
		return ctsname;
	}
	public void setCtsname(String ctsname) {
		this.ctsname = ctsname;
	}
}
