package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class Lect extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = -5260470019236757136L;

	private String lectId;
	private String lectNm;
	private String lectSno;
	private String thmbPath;
	private String lectExp;
	private String lectSuplExpCntn;
	private String brdcDt;
	private String lectLoadDt;
	private String opnCpltDt;
	private String shwStartDtm;
	private String lectClsCd;
	private String lectIntroYn;
	private String plyrUseYn;
	private String ecdFileVodTime;
	private String fileId;
	private String fmySiteDsCd;
	
	public String getLectId() {
		return lectId;
	}
	
	public String getLectNm() {
		return super.getNullToEmpty(lectNm);
	}
	
	public String getLectSno() {
		return super.getNullToEmpty(lectSno);
	}
	
	public String getThmbPath() {
		return super.getNullToEmpty(thmbPath);
	}
	
	public String getLectExp() {
		return super.getNullToEmpty(lectExp);
	}
	
	public String getLectSuplExpCntn() {
		return super.getNullToEmpty(lectSuplExpCntn);
	}
	
	public String getBrdcDt() {
		return super.getNullToEmpty(brdcDt);
	}
	
	public String getLectLoadDt() {
		return super.getNullToEmpty(lectLoadDt);
	}
	
	public String getOpnCpltDt() {
		return super.getNullToEmpty(opnCpltDt);
	}
	
	public String getShwStartDtm() {
		return super.getNullToEmpty(shwStartDtm);
	}
	
	public String getLectClsCd() {
		return super.getNullToEmpty(lectClsCd);
	}
	
	public String getLectIntroYn() {
		return super.getEmptyToN(lectIntroYn);
	}
	
	public String getPlyrUseYn() {
		return super.getEmptyToN(plyrUseYn);
	}
	
	public String getEcdFileVodTime() {
		return super.getNullToEmpty(ecdFileVodTime);
	}
	
	public String getFileId() {
		return super.getNullToEmpty(fileId);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
}
