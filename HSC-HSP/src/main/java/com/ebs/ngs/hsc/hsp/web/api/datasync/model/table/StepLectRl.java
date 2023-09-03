package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class StepLectRl extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = 1142045155521048663L;

	private String stepId;
	private String lectId;
	private String sortSeq;
	private String shwYn;
	private String prvLectYn;
	private String orntLectYn;
	private String lectOpnDt;
	private String fmySiteDsCd;
	
	public String getStepId() {
		return stepId;
	}
	
	public String getLectId() {
		return lectId;
	}
	
	public String getSortSeq() {
		return super.getNullToEmpty(sortSeq);
	}
	
	public String getShwYn() {
		return super.getEmptyToY(shwYn);
	}
	
	public String getPrvLectYn() {
		return super.getEmptyToN(prvLectYn);
	}
	
	public String getOrntLectYn() {
		return super.getEmptyToN(orntLectYn);
	}
	
	public String getLectOpnDt() {
		return super.getNullToEmpty(lectOpnDt);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
}
