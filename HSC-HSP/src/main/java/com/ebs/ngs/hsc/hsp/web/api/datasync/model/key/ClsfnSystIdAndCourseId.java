package com.ebs.ngs.hsc.hsp.web.api.datasync.model.key;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClsfnSystIdAndCourseId implements Serializable {
	
	private static final long serialVersionUID = 3717129094826649498L;

	private String clsfnSystId;
	
	private String courseId;

}
