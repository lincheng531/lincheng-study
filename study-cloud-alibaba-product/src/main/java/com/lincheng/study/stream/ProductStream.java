package com.lincheng.study.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductStream {

    @Output("output")
    MessageChannel productOutput();

}
