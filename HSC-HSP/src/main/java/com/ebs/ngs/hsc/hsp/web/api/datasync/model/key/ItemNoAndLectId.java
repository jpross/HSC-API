package com.ebs.ngs.hsc.hsp.web.api.datasync.model.key;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ItemNoAndLectId implements Serializable {

	private static final long serialVersionUID = 1128603824611153213L;
	
	private String itemNo;
	private String lectId;

}
