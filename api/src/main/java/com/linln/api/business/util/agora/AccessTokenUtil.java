package com.linln.api.business.util.agora;

public class AccessTokenUtil {
    private String appId = "74abb84dfb984ec98e25d910c8ca0185";
    private String appCertificate = "d3fd581c0b5649f68a5644fd8980a166";
    private int expireTimestamp = 0;


    public String genToken(String uid, String channelName) {
        String result = "";
        try {
            AccessToken token = new AccessToken(appId, appCertificate, channelName, uid);
            expireTimestamp = Utils.getTimestamp() + 300;
            token.addPrivilege(AccessToken.Privileges.kJoinChannel, expireTimestamp);
            result = token.build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
