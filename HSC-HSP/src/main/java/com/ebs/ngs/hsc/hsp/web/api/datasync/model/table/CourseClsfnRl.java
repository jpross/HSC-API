package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class CourseClsfnRl extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = 2893930767805897508L;

	private String clsfnSystId;
	private String courseId;
	private String sortSeq;
	private String fmySiteDsCd;
	
	public String getClsfnSystId() {
		return clsfnSystId;
	}
	
	public String getCourseId() {
		return courseId;
	}
	
	public String getSortSeq() {
		return super.getNullToEmpty(sortSeq);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
}
