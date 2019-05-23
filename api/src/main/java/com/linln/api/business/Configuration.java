package com.linln.api.business;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "xyd")
@Component
@Data
public class Configuration {

    /**
     * andorid更新配置文件
     */
    private String android;

    /**
     * IOS更新配置文件
     */
    private String ios;

    /**
     * 下位机更新配置文件
     */
    private String device;

    /**
     * 雲通訊相關賬號
     */
    private String ccp_sid;

    private String ccp_token;

    private String ccp_appId;


}
