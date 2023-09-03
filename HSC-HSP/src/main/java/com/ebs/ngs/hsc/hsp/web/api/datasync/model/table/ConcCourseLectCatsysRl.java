package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

<<<<<<< HEAD
import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class ConcCourseLectCatsysRl extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = -895032465681546786L;

	private String courseId;
	private String lectId;
	private String prbmCatId;
	private String ar;
	private String subj;
	private String larcat;
	private String ingcat;
	private String fmySiteDsCd;
	private String hgrnkClsfnSystId;
	private String botmClsfnSystYn;
	private String clsfnSystPhs;
	private String sortSeq;
	private String clsfnSystNm1;
	private String clsfnSystNm2;
	private String clsfnSystNm3;
	private String clsfnSystNm4;
	private String clsfnSystNm5;
	private String clsfnSystNm6;
	
	public String getCourseId() {
		return courseId;
	}
	
	public String getLectId() {
		return lectId;
	}
	
	public String getPrbmCatId() {
		return super.getNullToEmpty(prbmCatId);
	}
	
	public String getAr() {
		return super.getNullToEmpty(ar);
	}
	
	public String getSubj() {
		return super.getNullToEmpty(subj);
	}
	
	public String getLarcat() {
		return super.getNullToEmpty(larcat);
	}
	
	public String getIngcat() {
		return super.getNullToEmpty(ingcat);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
	public String getHgrnkClsfnSystId() {
		return super.getNullToEmpty(hgrnkClsfnSystId);
	}

	public String getBotmClsfnSystYn() {
		return super.getNullToEmpty(botmClsfnSystYn);
	}

	public String getClsfnSystPhs() {
		return super.getNullToEmpty(clsfnSystPhs);
	}

	public String getSortSeq() {
		return super.getNullToEmpty(sortSeq);
	}

	public String getClsfnSystNm1() {
		return super.getNullToEmpty(clsfnSystNm1);
	}

	public String getClsfnSystNm2() {
		return super.getNullToEmpty(clsfnSystNm2);
	}

	public String getClsfnSystNm3() {
		return super.getNullToEmpty(clsfnSystNm3);
	}

	public String getClsfnSystNm4() {
		return super.getNullToEmpty(clsfnSystNm4);
	}

	public String getClsfnSystNm5() {
		return super.getNullToEmpty(clsfnSystNm5);
	}

	public String getClsfnSystNm6() {
		return super.getNullToEmpty(clsfnSystNm6);
	}
	
=======
import lombok.Data;

@Data
public class ConcCourseLectCatsysRl {
	private String courseId;
	private String lectId;
	private String prbmCatId;
	private String ar;
	private String subj;
	private String larcat;
	private String ingcat;
	private String fmySiteDsCd;
	private String hgrnkClsfnSystId;
	private String botmClsfnSystYn;
	private String clsfnSystPhs;
	private String sortSeq;
	private String clsfnSystNm1;
	private String clsfnSystNm2;
	private String clsfnSystNm3;
	private String clsfnSystNm4;
	private String clsfnSystNm5;
	private String clsfnSystNm6;
>>>>>>> branch 'main' of https://github.com/jpross/HSC-API
}
