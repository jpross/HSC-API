package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class Tch extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = 3239893867542757896L;

	private String tchId;
	private String fmySiteDsCd;
	private String tchNm;
	private String tchDsCd;
	
	public String getTchId() {
		return tchId;
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
	public String getTchNm() {
		return super.getNullToEmpty(tchNm);
	}
	
	public String getTchDsCd() {
		return super.getNullToEmpty(tchDsCd);
	}

}
