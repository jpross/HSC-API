package com.ebs.ngs.hsc.hsp.web.api.datasync.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hwpparser.edenit.Arrays;

public class TableSelector {
	
	private static final Map<String, List<String>> CONTAINER = new HashMap<>();
	
	public static List<String> getTable(String tableCd) {
		
		List<String> tableList = CONTAINER.get(tableCd);
		
		if (tableList == null) {	
			tableList = getTableList(tableCd);
			CONTAINER.put(tableCd, tableList);
		}
		
		return tableList;
		
	}
	
	public static List<String> getTableList(String tableCd) {
		switch (tableCd) {
			case "001":
				return Arrays.asList("course");
			case "002":
				return Arrays.asList("courseClsfnRl.hsc", "courseClsfnRl.jhs");
			case "003":
				return Arrays.asList("courseClsfn.hsc", "courseClsfn.jhs");
			case "004":
				return Arrays.asList("courseAdtlInfo");
			case "005":
				return Arrays.asList("eduCourse");
			case "006":
				return Arrays.asList("courseTchRl");
			case "007":
				return Arrays.asList("tch");
			case "008":
				return Arrays.asList("tchAdtlInfo");
			case "009":
				return Arrays.asList("courseStepRl");
			case "010":
				return Arrays.asList("step");
			case "011":
				return Arrays.asList("stepLectRl");
			case "012":
				return Arrays.asList("lect");
			case "013":
				return Arrays.asList("lectAdtlInfo");
			case "014":
				return Arrays.asList("lectIndex.hsc", "lectIndex.scvExpl");
			case "015":
				return Arrays.asList("concCourseLectCatsysRl.hsc", "concCourseLectCatsysRl.jhs");
			case "023":
				return Arrays.asList("xipScvExpl");
			case "024":
				return Arrays.asList("lectIndexPcodeRl");
			default:
				return Arrays.asList("");
		}
	}

}
