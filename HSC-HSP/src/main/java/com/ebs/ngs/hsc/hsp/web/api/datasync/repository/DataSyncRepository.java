package com.ebs.ngs.hsc.hsp.web.api.datasync.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.Course;
import com.ebs.ngs.hsc.hsp.web.common.mapper.CommonMapper;

@Repository("api-datasync-repository")
public class DataSyncRepository {
	
	private static final String QUERY_ID_PREFIX = "api.apiDataSync.";
	
	@Autowired
	private CommonMapper commonMapper;
	
	private String getQueryId(String queryId) {
		return QUERY_ID_PREFIX + queryId;
	}
	
	// 강의 - 신규 데이터
	public List<Course> findCourseByCreate(String date) {
		return commonMapper.selectOne(getQueryId("findCourseByCreate"), date);
	}

}
