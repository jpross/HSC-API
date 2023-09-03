package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class EduCourse extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = 2617692084926426156L;
	
	private String courseId;
	private String regCopionCnt;
	private String learnStep;
	private String fmySiteDsCd;
	
	public String getCourseId() {
		return courseId;
	}
	
	public String getRegCopionCnt() {
		return super.getNullToEmpty(regCopionCnt);
	}
	
	public String getLearnStep() {
		return super.getNullToEmpty(learnStep);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
}
