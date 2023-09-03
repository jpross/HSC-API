package com.ebs.ngs.hsc.hsp.web.api.datasync.model.key;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class StepIdAndLectId implements Serializable {

	private static final long serialVersionUID = -6672816897080491011L;
	
	private final String stepId;
	private final String lectId;

}
