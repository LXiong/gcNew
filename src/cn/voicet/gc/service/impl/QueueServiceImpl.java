package cn.voicet.gc.service.impl;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.voicet.gc.dao.QueueDao;
import cn.voicet.gc.service.QueueService;
import cn.voicet.gc.web.form.QueueForm;
import cn.voicet.util.DotSession;

@Transactional(readOnly=true)
@Service(QueueService.SERVICE_NAME)
public class QueueServiceImpl implements QueueService{
	
	@Resource(name=QueueDao.SERVICE_NAME)
	private QueueDao queueDao;
	
	public void queryTaskTelList(DotSession ds, QueueForm queueForm) {
		queueDao.queryTaskTelList(ds, queueForm);
	}
	public void queryQueueList(DotSession ds, QueueForm queueForm) {
		queueDao.queryQueueList(ds, queueForm);
	}
	public void saveQueue(QueueForm queueForm) {
		queueDao.saveQueue(queueForm);
	}
	public void deleteQueueByTid(QueueForm queueForm) {
		queueDao.deleteQueueByTid(queueForm);
	}
	public void exportData(QueueForm queueForm, HttpServletResponse response) {
		queueDao.exportData(queueForm, response);
	}
	
	
	/**
	 * import
	 */
	public void batchImportData(File uploadExcel, int iTid, int iKind) {
		queueDao.batchImportData(uploadExcel, iTid, iKind);
	}
	
}
