package com.xyd.control.main.mina.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends IoHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);

	public static Map<Long, IoSession> ALLI_SESSIONS_MAPS = new HashMap<>();

	public ServerHandler() {

	}

	/**
	 * 当一个客户端连接进入时
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		ALLI_SESSIONS_MAPS = session.getService().getManagedSessions();
		System.out.println("client connection from : " + session.getRemoteAddress());
	}

	/**
	 * 当一个客户端关闭时
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("session closed " + session.getAttribute("bindId"));
		logger.info("session closed " + session.getAttribute("bindId"));
	}

	/**
	 * 当接收到客户端的信息
	 * 
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String mac = new String((byte[])message);
		session.setAttribute("bindId", mac);
		session.setAttribute("time", new Date().getTime());
	}

}