package com.xyd.util;

import com.xyd.main.mina.Message;
import com.xyd.main.mina.ProtocalPack;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class Conver {

	public static ProtocalPack message2Pack(Message msg) {
		JSONObject jsonobj = JSONUtil.parseObj(msg);
		String jsonstr = JSONUtil.toJsonStr(jsonobj);
		ProtocalPack pack = new ProtocalPack(jsonstr);
		return pack;
	}

	public static Message pack2Message(String conten) {
		return JSONUtil.toBean(conten, Message.class);
	}

}
