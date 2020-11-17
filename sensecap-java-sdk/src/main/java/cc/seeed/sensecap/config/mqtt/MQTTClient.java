package cc.seeed.sensecap.config.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/3 15:23
 * @Version V1.0
 */
public class MQTTClient {

    public MqttClient client;
    private MqttConnectOptions options;
    private MqttConnectionInfo mqttConnectionInfo;

    private int connectionTimeout = 10;
    private int keepAliveInterval = 60;
    private boolean cleanSession = false;


    public MQTTClient(MqttConnectionInfo mqttConnectionInfo) {
        this.mqttConnectionInfo = mqttConnectionInfo;
    }

    public void start() {
        try {

            client = new MqttClient(mqttConnectionInfo.getHost(), mqttConnectionInfo.getClientId(), new MemoryPersistence());
            options = new MqttConnectOptions();

            options.setCleanSession(cleanSession);
            options.setUserName(mqttConnectionInfo.getUserName());
            options.setPassword(mqttConnectionInfo.getPassWord().toCharArray());
            options.setConnectionTimeout(connectionTimeout);
            options.setKeepAliveInterval(keepAliveInterval);
            options.setAutomaticReconnect(true);
            client.setCallback(mqttConnectionInfo.getMqttCallback());

            if (!client.isConnected()) {
                client.connect(options);
            } else {
                client.disconnect();
                client.connect(options);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
