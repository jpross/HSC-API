package com.ebs.ngs.hsc.hsp.web.api.datasync.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebs.biz.api.common.exception.BizApiException;
import com.ebs.biz.api.common.response.BizDataSyncApiResponse;
import com.ebs.biz.api.common.util.DateUtil;
import com.ebs.biz.api.common.validate.ApiKeyValidate;
import com.ebs.ngs.hsc.hsp.web.api.datasync.service.DataSyncService;

@RequestMapping("/api/v1/data")
@RestController("DataSyncController")
@Validated
public class DataSyncControllerV1 {
	
	@Autowired
	private DataSyncService dataSyncService;
	
	@GetMapping("sync")
	public BizDataSyncApiResponse<?> sync(
			@RequestParam(value = "table", required = true) String table,
			@RequestParam(value = "action", required = true) String action,
			@RequestParam(value = "apiKey", required = true) @Valid @ApiKeyValidate String apiKey,
			@RequestParam(value = "date", required = false) String date
			) {
		
		if (date == null) {
			date = DateUtil.getDate("yyyyMMdd", -1);
		}
		
		try {
			BizDataSyncApiResponse<?> response = dataSyncService.getDataByFactory(table, action, date);
			return response;
		}
		catch (BizApiException e) {
			return new BizDataSyncApiResponse<Object>(e.getBizApiResponseCode());
		}
		
	}
	
}
