package cn.voicet.gc.dao;

public interface SysParamDao {
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.SysParamDaoImpl";
	int getAni();
	int getMaxwait();
	void saveParam(int ani, int maxwait);
}
