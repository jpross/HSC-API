package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;
import com.ebs.ngs.hsc.hsp.web.api.datasync.model.key.CourseId;

import lombok.Setter;

@Setter
public class CourseAdtlInfo extends DataSyncModel implements Serializable {

	private static final long serialVersionUID = -9093682888187868627L;
	
	private CourseId courseId;
	private String toryCd;
	private String listViewYn;
	private String regCmpltYn;
	private String fmySiteDsCd;
	
	public String getCourseId() {
		return courseId.getCourseId();
	}
	
	public void setCourseId(String courseId) {
		this.courseId = new CourseId(courseId);
	}
	
	public String getToryCd() {
		return super.getNullToEmpty(toryCd);
	}
	
	public String getListViewYn() {
		return super.getEmptyToY(listViewYn);
	}
	
	public String getRegCmpltYn() {
		return super.getEmptyToN(regCmpltYn);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
}
