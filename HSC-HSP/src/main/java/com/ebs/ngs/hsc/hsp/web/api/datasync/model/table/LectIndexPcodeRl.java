package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class LectIndexPcodeRl extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = 3302812827365977489L;

	private String lectId;
	private String indexId;
	private String sortSeq;
	private String prbmCatId;
	private String pcode;
	private String fmySiteDsCd;
	private String kwd;
	private String cntnExp;
	private String ldfcy;
	private String itemId;
	private String hgrnkClsfnSystId;
	private String clsfnSystPhs;
	private String clsfnSystNm;
	
	public String getLectId() {
		return lectId;
	}
	
	public String getIndexId() {
		return indexId;
	}
	
	public String getSortSeq() {
		return sortSeq;
	}
	
	public String getPrbmCatId() {
		return super.getNullToEmpty(prbmCatId);
	}
	
	public String getPcode() {
		return super.getNullToEmpty(pcode);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
	public String getKwd() {
		return super.getNullToEmpty(kwd);
	}
	
	public String getCntnExp() {
		return super.getNullToEmpty(cntnExp);
	}
	
	public String getLdfcy() {
		return super.getNullToEmpty(ldfcy);
	}
	
	public String getItemId() {
		return super.getNullToEmpty(itemId);
	}
	
	public String getHgrnkClsfnSystId() {
		return super.getNullToEmpty(hgrnkClsfnSystId);
	}
	
	public String getClsfnSystPhs() {
		return super.getNullToEmpty(clsfnSystPhs);
	}
	
	public String getClsfnSystNm() {
		return super.getNullToEmpty(clsfnSystNm);
	}

}
