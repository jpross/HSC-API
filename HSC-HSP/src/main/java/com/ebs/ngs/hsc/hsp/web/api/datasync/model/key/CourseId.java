package com.ebs.ngs.hsc.hsp.web.api.datasync.model.key;

import lombok.Setter;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Setter
public class CourseId implements Serializable {

	private static final long serialVersionUID = 2528802731869623690L;

	private final String courseId;
	
}
