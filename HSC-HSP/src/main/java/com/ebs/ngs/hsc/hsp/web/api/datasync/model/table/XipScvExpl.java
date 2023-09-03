package com.ebs.ngs.hsc.hsp.web.api.datasync.model.table;

<<<<<<< HEAD
import java.io.Serializable;

import com.ebs.ngs.hsc.hsp.web.api.datasync.model.DataSyncModel;

import lombok.Setter;

@Setter
public class XipScvExpl extends DataSyncModel implements Serializable {
	
	private static final long serialVersionUID = -5709898108210447438L;

	private String itemNo;
	private String lectId;
	private String startTime;
	
	public String getItemNo() {
		return itemNo;
	}
	
	public String getLectId() {
		return lectId;
	}
	
	public String getStartTime() {
		return super.getNullToEmpty(startTime);
	}
	
=======
import lombok.Data;

@Data
public class XipScvExpl {
	private String prbmNoitemNo;
	private String lecIdlectId;
	private String explTmstartTime;
>>>>>>> branch 'main' of https://github.com/jpross/HSC-API
}
