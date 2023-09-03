package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

<<<<<<< HEAD
import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class CourseTchRl extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = -713221506988625951L;
	
	private String courseId;
	
	private String fmySiteDsCd;
	
	private String tchId;
	
	private String typeCode;
	
	public String getCourseId() {
		return courseId;
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
	public String getTchId() {
		return tchId;
	}
	
	public String getTypeCode() {
		return typeCode;
	}
	
=======
import lombok.Data;

@Data
public class CourseTchRl {
	private String courseId;
	private String fmySiteDsCd;
	private String tchId;
	private String typeCode;
>>>>>>> branch 'main' of https://github.com/jpross/HSC-API
}
