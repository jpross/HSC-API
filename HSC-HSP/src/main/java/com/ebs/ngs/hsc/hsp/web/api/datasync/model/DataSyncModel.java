package com.ebs.ngs.hsc.hsp.web.api.datasync.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class DataSyncModel {
	
	@JsonIgnore
	protected String getFmySiteDsCd(String fmySiteDsCd) {
		if (fmySiteDsCd == null) {
			return "";
		}
		if ("EBSI".equals(fmySiteDsCd)) {
			return "HSC";
		}
		if ("PRIM".equals(fmySiteDsCd)) {
			return "PRI";
		}
		if ("MIDL".equals(fmySiteDsCd)) {
			return "JHS";
		}
		return null;
	}
	
	@JsonIgnore
	protected String getNullToEmpty(String value) {
		return value == null ? "" : value;
	}
	
	@JsonIgnore
	protected String getEmptyToY(String value) {
		if (value == null || "".equals(value)) {
			return "Y";
		}
		return value;
	}
	
	@JsonIgnore
	protected String getEmptyToN(String value) {
		if (value == null || "".equals(value)) {
			return "N";
		}
		return value;
	}

}
