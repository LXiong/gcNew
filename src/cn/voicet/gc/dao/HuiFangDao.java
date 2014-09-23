package cn.voicet.gc.dao;

import java.util.Map;

import cn.voicet.gc.form.HuiFangForm;

public interface HuiFangDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.HuiFangDaoImpl";
	Map<String, Object> getHuiFangInfo(int flag,HuiFangForm huiFangForm);
	void saveHuiFangInfo(int flag, HuiFangForm huiFangForm);
	int getHuiFangType(HuiFangForm huiFangForm);
	Map<String, Object> queryAgentAnserDetailList(HuiFangForm huiFangForm);
}
