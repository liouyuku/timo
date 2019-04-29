package com.xyd.parkserver.ws;

import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.xyd.parkserver.bean.MinaBean;
import com.xyd.parkserver.util.WebSocketUtil;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class MinaServerHandler extends IoHandlerAdapter {

	private String allMsg = "";

	private boolean flag = false;

	public static Map<Long, IoSession> ALLI_SESSIONS_MAPS;

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.out.println("[exceptionCaught] " + (cause != null ? cause.getMessage() : ""));
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
		System.out.println("[messageSent] [" + session.getRemoteAddress() + "] " + message.toString());
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		System.out.println("[messageReceived] " + message.toString());

		MinaBean minaBean = (MinaBean) message;
		if (minaBean.isWebAccept() && message.toString().contains("Sec-WebSocket-Key")) {
			MinaBean sendMessage = minaBean;
			sendMessage.setMsg(WebSocketUtil.getSecWebSocketAccept(minaBean.getMsg()));
			session.write(sendMessage);
		} else {
			String msg = minaBean.getMsg();
			if (msg == null || "".equals(msg.trim())) {
				return;
			}

			int end = msg.lastIndexOf("}");

			if (end == -1) {
				allMsg += msg;
				return;
			} else {
				flag = true;
			}

			if (flag) {
				allMsg = allMsg + msg;
				allMsg = allMsg.substring(allMsg.indexOf("{"), allMsg.lastIndexOf("}") + 1);
			}

			JSONObject bean = JSONUtil.parseObj(allMsg);

			if (bean == null)
				return;

			JSONObject data = bean.getJSONObject("data");
			Console.log("上传数据 ： {}", data);

			flag = false;
			allMsg = "";

		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		System.out.println("[sessionClosed]");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		super.sessionIdle(session, status);
		System.out.println("[sessionIdle] " + status.toString() + "," + session.getRemoteAddress());
		session.close(false);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
		System.out.println("[sessionCreated]");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		ALLI_SESSIONS_MAPS = session.getService().getManagedSessions();
		System.out.println("[sessionOpened] " + session.getRemoteAddress());
	}
}
