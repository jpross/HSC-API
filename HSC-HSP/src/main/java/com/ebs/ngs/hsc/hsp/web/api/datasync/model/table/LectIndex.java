package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

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
}
