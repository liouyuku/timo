package com.xyd.control.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.xyd.control.main.mina.ByteArrayCodecFactory;
import com.xyd.control.main.mina.handler.ServerHandler;
import com.xyd.control.util.ConfigUtil4Port;
import com.xyd.control.util.NumberUtil;

public class ControlServer {

	/** 60秒后超时 */
	private static final int IDELTIMEOUT = 60;
	
	
	public static void main(String[] args) {
		ControlServer.start();
	}

	private static Logger logger = LoggerFactory.getLogger(ControlServer.class);

	public static void start() {
		try {
			logger.info("ControlServer start start start");

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
			chain.addLast("codec", new ProtocolCodecFilter(new ByteArrayCodecFactory()));
			chain.addLast("logger", new LoggingFilter());
			acceptor.setHandler(new ServerHandler());
			acceptor.bind(new InetSocketAddress(ConfigUtil4Port.getPort()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("消息服务已开启:" + ConfigUtil4Port.getPort());
	}

	/**
	 * 呼叫电梯
	 * 
	 * @param mac
	 *            串口服务器MAC地址
	 * @param callInfo
	 *            门牌号
	 * @return
	 */
	public synchronized static boolean sendMessage(String mac, String callInfo) {
		List<IoSession> sessions = getIoSession(mac);
		if (sessions.isEmpty())
			return false;
		StringBuilder sb = new StringBuilder();
		// 00 为对讲呼电梯
		// 01 为室内机招梯
		// 02 为门禁开锁联动电梯
		String linkage = "00";
		sb.append("FE").append(linkage).append("00").append(callInfo.substring(0, 2)).append(callInfo.substring(2, 4))
				.append("90300304000000");
		byte[] data = NumberUtil.toBytes(sb.toString());
		System.out.println("send message : " + NumberUtil.bytesToHex(data) + " session size : " + sessions.size());
		for (IoSession ioSession : sessions) {
			WriteFuture future = ioSession.write(data);
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

}
