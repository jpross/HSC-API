package com.ebs.ngs.hsc.hsp.web.api.datasync.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import com.ebs.biz.api.common.response.BizDataSyncApiResponse;
import com.ebs.biz.api.common.util.DateUtil;
=======
import com.ebs.biz.api.common.response.BizDataSyncApiResponse;
import com.ebs.biz.api.common.util.DateUtil;
>>>>>>> branch 'main' of https://github.com/jpross/HSC-API.git
import com.ebs.biz.api.common.validate.ApiKeyValidate;
import com.ebs.ngs.hsc.hsp.web.api.datasync.service.DataSyncService;

@RequestMapping("/api/v1/data")
@RestController("DataSyncController")
@Validated
public class DataSyncController {
	
	@Autowired
	private DataSyncService dataSyncService;
	
	@GetMapping("sync")
	public BizDataSyncApiResponse<?> sync(
			@RequestParam(value = "table", required = true) String table,
			@RequestParam(value = "action", required = true) String action,
			@RequestParam(value = "apiKey", required = true) @Valid @ApiKeyValidate String apiKey,
			@RequestParam(value = "date", required = false) String date
			) {
<<<<<<< HEAD
		
		if (date == null) {
			date = DateUtil.getDate("yyyyMMdd", -1);
			System.out.println("date: " + date);
		}
		
=======
		
		if (date == null) {
			date = DateUtil.getDate("yyyyMMdd", -1);
		}
		
>>>>>>> branch 'main' of https://github.com/jpross/HSC-API.git
		BizDataSyncApiResponse<?> response = dataSyncService.getDatasByFactory(table, action, date);
		
		return response;
		
	}
	
}
