package com.xyd.main.mina.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xyd.main.XydServer;
import com.xyd.main.mina.Message;
import com.xyd.main.mina.ProtocalPack;
import com.xyd.util.ConnectEvent;
import com.xyd.util.Conver;

import cn.hutool.core.date.DateUtil;

public class ServerHandler extends IoHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);

	public static Map<Long, IoSession> ALLI_SESSIONS_MAPS = new HashMap<>();

	private ConnectEvent event;

	public ServerHandler() {
	}

	public ServerHandler(ConnectEvent event) {
		this.event = event;
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		super.sessionIdle(session, status);
		session.close(true);
	}

	/**
	 * 当一个客户端连接进入时
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		if (event != null)
			event.connect(session);
		ALLI_SESSIONS_MAPS = session.getService().getManagedSessions();
		logger.info("client connection from : " + session.getRemoteAddress());
		System.out.println("client connection from : " + session.getRemoteAddress());
	}

	/**
	 * 当一个客户端关闭时
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		if (event != null)
			event.disconnect(session);
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
		ProtocalPack pack = (ProtocalPack) message;
		Message bean = Conver.pack2Message(pack.getContent());
		if (bean != null) {
			session.setAttribute("bindId", bean.getmId());
			session.setAttribute("time", new Date().getTime());
			if ("M077".equals(bean.getCommond())) {
				System.out.println(DateUtil.now() + "收到回执  : msgId : " + bean.getMsgId() + " mac : " + bean.getmId() + "  commond :" + bean.getCommond() + " 回调执行成功。" + DateUtil.now());
				bean.setStatus(1);
				XydServer.MSG_MAP.put(bean.getMsgId(), bean);
			}
			session.write(pack);
		}
	}

}