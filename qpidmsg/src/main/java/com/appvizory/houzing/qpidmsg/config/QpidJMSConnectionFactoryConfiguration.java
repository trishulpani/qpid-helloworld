package com.appvizory.houzing.qpidmsg.config;


import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;


@Configuration
@ConditionalOnMissingBean(ConnectionFactory.class)
public class QpidJMSConnectionFactoryConfiguration {

    @Bean
    public JmsConnectionFactory jmsConnectionFactory(QpidJMSProperties properties) {
        return new QpidJMSConnectionFactory(properties)
                .createConnectionFactory(JmsConnectionFactory.class);
    }
}