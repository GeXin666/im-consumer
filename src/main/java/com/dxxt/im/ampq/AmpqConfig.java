package com.dxxt.im.ampq;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AmpqConfig {

    public static final String messgaeQueue = "daxin.im.message.queue";
    public static final String exchangeName = "amq.direct";

}
