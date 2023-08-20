package com.ebs.ngs.hsc.hsp.web.api.datasync.model;

import lombok.Data;

@Data
public class Course {
	private String courseId;
	private String courseNm;
	private String courseSubtitleNm;
	private String thmbPath;
	private String courseFeat;
	private String courseYear;
	private String courseStartDt;
	private String courseEndDt;
	private String courseClsCd;
	private String stdyPeriod;
	private String reviewPeriod;
	private String lvlCd;
	private String stdyTgCd;
	private String fmySiteDsCd;
}
