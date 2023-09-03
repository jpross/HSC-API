package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class LectAdtlInfo extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = 5448546218354751282L;

	private String lectId;
	private String otYn;
	private String sbjtId;
	private String openYn;
	private String fmySiteDsCd;
	
	public String getLectId() {
		return lectId;
	}
	
	public String getOtYn() {
		return super.getEmptyToN(otYn);
	}
	
	public String getSbjtId() {
		return super.getNullToEmpty(sbjtId);
	}
	
	public String getOpenYn() {
		return super.getEmptyToN(openYn);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
}
