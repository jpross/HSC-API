package com.ebs.ngs.hsc.hsp.web.api.datasync.model;

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
}
