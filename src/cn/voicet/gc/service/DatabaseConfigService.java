package cn.voicet.gc.service;

import cn.voicet.util.DotSession;

public interface DatabaseConfigService {

	public final static String SERVICE_NAME = "cn.voicet.gc.service.impl.DatabaseConfigServiceImpl";

	void databaseInfo(DotSession ds);

	void emptyDatabase(DotSession ds);

	void backupDatabase(DotSession ds, String defbackupfilename);

	void databaseIndexManage(DotSession ds);

}
