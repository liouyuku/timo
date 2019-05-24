package com.linln.api.business.util.push;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.gson.JsonObject;
import com.linln.api.swagger.plugin.log.LogAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class JiGuangPush {

    public static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    private static final String APPKEY = "ad6f20e9d21f1ce17ebe70d8";
    private static final String MASTERSECRET = "5209b24324d721675a9ce2ea";

    private static final boolean PRODUCTION = true;

    public static void sendPush(final PushBean b, final String[] tag) {
        PushPayload p = PushPayload.newBuilder().setPlatform(Platform.android_ios())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.newBuilder().setAlert(b.getAlertContent())
                        .addPlatformNotification(AndroidNotification.newBuilder().setTitle(b.getTitle())
                                .addExtra("pushObject", toJsonObject(b)).build())
                        .addPlatformNotification(IosNotification.newBuilder().incrBadge(1).setSound("ring.mp3")
                                .addExtra("pushObject", toJsonObject(b)).build())
                        .build())
                .setMessage(Message.content(StringUtils.isEmpty(b.getMsgId()) ? "Empty message" : b.getMsg()))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(PRODUCTION).setTimeToLive(0)
                        .build())
                .build();
        sendPush(p);

    }

    public static void sendPush(final PushBean b) {


        if (StringUtils.isEmpty(b.getRegId())) {
            return;
        }

        // 极光推送
        IosNotification iOsnotification = IosNotification.newBuilder().setContentAvailable(true)
                .addExtra("pushObject", toJsonObject(b)).build();

        if ("2".equals(b.getType()) || "4".equals(b.getType())) {
            iOsnotification = IosNotification.newBuilder().setContentAvailable(true).incrBadge(0)
                    .setSound("ring.mp3").addExtra("pushObject", toJsonObject(b)).build();
        } else {
        }


        Audience audience = StringUtils.isEmpty(b.getRegId()) ? Audience.alias(b.getPhone())
                : Audience.registrationId(b.getRegId());


        PushPayload p = PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(audience)
                .setNotification(Notification.newBuilder().setAlert(b.getAlertContent())
                        .addPlatformNotification(AndroidNotification.newBuilder().setTitle(b.getTitle())
                                .addExtra("pushObject", toJsonObject(b)).build())
                        .addPlatformNotification(iOsnotification).build())
                .setMessage(Message.content(StringUtils.isEmpty(b.getMsg()) ? "Empty message" : b.getMsg()))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(Boolean.valueOf(PRODUCTION)).setTimeToLive(0)
                        .build())
                .build();
        sendPush(p);

    }

    public static String toFirstLetterUpperCase(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        String firstLetter = str.substring(0, 1).toUpperCase();
        return firstLetter + str.substring(1, str.length());
    }

    private static JsonObject toJsonObject(PushBean bean) {
        JsonObject result = new JsonObject();
        try {
            Field[] fields = bean.getClass().getDeclaredFields();

            for (Field field : fields) {
                String f = field.getName();
                String getMethodName = "";
                if (f.startsWith("m")) {
                    getMethodName = "get" + field.getName();
                } else {
                    getMethodName = "get" + toFirstLetterUpperCase(field.getName());
                }
                Object value = bean.getClass().getMethod(getMethodName).invoke(bean);

                if (value != null) {
                    result.addProperty(field.getName(), value.toString());
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings("deprecation")
    private static JPushClient jpushClient = new JPushClient(MASTERSECRET, APPKEY, 3);

    public static void sendPush(PushPayload payload) {
        try {
            System.out.println(jpushClient.sendPush(payload));
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
        }
    }
}
