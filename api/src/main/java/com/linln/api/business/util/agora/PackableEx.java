package com.linln.api.business.util.agora;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
