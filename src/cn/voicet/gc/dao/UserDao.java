package cn.voicet.gc.dao;

import java.util.Map;

import cn.voicet.gc.form.UserForm;
import cn.voicet.util.DotSession;

public interface UserDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.UserDaoImpl";
	Map<String, Object> userLogin(UserForm userForm);
	boolean updateUserPassword(DotSession ds, UserForm userForm);
	void queryCTSList(DotSession ds);
	void bindCurrentAgt(DotSession ds);
}
