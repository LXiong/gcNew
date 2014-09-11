package cn.voicet.gc.service;

import java.util.Map;

import cn.voicet.gc.web.form.UserForm;
import cn.voicet.util.DotSession;

public interface UserService {
	public final static String SERVICE_NAME = "cn.voicet.gc.service.impl.UserServiceImpl";
	Map<String, Object> userLogin(UserForm userForm);
	Integer updateUserPassword(DotSession ds, UserForm userForm);
}
