package com.appvizory.houzing.qpidmsg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by elflord on 2/18/18.
 */
@Component
public class MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class.getName());

    @JmsListener(destination = "testqueue")
    public void processMessage(String message){
        logger.info("Received message : {}", message);
    }
}
