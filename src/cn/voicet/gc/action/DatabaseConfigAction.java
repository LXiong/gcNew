package cn.voicet.gc.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.DatabaseConfigDao;
import cn.voicet.util.DotSession;

@Controller("databaseConfigAction")
@Scope("prototype")
@SuppressWarnings("serial")
public class DatabaseConfigAction extends BaseAction{

	private static Logger log = Logger.getLogger(DatabaseConfigAction.class);
	@Resource(name=DatabaseConfigDao.SERVICE_NAME)
	private DatabaseConfigDao databaseConfigDao;
	
	public String home() {
		DotSession ds = DotSession.getVTSession(request);
		databaseConfigDao.databaseInfo(ds);
		return "show_database";
	}
	
	/** 清空日志 */
	public String empty() {
		DotSession ds = DotSession.getVTSession(request);
		log.info("account:"+ds.account+", roleid:"+ds.roleID);
		databaseConfigDao.emptyDatabase(ds);
		return null;
	}
	
	/** 备份数据库 */
	public String backup() {
		DotSession ds = DotSession.getVTSession(request);
		log.info("account:"+ds.account+", roleid:"+ds.roleID+", defbackupfilename:"+defbackupfilename);
		databaseConfigDao.backupDatabase(ds, defbackupfilename);
		return null;
	}

	private String defbackupfilename;
	public String getDefbackupfilename() {
		return defbackupfilename;
	}
	public void setDefbackupfilename(String defbackupfilename) {
		this.defbackupfilename = defbackupfilename;
	}
}
