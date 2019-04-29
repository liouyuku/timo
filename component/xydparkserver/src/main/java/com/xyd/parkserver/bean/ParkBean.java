package com.xyd.parkserver.bean;

import lombok.Data;

@Data
public class ParkBean {

	private Integer parkid;
	private Integer workstationid;
	private Integer recordid;
	private Integer code;
	private Integer command;
	private Integer isask;
	private String check;
	private ParkDataBean data;
}
