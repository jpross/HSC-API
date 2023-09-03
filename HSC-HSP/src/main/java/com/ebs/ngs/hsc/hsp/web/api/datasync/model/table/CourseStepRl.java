package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

<<<<<<< HEAD
import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class CourseStepRl extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = -5003968056508753202L;

	private String courseId;
	private String stepId;
	private String sortSeq;
	private String prcPcyCd;
	private String shwYn;
	private String estDt;
	private String fmySiteDsCd;

	public String getCourseId() {
		return courseId;
	}
	
	public String getStepId() {
		return stepId;
	}
	
	public String getSortSeq() {
		return super.getNullToEmpty(sortSeq);
	}
	
	public String getPrcPcyCd() {
		return super.getNullToEmpty(prcPcyCd);
	}
	
	public String getShwYn() {
		return super.getEmptyToY(shwYn);
	}
	
	public String getEstDt() {
		return super.getNullToEmpty(estDt);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
=======
import lombok.Data;

@Data
public class CourseStepRl {
	private String stepId;
	private String courseId;
	private String sortSeq;
	private String prcPcyCd;
	private String shwYn;
	private String estDt;
	private String fmySiteDsCd;
>>>>>>> branch 'main' of https://github.com/jpross/HSC-API
}
