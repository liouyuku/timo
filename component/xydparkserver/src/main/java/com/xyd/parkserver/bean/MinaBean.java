package com.xyd.parkserver.bean;

import lombok.Data;

@Data
public class MinaBean {
	
	private String msg;
	private boolean isWebAccept=false;


	public MinaBean()
	{}
	
	public MinaBean(String msg)
	{
		this.msg  = msg;
	}
	
	@Override
	public String toString() {
		return "MinaBean [msg=" + msg + "]";
	}

}
