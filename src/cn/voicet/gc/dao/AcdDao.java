package cn.voicet.gc.dao;

import cn.voicet.gc.web.form.AcdForm;
import cn.voicet.util.DotSession;

public interface AcdDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.AcdDaoImpl";
	void queryAcdList(DotSession ds, AcdForm acdForm);
	void queryAcdAnalyList(DotSession ds, AcdForm acdForm);
	void saveAcd(DotSession ds, AcdForm acdForm);
	void deleteAcdByGrpid(AcdForm acdForm);
	void setTaskForAcd(AcdForm acdForm);
	void getAcdMonitorList(DotSession ds);
	void setCaller(DotSession ds, AcdForm acdForm);
}
