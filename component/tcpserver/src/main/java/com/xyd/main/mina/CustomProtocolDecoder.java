package com.xyd.main.mina;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import cn.hutool.core.util.StrUtil;

public class CustomProtocolDecoder implements ProtocolDecoder {

	private final AttributeKey CONTEXT = new AttributeKey(getClass(), "context");

	private final Charset charset;

	private int maxPackLength = 512;

	public CustomProtocolDecoder() {
		this(Charset.defaultCharset());
	}

	public CustomProtocolDecoder(Charset charset) {
		this.charset = charset;
	}

	public int getMaxLineLength() {
		return maxPackLength;
	}

	public void setMaxLineLength(int maxLineLength) {
		if (maxLineLength <= 0) {
			throw new IllegalArgumentException("maxLineLength: " + maxLineLength);
		}
		this.maxPackLength = maxLineLength;
	}

	private Context getContext(IoSession session) {

		Context ctx = (Context) session.getAttribute(CONTEXT);
		if (ctx == null) {
			ctx = new Context();
			session.setAttribute(CONTEXT, ctx);
		}
		return ctx;
	}
	
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		final int packHeadLength = 4;
		// 先获取上次的处理上下文，其中可能有未处理完的数据
		Context ctx = getContext(session);
		// 先把当前buffer中的数据追加到Context的buffer当中
		ctx.append(in);
		// 把position指向0位置，把limit指向原来的position位置
		IoBuffer buf = ctx.getBuffer();
		buf.flip();
		// 然后按数据包的协议进行读取
		while (buf.remaining() >= packHeadLength) {
			buf.mark();
			// 读取消息头部分
			int length = buf.getInt();
			// 检查读取的包头是否正常，不正常的话清空buffer
			if (length < 0 || length > maxPackLength) {
				buf.clear();
				break;
			}
			// 读取正常的消息包，并写入输出流中，以便IoHandler进行处理
			else if (length >= packHeadLength && length <= buf.remaining()) {
				int oldLimit2 = buf.limit();
				buf.limit(buf.position() + length);
				String content = buf.getString(ctx.getDecoder());
				if (StrUtil.isNotEmpty(content)) {
					buf.limit(oldLimit2);
					ProtocalPack pack = new ProtocalPack(content);
					out.write(pack);
				} else {
					// 获取消息为空时代表此消息有问题，将BUFF重置，以免影响后续消息的传递.
					buf.reset();
					break;
				}

			} else {
				// 如果消息包不完整
				// 将指针重新移动消息头的起始位置
				buf.reset();
				break;
			}
		}
		if (buf.hasRemaining()) {
			// 将数据移到buffer的最前面
			IoBuffer temp = IoBuffer.allocate(maxPackLength).setAutoExpand(false);
			temp.put(buf);
			temp.flip();
			buf.clear();
			buf.put(temp);
		} else {// 如果数据已经处理完毕，进行清空
			buf.clear();
		}

	}

	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
	}

	public void dispose(IoSession session) throws Exception {
		Context ctx = (Context) session.getAttribute(CONTEXT);
		if (ctx != null) {
			session.removeAttribute(CONTEXT);
		}
	}

	@SuppressWarnings("unused")
	private class Context {
		private final CharsetDecoder decoder;
		private IoBuffer buf;
		private int matchCount = 0;
		private int overflowPosition = 0;
		private int index = 0;

		private Context() {
			decoder = charset.newDecoder();
			buf = IoBuffer.allocate(1024).setAutoExpand(false).setAutoShrink(true);
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int i) {
			this.index = i;
		}

		public CharsetDecoder getDecoder() {
			return decoder;
		}

		public IoBuffer getBuffer() {
			return buf;
		}

		public int getOverflowPosition() {
			return overflowPosition;
		}

		public int getMatchCount() {
			return matchCount;
		}

		public void setMatchCount(int matchCount) {
			this.matchCount = matchCount;
		}

		public void reset() {
			overflowPosition = 0;
			matchCount = 0;
			decoder.reset();
		}

		public void append(IoBuffer in) {
			getBuffer().put(in);

		}

	}
}
