package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;
<<<<<<< HEAD

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
=======
import com.ebs.ngs.hsc.hsp.web.api.datasync.model.key.ClsfnSystIdAndCourseId;

import lombok.Setter;

@Setter
public class CourseClsfnRl extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = 2893930767805897508L;

	private ClsfnSystIdAndCourseId key;
	
	private String sortSeq;
	private String fmySiteDsCd;
	
	public String getClsfnSystId() {
		return key.getClsfnSystId();
	}
	
	public void setClsfnSystId(String clsfnSystId) {
		key.setClsfnSystId(clsfnSystId);
	}
	
	public String getCourseId() {
		return key.getCourseId();
	}
	
	public void setCourseId(String courseId) {
		key.setCourseId(courseId);
>>>>>>> branch 'main' of https://github.com/jpross/HSC-API
	}
	
	public String getSortSeq() {
		return super.getNullToEmpty(sortSeq);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
}
