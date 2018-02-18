package com.appvizory.houzing.qpidmsg.config;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.apache.qpid.jms.policy.JmsDefaultDeserializationPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Builder of JmsConnectionFactory instances.
 */
public class QpidJMSConnectionFactory {

    private static final Logger LOG = LoggerFactory.getLogger(QpidJMSConnectionFactory.class);

    private static final String DEFAULT_REMOTE_URL = "amqp://localhost:5672";

    private final QpidJMSProperties properties;

    /**
     * Creates a new QpidJMSConnectionFactoryFactory instance
     *
     * @param properties
     *      The QpidJMSProperties to use when building new factories.
     */
    public QpidJMSConnectionFactory(QpidJMSProperties properties) {
        Assert.notNull(properties, "Properties must not be null");
        this.properties = properties;
    }

    /**
     * Creates and returns a JmsConnectionFactory instance using the current
     * configuration to prepare the factory for use.
     *
     * @param factoryClass
     *      The type of JmsConnectionFactory to create.
     *
     * @return a newly created and configured JmsConnectionFactory instance.
     */
    public JmsConnectionFactory createConnectionFactory(Class<JmsConnectionFactory> factoryClass) {
        try {
            JmsConnectionFactory factory = new JmsConnectionFactory();

            factory.setRemoteURI(getRemoteURI());

            // Override the URI options with configuration values, but only if
            // the value is actually set.

            if (StringUtils.hasLength(properties.getUsername())) {
                factory.setUsername(properties.getUsername());
            }

            if (StringUtils.hasLength(properties.getPassword())) {
                factory.setPassword(properties.getPassword());
            }

            if (StringUtils.hasLength(properties.getClientId())) {
                factory.setClientID(properties.getClientId());
            }

            if (properties.isReceiveLocalOnly() != null) {
                factory.setReceiveLocalOnly(properties.isReceiveLocalOnly());
            }

            if (properties.isReceiveNoWaitLocalOnly() != null) {
                factory.setReceiveNoWaitLocalOnly(properties.isReceiveNoWaitLocalOnly());
            }

            configureDeserializationPolicy(properties, factory);

            return factory;
        } catch (Exception ex) {
            LOG.error("Exception while createing Qpid JMS Connection Factory.", ex);
            throw new IllegalStateException("Failed to create the Qpid JMS ConnectionFactory, " +
                    "make sure the client Jar is on the Classpath.", ex);
        }
    }

    public String getRemoteURI() {
        if (StringUtils.hasLength(properties.getRemoteURL())) {
            return properties.getRemoteURL();
        } else {
            return DEFAULT_REMOTE_URL;
        }
    }

    private void configureDeserializationPolicy(QpidJMSProperties properties, JmsConnectionFactory factory) {
        JmsDefaultDeserializationPolicy deserializationPolicy =
                (JmsDefaultDeserializationPolicy) factory.getDeserializationPolicy();

        if (StringUtils.hasLength(properties.getDeserializationPolicy().getWhiteList())) {
            deserializationPolicy.setWhiteList(properties.getDeserializationPolicy().getWhiteList());
        }

        if (StringUtils.hasLength(properties.getDeserializationPolicy().getBlackList())) {
            deserializationPolicy.setBlackList(properties.getDeserializationPolicy().getBlackList());
        }
    }
}