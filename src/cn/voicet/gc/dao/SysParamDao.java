package cn.voicet.gc.dao;

import cn.voicet.util.DotSession;

public interface SysParamDao {
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.SysParamDaoImpl";
	String getAni(String defcts);
	int getMaxwait(String defcts);
	void saveParam(DotSession ds, String defcts, String ani, int maxwait);
}
