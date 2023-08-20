package com.ebs.ngs.hsc.hsp.web.api.application.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebs.ngs.hsc.hsp.web.common.mapper.CommonMapper;
import com.ebs.ngs.hsc.hsp.web.common.mapper.DataBaseFactory.DataSource;

@Repository("api-eduoffice-repository")
public class ApiRepository {
	
	private static final String QUERY_ID_PREFIX = "api.apiEduOffice.";
	
	@Autowired
	private CommonMapper commonMapper;
	
	private String getQueryId(String queryId) {
		return QUERY_ID_PREFIX + queryId;
	}
	
	// 강의 영상 URL
	public String getLectVodUrl(String lectId) throws Exception {
		return commonMapper.selectOne(getQueryId("findVodUrlByLectId"), lectId);
	}
	
	// 문항 소유주체 조회
	public String getFmySiteDsCd(Integer itemId) throws Exception {
		return commonMapper.selectOne(getQueryId("findFmySiteDsCdByItemId"), itemId);
	}
	
	// 해설 영상 URL (초/중학)
	public String getVodUrlByItemIdOnFmySiteDsCd(Integer itemId, DataSource dataSource) throws Exception {
		return commonMapper.selectOne(getQueryId("findVodUrlByItemIdOnFmySiteDsCd"), itemId, dataSource);
	}
	
	// 진단평가 해설 영상 URL (초/중학-ANL)
	public String getVodUrlByItemIdOnAnl(Integer itemId, DataSource dataSource) throws Exception {
		return commonMapper.selectOne(getQueryId("findVodUrlByItemIdOnAnl"), itemId, dataSource);
	}
	
	// 해설 영상 URL
	public String getItemVodUrl(Integer itemId) throws Exception {
		return commonMapper.selectOne(getQueryId("findVodUrlByItemId"), itemId);
	}

}
