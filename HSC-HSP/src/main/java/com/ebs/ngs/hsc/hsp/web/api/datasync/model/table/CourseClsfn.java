package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

<<<<<<< HEAD
import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class CourseClsfn extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = -1554281325802979709L;
	
	private String clsfnSystId;
	private String hgrnkClsfnSystId;
	private String clsfnSystNm;
	private String sortSeq;
	private String fmySiteDsCd;
	
	public String getClsfnSystId() {
		return clsfnSystId;
	}
	
	public String getHgrnkClsFnSystId() {
		return super.getNullToEmpty(hgrnkClsfnSystId);
	}
	
	public String getClsfnSystNm() {
		return super.getNullToEmpty(clsfnSystNm);
	}
	
	public String getSortSeq() {
		return super.getNullToEmpty(sortSeq);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
=======
import lombok.Data;

@Data
public class CourseClsfn {
	private String clsfnSystId;
	private String hgrnkClsfnSystId;
	private String clsfnSystNm;
	private String sortSeq;
	private String fmySiteDsCd;
>>>>>>> branch 'main' of https://github.com/jpross/HSC-API
}
