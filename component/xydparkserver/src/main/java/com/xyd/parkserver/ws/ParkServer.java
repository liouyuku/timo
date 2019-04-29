package com.xyd.parkserver.ws;

import java.net.InetSocketAddress;
import java.util.Map;

import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.xyd.parkserver.bean.MinaBean;
import com.xyd.parkserver.bean.ParkBean;

import cn.hutool.json.JSONUtil;

public final class ParkServer {

	public static void main(String[] args) {
		start(8081);
	}

	public static void start(int port) {
		try {
			// 服务器端的主要对象
			IoAcceptor acceptor = new NioSocketAcceptor();

			// 设置Filter链
			// acceptor.getFilterChain().addLast("ioFilter", new
			// IoFilterAdapter());
			acceptor.getFilterChain().addLast("coder", new ProtocolCodecFilter(new MinaEncoder(), new MinaDecoder()));

			// 设置消息处理类（创建、关闭Session，可读可写等等，继承自接口IoHandler）
			acceptor.setHandler(new MinaServerHandler());
			// 设置接收缓存区大小
			acceptor.getSessionConfig().setReadBufferSize(2048);
			acceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 60 * 60 * 2);

			// 服务器开始监听
			acceptor.bind(new InetSocketAddress(port));
			System.out.println("服务已启动");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static IoSession getSession(String pId) {
		Map<Long, IoSession> sessionMap = MinaServerHandler.ALLI_SESSIONS_MAPS;
		if (sessionMap == null) {
			return null;
		}
		for (IoSession session : sessionMap.values()) {
			if (pId.equals(session.getAttribute("bindId"))) {
				return session;
			}
		}
		return null;
	}

	public static boolean sendMessage2Park(String pId, ParkBean obj) {
		IoSession session = getSession(pId);
		if (session == null)
			return false;

		WriteFuture future = session.write(conver2PackMsg(obj));
		future.awaitUninterruptibly();
		if (future.getException() != null) {
			return false;
		}
		return true;
	}

	private static MinaBean conver2PackMsg(ParkBean obj) {
		MinaBean bean = new MinaBean(JSONUtil.toJsonStr(obj));
		return bean;
	}
}
