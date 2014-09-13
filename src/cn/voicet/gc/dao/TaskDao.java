package cn.voicet.gc.dao;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import cn.voicet.gc.web.form.QueueForm;
import cn.voicet.util.DotSession;

public interface TaskDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.TaskDaoImpl";
	void queryTaskList(DotSession ds);
}
