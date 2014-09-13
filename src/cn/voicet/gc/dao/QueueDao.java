package cn.voicet.gc.dao;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import cn.voicet.gc.web.form.QueueForm;
import cn.voicet.util.DotSession;

public interface QueueDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.QueueDaoImpl";
	void queryTaskTelList(DotSession ds, QueueForm queueForm);
	void queryQueueList(DotSession ds, QueueForm queueForm);
	void saveQueue(QueueForm queueForm);
	void deleteQueueByTid(QueueForm queueForm);
	void exportData(QueueForm queueForm, HttpServletResponse response);
	void batchImportData(File uploadExcel, int iTid, int iKind);
	void queryTelListByTid(DotSession ds, QueueForm queueForm);
}
