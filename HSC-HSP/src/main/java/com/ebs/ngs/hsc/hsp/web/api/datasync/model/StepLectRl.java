package com.ebs.ngs.hsc.hsp.web.api.datasync.model;

import lombok.Data;

@Data 
public class StepLectRl {
	private String stepId;
	private String lectId;
	private String sortSeq;
	private String shwYn;
	private String prvLectYn;
	private String orntLectYn;
	private String lectOpnDt;
	private String fmySiteDsCd;
}
