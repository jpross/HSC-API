package com.ebs.ngs.hsc.hsp.web.api.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebs.biz.api.common.exception.BizApiException;
import com.ebs.biz.api.common.response.BizApiResponseCode;
import com.ebs.ngs.hsc.hsp.web.api.application.repository.ApiRepository;
import com.ebs.ngs.hsc.hsp.web.common.mapper.DataBaseFactory.DataSource;
import com.ebs.ngs.hsc.hsp.web.common.redis.RedisService;

@Service("api-eduoffice-service")
public class ApiService {
	
	private static final String API_REDIS_KEY_PREFIX = "api-eduoffice-vod:";
	
	// 하루
	private static final long EXPIRE_TIME = 86400L;
	
	@Autowired @Qualifier("apiRedisService")
	private RedisService redisService;
	
	@Autowired
	private ApiRepository repository;
	
	private static String getApiRedisKey(String key) {
		return API_REDIS_KEY_PREFIX + key;
	}
	
	// 강의 영상 URL
	public String getLectVodUrl(String lectId) {
		// redis key: api-eduoffice-vod:lect
		// hash key: lectId
		String key = getApiRedisKey("lect");
		return redisService
				.getValueToMap(
					key, 
					lectId,
					() -> getLectVodUrlToDB(lectId),
					// 만료시간 하루로 셋팅
					EXPIRE_TIME
				);
	}
	
	private String getLectVodUrlToDB(String lectId) {
		try {
			return repository.getLectVodUrl(lectId);
		} catch (Exception e) {
			throw new BizApiException(BizApiResponseCode.INTERNAL_DB_ERROR);
		}
	}
	
	// 해설 영상 URL
	public String getItemVodUrl(Integer itemId) {
		// redis key: api-eduoffice-vod:item
		// hash key: itemId
		String key = getApiRedisKey("item");
		return redisService
				.getValueToMap(
					key,
					itemId.toString(),
					() -> getItemVodUrlToDB(itemId)
					, 
					// 만료시간 하루로 셋팅
					EXPIRE_TIME
				);
	}
	
	private String getItemVodUrlToDB(Integer itemId) {
		try {
			// 문항의 초/중/고 영역 확인
			String fmySiteDsCd = repository.getFmySiteDsCd(itemId);
			if (fmySiteDsCd == null) {
				return null;
			}
			
			String vodUrl; 
			fmySiteDsCd = fmySiteDsCd.toUpperCase();
			if ("HSC".equals(fmySiteDsCd)) {
				// 고교
				vodUrl = repository.getItemVodUrl(itemId);
				if (vodUrl != null) {
					return vodUrl;
				}
			}
			
			// 초/중학
			DataSource dataSource = "PRI".equals(fmySiteDsCd) ? DataSource.PRI_SLAVE : DataSource.JHS_SLAVE;
			vodUrl = repository.getVodUrlByItemIdOnFmySiteDsCd(itemId, dataSource);
			if (vodUrl == null || vodUrl.replaceAll("\\s", "").isEmpty()) {
				// 진단평가 해설 영상일 경우
				vodUrl = repository.getVodUrlByItemIdOnAnl(itemId, DataSource.ANL_SLAVE);
			}
			
			return vodUrl;
		}
		catch (Exception e) {
			throw new BizApiException(BizApiResponseCode.INTERNAL_DB_ERROR);
		}
	}
	
}
