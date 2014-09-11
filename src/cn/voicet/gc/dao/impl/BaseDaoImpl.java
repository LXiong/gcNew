package cn.voicet.gc.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class BaseDaoImpl extends JdbcDaoSupport {
	
	@Resource(name="jdbcTemplate")
	public final void setDataSourceDi(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
}
