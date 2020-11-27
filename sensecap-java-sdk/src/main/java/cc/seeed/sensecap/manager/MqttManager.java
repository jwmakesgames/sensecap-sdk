package cc.seeed.sensecap.manager;

import cc.seeed.sensecap.chain.Chain;
import cc.seeed.sensecap.config.mqtt.MQTTClient;
import cc.seeed.sensecap.config.mqtt.MapCache;
import cc.seeed.sensecap.config.mqtt.MqttConnectionInfo;
import cc.seeed.sensecap.exception.BaseException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.*;
import java.util.List;
import java.util.Map;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/3 15:20
 * @Version V1.0
 */
public class MqttManager {


    Log logger = LogFactory.getLog(this.getClass());

    public void initMqtt(MqttConnectionInfo mqttConnectionInfo) throws BaseException {
        checkMqttConnectionInfo(mqttConnectionInfo);
        try {
            String orgId = String.valueOf(mqttConnectionInfo.getOrgId());
            Map<String, MQTTClient> all = MapCache.getSimpleCache().getAll();
            if (all.containsKey(orgId)) {
                MqttClient client = all.get(orgId).client;
                if (client.isConnected()) {
                    String[] topics = mqttConnectionInfo.getTopics();
                    client.subscribe(topics, mqttConnectionInfo.getQos());
                    return;
                }
            }
            MQTTClient mqttClient = new MQTTClient(mqttConnectionInfo);
            mqttClient.start();
            String[] topics = mqttConnectionInfo.getTopics();
            mqttClient.client.subscribe(topics, mqttConnectionInfo.getQos());
            MapCache.getSimpleCache().put(orgId, mqttClient);
        } catch (MqttException e) {
            logger.warn("init mqtt fail :{}", e);
        }
    }


    public void restartMqttClient(List<MqttConnectionInfo> list) throws BaseException {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (MqttConnectionInfo mqttConnectionInfo : list) {
            initMqtt(mqttConnectionInfo);
        }
    }

    boolean checkMqttConnectionInfo(MqttConnectionInfo mqttConnectionInfo) throws BaseException {
        logger.warn("mqttConnectionInfo : "+ mqttConnectionInfo.toString());
        try {
            Chain.build()
                    .must(x -> mqttConnectionInfo.getOrgId() > 0)
                    .must(x -> mqttConnectionInfo.getTopics().length > 0)
                    .must(x -> StringUtils.isNotBlank(mqttConnectionInfo.getClientId()))
                    .must(x -> StringUtils.isNotBlank(mqttConnectionInfo.getHost()))
                    .must(x -> StringUtils.isNotBlank(mqttConnectionInfo.getPassWord()))
                    .must(x -> StringUtils.isNotBlank(mqttConnectionInfo.getUserName()))
                    //.must(x -> mqttConnectionInfo.getMqttCallback() != null)
            ;
        } catch (BaseException e) {
            throw new BaseException("MissingArgument");
        }
        return true;

    }
}
