package cn.voicet.gc.service;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import cn.voicet.gc.web.form.QueueForm;
import cn.voicet.util.DotSession;

public interface QueueService {
	public final static String SERVICE_NAME = "cn.voicet.gc.service.impl.QueueServiceImpl";
	void queryTaskTelList(DotSession ds, QueueForm queueForm);
	void queryQueueList(DotSession ds, QueueForm queueForm);
	void saveQueue(QueueForm queueForm);
	void deleteQueueByTid(QueueForm queueForm);
	void exportData(QueueForm queueForm, HttpServletResponse response);
	void batchImportData(File uploadExcel, int iTid, int iKind);
}
