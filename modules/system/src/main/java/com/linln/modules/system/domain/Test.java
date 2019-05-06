package com.linln.modules.system.domain;

import lombok.Data;

@Data
public class Test {

    public Test(String name1,String name2)
    {
        this.name1 = name1;
        this.name2 = name2;
    }

    private String name1;

    private String name2;

}
