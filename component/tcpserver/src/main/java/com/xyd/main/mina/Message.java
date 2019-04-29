package com.xyd.main.mina;

public class Message {

	public Message(String mId, String commond, String converType, String content) {
		super();
		this.mId = mId;
		this.commond = commond;
		this.converType = converType;
		this.content = content;
	}
	
	
	public Message() {
	}

	/**
	 * 机器ID
	 */
	private String mId;
	
	/**
	 * 消息ID
	 */
	private String msgId;

	/**
	 * 操作类型（命令）
	 */
	private String commond;

	/**
	 * 需要转换的类型
	 */
	private String converType;

	/**
	 * 
	 */
	private String content;
	
	/**
	 * 当前连接的唯一标识
	 */
	private String token;
	
	/**
	 * 状态,0：未读，1：已读 2：重发
	 */
	private Integer status = 0;
	
	/**
	 * 消息发送时间
	 */
	private Long createTime;
	

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getCommond() {
		return commond;
	}

	public void setCommond(String commond) {
		this.commond = commond;
	}

	public String getConverType() {
		return converType;
	}

	public void setConverType(String converType) {
		this.converType = converType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Message [mId=" + mId + ", msgId=" + msgId + ", commond=" + commond + ", converType=" + converType
				+ ", content=" + content + ", status=" + status + ", createTime=" + createTime + "]";
	}
	
}
