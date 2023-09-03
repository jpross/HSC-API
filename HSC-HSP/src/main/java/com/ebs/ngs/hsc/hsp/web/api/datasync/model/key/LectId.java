package com.ebs.ngs.hsc.hsp.web.api.datasync.model.key;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class LectId implements Serializable {

	private static final long serialVersionUID = 113126458126019404L;
	
	private final String lectId;

}
