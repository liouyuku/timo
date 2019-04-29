package com.xyd.parkserver.ws;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.DemuxingProtocolEncoder;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.xyd.parkserver.bean.MinaBean;
import com.xyd.parkserver.util.WebSocketUtil;

//编码器
public class MinaEncoder extends DemuxingProtocolEncoder {
	public MinaEncoder() {
		addMessageEncoder(MinaBean.class, new BaseSocketBeanEncoder());
	}

	class BaseSocketBeanEncoder implements MessageEncoder<MinaBean> {
		public void encode(IoSession session, MinaBean message, ProtocolEncoderOutput out) throws Exception {
			byte[] _protocol = null;
			if (message.isWebAccept()) {
				_protocol = message.getMsg().getBytes("UTF-8");
			} else {
				_protocol = WebSocketUtil.encode(message.getMsg());
			}
			int length = _protocol.length;
			IoBuffer buffer = IoBuffer.allocate(length);
			buffer.put(_protocol);
			buffer.flip();
			out.write(buffer);
		}
	}
}