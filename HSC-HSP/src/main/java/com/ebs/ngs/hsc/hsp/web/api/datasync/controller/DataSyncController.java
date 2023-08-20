package com.ebs.ngs.hsc.hsp.web.api.datasync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebs.ngs.hsc.hsp.web.api.datasync.service.DataSyncService;

@RequestMapping("/api/v1")
@RestController("DataSyncController")
public class DataSyncController {
	
	@Autowired
	private DataSyncService dataSyncService;
	
}
