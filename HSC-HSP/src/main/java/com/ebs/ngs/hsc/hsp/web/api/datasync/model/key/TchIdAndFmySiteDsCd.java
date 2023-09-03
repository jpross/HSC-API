package com.ebs.ngs.hsc.hsp.web.api.datasync.model.key;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class TchIdAndFmySiteDsCd implements Serializable {

	private static final long serialVersionUID = 8803217657592769556L;
	
	private final String tchId;
	
	private final String fmySiteDsCd;

}
