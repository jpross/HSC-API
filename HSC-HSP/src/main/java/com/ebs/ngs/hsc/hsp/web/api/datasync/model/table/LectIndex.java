package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

<<<<<<< HEAD
import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class LectIndex extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = -8387455287549284867L;

	private String lectId;
	private String indexId;
	private String indexNm;
	private String ecdFileVodTime;
	private String startTime;
	private String endTime;
	private String fmySiteDsCd;
	private String indexType;
	private String questNo;
	private String txbkPageNo;

	public String getLectId() {
		return lectId;
	}
	
	public String getIndexId() {
		return indexId;
	}
	
	public String getIndexNm() {
		return super.getNullToEmpty(indexNm);
	}
	
	public String getEcdFileVodTime() {
		return super.getNullToEmpty(ecdFileVodTime);
	}
	
	public String getStartTime() {
		return super.getNullToEmpty(startTime);
	}
	
	public String getEndTime() {
		return super.getNullToEmpty(endTime);
	}
	
	public String getFmySiteDsCd() {
		return super.getFmySiteDsCd(fmySiteDsCd);
	}

	public String getIndexType() {
		return super.getNullToEmpty(indexType);
	}
	
	public String getQuestNo() {
		return super.getNullToEmpty(questNo);
	}
	
	public String getTxbkPageNo() {
		return super.getNullToEmpty(txbkPageNo);
	}
	
=======
import lombok.Data;

@Data
public class LectIndex {
	private String lectId;
	private String lectItemId;
	private String clipNm;
	private String ecdFileVodTime;
	private String startTime;
	private String endTime;
	private String fmySiteDsCd;
>>>>>>> branch 'main' of https://github.com/jpross/HSC-API
}
