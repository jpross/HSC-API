package com.ebs.ngs.hsc.hsp.web.api.datasync.model.key;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CourseIdAndTchId implements Serializable {
	
	private static final long serialVersionUID = 6618468294132743585L;
	
	private final String courseId;
	
	private final String tchId;

}
