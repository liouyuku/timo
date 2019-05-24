package com.linln.api.business.frombean.response;

import lombok.Data;

@Data
public class VerificationSMSResponse {

    public VerificationSMSResponse(String code, String sessionId) {
        this.code = code;
        this.sessionId = sessionId;
    }

    private String code;

    private String sessionId;
}
