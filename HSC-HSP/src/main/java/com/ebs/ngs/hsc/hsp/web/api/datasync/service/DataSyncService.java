package com.ebs.ngs.hsc.hsp.web.api.datasync.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.Course;
import com.ebs.ngs.hsc.hsp.web.api.datasync.repository.DataSyncRepository;

@Service("api-datasync-service")
public class DataSyncService {
	
	@Autowired
	private DataSyncRepository dataSyncRepository;
	
	public List<Course> findCourseByCreate(String date) {
		return dataSyncRepository.findCourseByCreate(date);
	}
	
}
