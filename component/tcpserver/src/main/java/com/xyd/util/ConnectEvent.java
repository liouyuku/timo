package com.xyd.util;

import org.apache.mina.core.session.IoSession;

import com.xyd.main.mina.Message;

public interface ConnectEvent {
	
	void disconnect(IoSession session);
	
	void connect(IoSession session);
	
	void onMessage(Message msg);
	
	void onFailure(Message msgId);
	
}
