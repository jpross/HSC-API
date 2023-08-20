package com.ebs.ngs.hsc.hsp.web.api.datasync.model;

import lombok.Data;

@Data
public class Lect {
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
}
