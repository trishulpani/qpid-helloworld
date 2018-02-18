package com.appvizory.houzing.qpidmsg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.qpidjms")
public class QpidJMSProperties {

    private String remoteURL;
    private String username;
    private String password;
    private String clientId;

    private Boolean receiveLocalOnly;
    private Boolean receiveNoWaitLocalOnly;

    private final DeserializationPolicy deserializationPolicy = new DeserializationPolicy();

    public String getRemoteURL() {
        return remoteURL;
    }

    public void setRemoteURL(String remoteURL) {
        this.remoteURL = remoteURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Boolean isReceiveLocalOnly() {
        return receiveLocalOnly;
    }

    public void setReceiveLocalOnly(Boolean receiveLocalOnly) {
        this.receiveLocalOnly = receiveLocalOnly;
    }

    public Boolean isReceiveNoWaitLocalOnly() {
        return receiveNoWaitLocalOnly;
    }

    public void setReceiveNoWaitLocalOnly(Boolean receiveNoWaitLocalOnly) {
        this.receiveNoWaitLocalOnly = receiveNoWaitLocalOnly;
    }

    public DeserializationPolicy getDeserializationPolicy() {
        return deserializationPolicy;
    }

    public static class DeserializationPolicy {

        private String whiteList;
        private String blackList;

        public String getWhiteList() {
            return whiteList;
        }

        public void setWhiteList(String whiteList) {
            this.whiteList = whiteList;
        }

        public String getBlackList() {
            return blackList;
        }

        public void setBlackList(String blackList) {
            this.blackList = blackList;
        }
    }
}