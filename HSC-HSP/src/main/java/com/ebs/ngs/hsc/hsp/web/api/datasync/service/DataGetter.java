package com.ebs.ngs.hsc.hsp.web.api.datasync.service;

import java.util.List;

import com.ebs.ngs.hsc.hsp.web.common.mapper.DataBaseFactory.DataSource;

@FunctionalInterface
public interface DataGetter {

	public <T> List<T> get(String table, String date, DataSource dataSource);
	
}
