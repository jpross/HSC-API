package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class TchAdtlInfo extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = 2759705641903589833L;

	private String tchId;
	private String fmySiteDsCd;
	private String shwYn;
	private String imgUrl;
	private String mainHtml;
	private String actYn;
	private String toryCd;
	private String dstgCd;
	
	public String getTchId() {
		return tchId;
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}
	
	public String getShwYn() {
		return super.getEmptyToY(shwYn);
	}
	
	public String getImgUrl() {
		return super.getNullToEmpty(imgUrl);
	}
	
	public String getMainHtml() {
		return super.getNullToEmpty(mainHtml);
	}
	
	public String getActYn() {
		return super.getEmptyToY(actYn);
	}
	
	public String getToryCd() {
		return super.getNullToEmpty(toryCd);
	}
	
	public String getDstgCd() {
		return super.getNullToEmpty(dstgCd);
	}

}
