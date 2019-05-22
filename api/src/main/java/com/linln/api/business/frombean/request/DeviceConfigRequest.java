package com.linln.api.business.frombean.request;

import lombok.Data;

@Data
public class DeviceConfigRequest {

    // 视频呼叫超时时长
    private Float agoraTimeout;
    // 视频通话时长
    private Float agoraDuration;
    // sip连接超时时长
    private Float sipTimeout;
    // sip连接时长
    private Float sipDuration;
}
