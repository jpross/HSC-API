package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class Course extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = -5758224704791851065L;
	
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
	
	public String getCourseId() {
		return courseId;
	}

	public String getCourseNm() {
		return super.getNullToEmpty(courseNm);
	}
	
	public String getCourseSubtitleNm() {
		return super.getNullToEmpty(courseSubtitleNm);
	}
	
	public String getThmbPath() {
		return super.getNullToEmpty(thmbPath);
	}
	
	public String getCourseFeat() {
		return super.getNullToEmpty(courseFeat);
	}
	
	public String getCourseYear() {
		return super.getNullToEmpty(courseYear);
	}
	
	public String getCourseStartDt() {
		return super.getNullToEmpty(courseStartDt);
	}
	
	public String getCourseEndDt() {
		return super.getNullToEmpty(courseEndDt);
	}
	
	public String getCourseClsCd() {
		return super.getNullToEmpty(courseClsCd);
	}
	
	public String getStdyPeriod() {
		return super.getNullToEmpty(stdyPeriod);
	}
	
	public String getReviewPeriod() {
		return super.getNullToEmpty(reviewPeriod);
	}
	
	public String getLvlCd() {
		return super.getNullToEmpty(lvlCd);
	}
	
	public String getStdyTgCd() {
		return super.getNullToEmpty(stdyTgCd);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
}
