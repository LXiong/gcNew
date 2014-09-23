package cn.voicet.gc.dao;

import cn.voicet.util.DotSession;

public interface DatabaseConfigDao {
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.DatabaseConfigDaoImpl";
	void databaseInfo(DotSession ds);
	void emptyDatabase(DotSession ds);
	void backupDatabase(DotSession ds, String defbackupfilename);
}
