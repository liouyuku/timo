package com.linln.api.business.frombean.request;

import lombok.Data;

@Data
public class DeviceConfigRequest {

    // 视频呼叫超时时长
    private Integer agoraTimeout;
    // 视频通话时长
    private Integer agoraDuration;
    // sip连接超时时长
    private Integer sipTimeout;
    // sip连接时长
    private Integer sipDuration;
}
