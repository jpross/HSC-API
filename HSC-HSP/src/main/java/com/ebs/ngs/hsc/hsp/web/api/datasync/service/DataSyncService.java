package com.ebs.ngs.hsc.hsp.web.api.datasync.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebs.biz.api.common.response.BizDataSyncApiResponse;
import com.ebs.ngs.hsc.hsp.web.api.datasync.repository.DataSyncRepository;
import com.ebs.ngs.hsc.hsp.web.common.mapper.DataBaseFactory.DataSource;

@Service
public class DataSyncService {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSyncService.class);
	
	@Autowired
	private DataSyncRepository repository;
	
	public <T> BizDataSyncApiResponse<T> getDatasByFactory(String tableCd, String action, String date) {
		
		List<T> datas = new ArrayList<>();
		List<String> tables = TableSelector.getTable(tableCd);
		
		logger.info("==================================================================================");
		logger.info("Biz DataSync API Service");
		logger.info("==================================================================================");
		logger.info("tableCd: " + tableCd);
		logger.info("tables: " + String.join(",", tables));
		logger.info("action: " + action);
		logger.info("date: " + date);
		
		DataGetter getter = getDataGetter(action);
		
		tables.forEach(table -> {
			datas.addAll(getData(getter, table, date));
		});
		
		logger.info("data size: " + String.valueOf(datas.size()));
		logger.info("==================================================================================");
		
		return new BizDataSyncApiResponse<T>()
				.setDate(date)
				.setData(datas);
		
	}
	
	public <T> List<T> getData(DataGetter getter, String table, String date) {
		DataSource dataSource = (table.indexOf(".jhs") > -1) ? DataSource.JHS_SLAVE : DataSource.HSC_SLAVE;
		List<T> datas = getter.get(table, date, dataSource);
		return datas;
	}
	
	public DataGetter getDataGetter(String action) {
		
		switch (action) {
			case "create":
				return repository::getCreateData;
			case "update":
				return repository::getUpdateData;
			case "delete":
				return repository::getDeleteData;
			default:
				return null;
		}
		
	}
	
}
