package cn.voicet.gc.service;

import cn.voicet.gc.web.form.BlackForm;
import cn.voicet.util.DotSession;

public interface BlackService {
	public final static String SERVICE_NAME = "cn.voicet.gc.service.impl.BlackServiceImpl";
	void queryBlackList(DotSession ds, BlackForm blackForm);
	void saveTelnum(BlackForm blackForm);
	void deleteTelnumByBid(BlackForm blackForm);
}
