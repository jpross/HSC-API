package com.ebs.ngs.hsc.hsp.web.api.datasync.model.key;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class LectIdAndIndexId implements Serializable {

	private static final long serialVersionUID = 1241385568191287805L;
	
	private final String lectId;
	private final String indexId;

}
