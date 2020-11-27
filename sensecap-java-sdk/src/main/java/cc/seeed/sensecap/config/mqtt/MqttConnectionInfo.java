package cc.seeed.sensecap.config.mqtt;

import org.eclipse.paho.client.mqttv3.MqttCallback;

import java.util.Arrays;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/3 18:09
 * @Version V1.0
 */
public class MqttConnectionInfo {
    private String host;
    private String[] topics;
    private String clientId;
    private String userName;
    private String passWord;
    private long orgId;
    private int[] qos = {1};

    //private MqttCallback mqttCallback;

    public int[] getQos() {
        return qos;
    }

    public MqttConnectionInfo setQos(int[] qos) {
        this.qos = qos;
        return this;
    }

    public long getOrgId() {
        return orgId;
    }

    public MqttConnectionInfo setOrgId(long orgId) {
        this.orgId = orgId;
        return this;
    }


    public String getHost() {
        return host;
    }

    public MqttConnectionInfo setHost(String host) {
        this.host = host;
        return this;
    }

    public String[] getTopics() {
        return topics;
    }

    public MqttConnectionInfo setTopics(String[] topics) {
        this.topics = topics;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public MqttConnectionInfo setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public MqttConnectionInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassWord() {
        return passWord;
    }

    public MqttConnectionInfo setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    /*public MqttCallback getMqttCallback() {
        return mqttCallback;
    }

    public MqttConnectionInfo setMqttCallback(MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
        return this;
    }*/

    @Override
    public String toString() {
        return "MqttConnectionInfo{" +
                "host='" + host + '\'' +
                ", topics='" + Arrays.toString(topics) + '\'' +
                ", clientId='" + clientId + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", orgId=" + orgId +
                ", qos=" + Arrays.toString(qos) +
                '}';
    }
}
