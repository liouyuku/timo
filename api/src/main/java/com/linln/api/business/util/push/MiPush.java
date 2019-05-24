package com.linln.api.business.util.push;

import com.google.gson.Gson;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

import java.util.List;

public class MiPush {

    private static final String MASTERSECRET = "L52P1R5F5CBgy1Dv+8R/lg==";

    private static final String PACKAGENAME = "com.xingyeda.ehome";


    private static final Sender sender = new Sender(MASTERSECRET);

    private static Gson gson = new Gson();

    public static Message buildAndoridMessage(PushBean bean) throws Exception {
        Message message = new Message.Builder().title(bean.getTitle()).description(bean.getAlertContent()).payload(null == bean.getMsg() ? "empty" : bean.getMsg())
                .restrictedPackageName(PACKAGENAME).passThrough(1) // 消息使用透传方式
                .notifyType(1) // 使用默认提示音提示
                .extra("flow_control", "4000")// 设置平滑推送, 推送速度4000每秒(qps=4000)
                .extra("pushObject", gson.toJson(bean))
                .build();
        return message;
    }

    public static Message buildIOSMessage(PushBean bean) throws Exception {
        Message message = new Message.IOSBuilder().description(bean.getAlertContent()).soundURL("ring.mp3") // 消息铃声
                .badge(1) // 数字角标
                .category("action") // 快速回复类别
                .extra("pushObject", gson.toJson(bean))// 自定义键值对
                .extra("flow_control", "4000") // 设置平滑推送, 推送速度4000每秒(qps=4000)
                .build();
        return message;
    }

    public static Result sendMessageToAlias(Message message, String alias) throws Exception {
        return sender.sendToAlias(message, alias, 0); //根据alias，发送消息到指定设备上，不重试。
    }

    public static Result sendMessageToRegID(Message message, String regId) throws Exception {
        return sender.send(message, regId, 0); //根据regID，发送消息到指定设备上，不重试。
    }

    public static Result sendMessageToRegID(Message message, List<String> tag) throws Exception {
        return sender.send(message, tag, 0); //根据regID，发送消息到指定设备上，不重试。
    }
}
