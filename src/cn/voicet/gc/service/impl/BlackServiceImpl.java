package cn.voicet.gc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.voicet.gc.dao.BlackDao;
import cn.voicet.gc.service.BlackService;
import cn.voicet.gc.web.form.BlackForm;
import cn.voicet.util.DotSession;

@Transactional(readOnly=true)
@Service(BlackService.SERVICE_NAME)
public class BlackServiceImpl implements BlackService{
	
	@Resource(name=BlackDao.SERVICE_NAME)
	private BlackDao blackDao;

	public void queryBlackList(DotSession ds, BlackForm blackForm) {
		blackDao.queryBlackList(ds, blackForm);
	}
	public void saveTelnum(BlackForm blackForm) {
		blackDao.saveTelnum(blackForm);
	}
	public void deleteTelnumByBid(BlackForm blackForm) {
		blackDao.deleteTelnumByBid(blackForm);
	}
}
