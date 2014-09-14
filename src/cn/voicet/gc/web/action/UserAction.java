package cn.voicet.gc.web.action;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.UserDao;
import cn.voicet.gc.web.form.UserForm;
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
	
	@SuppressWarnings("unchecked")
	public String ajaxlogin(){
		log.info("account:"+userForm.getAccount()+", password:"+userForm.getPassword()+", vercode:"+userForm.getVercode());
		JSONObject json = new JSONObject();
		if (request.getSession().getAttribute("vts")==null) {
			DotSession ds = new DotSession();
			request.getSession().setAttribute("vts", ds);
		}
		DotSession ds=DotSession.getVTSession(request);
		
		Map<String, Object> map = userDao.userLogin(userForm);
		log.info("user login: "+map);
		ds.map.put("name", map.get("username"));
		ds.username=(String) map.get("username");
		ds.password = userForm.getPassword();
		ds.account=userForm.getAccount();
		ds.roleName = (String) map.get("rolename");
		ds.roleID=(String) map.get("roleid");
		
		/*
			ds.rbn = (String) map.get("rbn");
			ds.rbm = (String) map.get("rbm");
			ds.isedit = Integer.valueOf((String) map.get("isedit"));
			ds.workyear=Integer.valueOf((String) map.get("workyear"));
			ds.yearlock=Integer.valueOf((String) map.get("yearlock"));
			ds.curBM = ds.rbm;
			ds.subPathTitle.initPath();
		*/
		log.info("rand:"+request.getSession().getAttribute("rand"));
		
		ds.roleID ="1";
		if(!ds.roleID.equals("0"))
		{
			json.put("status", "ok");
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
		response.getWriter().print(bool);
		return null;
	}
	
	/**  */
	public String logout() {
		DotSession ds=DotSession.getVTSession(request);
		ds.clear();
		request.getSession().invalidate();
		return "show_logout";
	}
	
}
