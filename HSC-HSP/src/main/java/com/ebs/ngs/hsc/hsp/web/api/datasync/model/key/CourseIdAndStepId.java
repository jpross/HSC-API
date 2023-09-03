package com.ebs.ngs.hsc.hsp.web.api.datasync.model.key;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CourseIdAndStepId implements Serializable {

	private static final long serialVersionUID = -5354356954241175060L;
	
	private String courseId;
	private String stepId;

}
