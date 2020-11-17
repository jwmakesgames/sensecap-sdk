package cc.seeed.sensecap.model.callback;

import cc.seeed.sensecap.config.mqtt.CallBackMapCache;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.data.TelemetryDataResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/4 17:05
 * @Version V1.0
 */
public class SensorMqttCallback implements MqttCallback {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public SensorMqttCallback() {

    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        String message = new String(mqttMessage.getPayload());
        JSONObject jsonObject = JSON.parseObject(message);
        String[] split = topic.split("/");
        String deviceEui = split[3];
        int measurementId = Integer.parseInt(split[6]);
        int channelIndex = Integer.parseInt(split[4]);
        List<Map<String, TelemetryDataCallback>> mapList = CallBackMapCache.getSimpleCache().get(deviceEui);
        logger.warn("mapList:{},topic:{}", mapList, topic);
        if (mapList == null) {
            return;
        }
        mapList.stream().forEach(map -> map.forEach((k, v) -> {
            if (topic.replace("vs","+").equals(k)) {
                try {
                    v.messageArrived(new TelemetryDataResult().setValue(jsonObject.getDouble("value"))
                            .setTimestamp(jsonObject.getLong("timestamp"))
                            .setChannelIndex(channelIndex)
                            .setDeviceEui(deviceEui)
                            .setMeasurementId(measurementId)
                    );
                } catch (BaseException e) {
                    logger.warn("mqtt 上报回调到业务失败 e:{}", e);
                }
            }

        }));


    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
