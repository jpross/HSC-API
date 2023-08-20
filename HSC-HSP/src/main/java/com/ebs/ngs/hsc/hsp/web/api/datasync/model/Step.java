package com.ebs.ngs.hsc.hsp.web.api.datasync.model;

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
}
