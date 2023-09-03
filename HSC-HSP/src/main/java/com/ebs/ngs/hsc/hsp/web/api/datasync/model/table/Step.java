package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

<<<<<<< HEAD
import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class Step extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = 8643167732824278312L;

	private String stepId;
	private String stepNm;
	private String totalLectTnum;
	private String regLectTnum;
	private String lectSortType;
	private String useYn;
	private String fmySiteDsCd;
	
	public String getStepId() {
		return stepId;
	}
	
	public String getStepNm() {
		return super.getNullToEmpty(stepNm);
	}
	
	public String getTotalLectTnum() {
		return super.getNullToEmpty(totalLectTnum);
	}
	
	public String getRegLectTnum() {
		return super.getNullToEmpty(regLectTnum);
	}
	
	public String getLectSortType() {
		return super.getNullToEmpty(lectSortType);
	}
	
	public String getUseYn() {
		return super.getEmptyToY(useYn);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
=======
import lombok.Data;

@Data
public class Step {
	private String stepId;
	private String stepNm;
	private String totalLectTnum;
	private String regLectTnum;
	private String lectSortType;
	private String useYn;
	private String fmySiteDsCd;
>>>>>>> branch 'main' of https://github.com/jpross/HSC-API
}
