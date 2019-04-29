package com.xyd.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xyd.main.mina.CustomProtocalCodecFactory;
import com.xyd.main.mina.Message;
import com.xyd.main.mina.handler.ServerHandler;
import com.xyd.util.ConfigUtil4Port;
import com.xyd.util.ConnectEvent;
import com.xyd.util.Conver;
import com.xyd.util.MessageListener;

import cn.hutool.core.date.DateUtil;

public class XydServer {

	private static final int IDELTIMEOUT = 30 * 1000;

	private static Logger logger = LoggerFactory.getLogger(XydServer.class);

	public static ConcurrentHashMap<String, Message> MSG_MAP = new ConcurrentHashMap<String, Message>();

	private static ConnectEvent CONECTEVENT;

	
	/**
	 * 
	 * 
	 * @param event
	 */
	public static void start(ConnectEvent event) {
		try {
			logger.info("XydServer start start start");
			CONECTEVENT  = event;

			// 创建一个非阻塞的Server端Socket，用NIO
			SocketAcceptor acceptor = new NioSocketAcceptor();
			SocketSessionConfig sessionConfig = acceptor.getSessionConfig();
			sessionConfig.setReadBufferSize(10 * 2048);
			// 设置输入缓冲区的大小
			acceptor.getSessionConfig().setReceiveBufferSize(10 * 1024);
			// 设置输出缓冲区的大小
			acceptor.getSessionConfig().setSendBufferSize(1024 * 10);
			// 设置为非延迟发送，为true则不组装成大包发送，收到东西马上发出
			acceptor.getSessionConfig().setTcpNoDelay(true);
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, IDELTIMEOUT);

			DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
			chain.addLast("codec", new ProtocolCodecFilter(new CustomProtocalCodecFactory(Charset.forName("utf-8"))));
			chain.addLast("logger", new LoggingFilter());
			acceptor.setHandler(new ServerHandler(event));
			acceptor.bind(new InetSocketAddress(ConfigUtil4Port.getPort()));

			MessageListener ml = new MessageListener(event);
			ml.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("下位机消息服务已开启:" + ConfigUtil4Port.getPort());
	}

	public synchronized static boolean sendMessage(String mac, Message obj) {

		System.out.println(DateUtil.now() + "  发送消息 :  " + " msgId : " + obj.getMsgId() + " mac : " + obj.getmId() + "  commond :" + obj.getCommond()
				+ " 开始执行:" + DateUtil.now());

		obj.setCreateTime(DateUtil.currentSeconds());
		List<IoSession> sessions = getIoSession(mac);

		if (sessions.isEmpty()) {
			CONECTEVENT.onFailure(obj);
			return false;
		}

		XydServer.MSG_MAP.put(obj.getMsgId(), obj);

		for (IoSession ioSession : sessions) {
			WriteFuture future = ioSession.write(Conver.message2Pack(obj));
			future.awaitUninterruptibly();
			if (future.getException() != null) {
				return false;
			}
		}
		return true;
	}

	private static List<IoSession> getIoSession(String mId) {

		Map<Long, IoSession> sessionMap = ServerHandler.ALLI_SESSIONS_MAPS;
		List<IoSession> sessionlist = new ArrayList<IoSession>();

		if (sessionMap.isEmpty() || sessionMap.values().isEmpty()) {
			System.out.println("session list empty");
			return new ArrayList<IoSession>();
		}
		for (IoSession session : sessionMap.values()) {
			if (session.getAttribute("bindId") == null)
				continue;
			String sMid = session.getAttribute("bindId").toString().toLowerCase();
			if (mId.toLowerCase().equals(sMid)) {
				sessionlist.add(session);
			}
		}

		return sessionlist;
	}

	public static boolean isOnLine(String mId) {
		return getIoSession(mId) != null;
	}
}
