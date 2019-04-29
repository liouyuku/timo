package com.xyd.util;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.xyd.main.XydServer;
import com.xyd.main.mina.Message;

import cn.hutool.core.date.DateUtil;

public class MessageListener extends Thread {
	
	private ConnectEvent event;

	public MessageListener(ConnectEvent event) {
		this.event = event;
	}

	@Override
	public void run() {
		super.run();
		ConcurrentHashMap<String, Message> map = XydServer.MSG_MAP;
		try {
			// 每500毫秒迭代一次 ，看当前消息有没有被改变状态 。若还是没有改变则代表消息未发送成功
			while (true) {
				Thread.sleep(500);
				Iterator<String> it = map.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					Message value = map.get(key);
					if(DateUtil.currentSeconds() - value.getCreateTime() > 2)
					{
						
						if (value.getStatus() == 0) {
							System.out.println(DateUtil.now() + ":发送失败----" + "   msgid: " + value.getMsgId() + "  status : " + value.getStatus());
							event.onFailure(value);
						} else {
							System.out.println(DateUtil.now() + ":发送成功----" + "   msgid: " + value.getMsgId() + "  status : " + value.getStatus());
							event.onMessage(value);
						}
						it.remove();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
