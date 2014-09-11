package cn.voicet.gc.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.voicet.gc.dao.UserDao;
import cn.voicet.gc.service.UserService;
import cn.voicet.gc.web.form.UserForm;
import cn.voicet.util.DotSession;

@Transactional(readOnly=true)
@Service(UserService.SERVICE_NAME)
public class UserServiceImpl implements UserService{
	
	@Resource(name=UserDao.SERVICE_NAME)
	private UserDao userDao;

	public Map<String, Object> userLogin(UserForm userForm) {
		return userDao.userLogin(userForm);
	}
	
	public Integer updateUserPassword(DotSession ds, UserForm userForm) {
		return userDao.updateUserPassword(ds, userForm);
	}

}
