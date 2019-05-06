package com.linln.push;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.foxinmy.umeng4j.ApiResult;
import com.foxinmy.umeng4j.UmengProxy;
import com.foxinmy.umeng4j.cast.UmengCast;
import com.foxinmy.umeng4j.exception.UmengException;
import com.foxinmy.umeng4j.payload.AndroidPayload;
import com.foxinmy.umeng4j.payload.IOSPayload;
import com.foxinmy.umeng4j.payload.Payload;
import com.foxinmy.umeng4j.type.CastType;
import com.foxinmy.umeng4j.type.PayloadType;
import com.linln.push.bean.PushBean;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;

public class PushUtil {

	private static PushUtil singObj = new PushUtil();

	private static UmengProxy umengProxy = new UmengProxy();

	private PushUtil() {
		umengProxy = new UmengProxy();
	}

	public static PushUtil getSingleInstance() {
		return singObj;
	}

	private void setMsgId(PushBean b) {
		String msgId = "MSG" + DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
		b.setMMsgId(msgId);
	}

	public ApiResult sendPush(final PushBean b, final String[] tag) {
		return push(b);
	}

	public ApiResult sendToUser(final PushBean b, final String phone) {
		return push(b);
	}

	public ApiResult sendToDevicer(final PushBean b, final String deviceId) {
		return push(b);
	}

	private ApiResult push(PushBean b) {
		try {
			setMsgId(b);
			Payload payload = null;
			if (PushBean.DEVICE_TYPE_IOS.equals(b.getDeviceType())) {
				payload = new IOSPayload(b.getAlertContent()).keyValue(null);
			} else {
				payload = new AndroidPayload(b.getAlertContent(), b.getAlertContent(), b.getAlertContent())
						.displayType(PayloadType.MESSAGE).extra(null);
			}
			
			Cast cast = new Cast(CastType.UNICAST, payload);
			cast.setAlias(b.getPhone());
			return umengProxy.pushMessage(cast);
		} catch (UmengException e) {
			e.printStackTrace();
		}
		return null;

	}
}

class Cast extends UmengCast {

	private static String APP_KEY = "5cce4c600cafb297ca000bcd";

	public Cast(CastType castType, Payload payload) {
		super(castType, payload);
		this.setAppkey(APP_KEY);
	}
}
