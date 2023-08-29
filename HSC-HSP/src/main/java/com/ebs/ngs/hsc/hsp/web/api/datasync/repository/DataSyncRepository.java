package com.ebs.ngs.hsc.hsp.web.api.datasync.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebs.ngs.hsc.hsp.web.common.mapper.CommonMapper;
import com.ebs.ngs.hsc.hsp.web.common.mapper.DataBaseFactory.DataSource;

@Repository
public class DataSyncRepository {
	
	private static final String QUERY_ID_PREFIX = "api.dataSync";
	
	@Autowired
	private CommonMapper commonMapper;
	
	private String getQueryId(String table, String queryId) {
		return String.format("%s.%s.%s", QUERY_ID_PREFIX, table, queryId);
	}
	
	public <T> List<T> getCreateData(String table, String date) {
		List<T> datas = commonMapper.selectList(getQueryId(table, "findCreateData"), date);
		return datas;
	}

	public <T> List<T> getUpdateData(String table, String date) {
		List<T> datas = commonMapper.selectList(getQueryId(table, "findUpdateData"), date);
		return datas;
	}

	public <T> List<T> getDeleteData(String table, String date) {
		List<T> datas = commonMapper.selectList(getQueryId(table, "findDeleteData"), date);
		return datas;
	}
	
	public <T> List<T> getCreateData(String table, String date, DataSource dataSource) {
		List<T> datas = commonMapper.selectList(getQueryId(table, "findCreateData"), date, dataSource);
		return datas;
	}

	public <T> List<T> getUpdateData(String table, String date, DataSource dataSource) {
		List<T> datas = commonMapper.selectList(getQueryId(table, "findUpdateData"), date, dataSource);
		return datas;
	}

	public <T> List<T> getDeleteData(String table, String date, DataSource dataSource) {
		List<T> datas = commonMapper.selectList(getQueryId(table, "findDeleteData"), date, dataSource);
		return datas;
	}

}
