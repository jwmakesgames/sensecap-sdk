package cc.seeed.sensecap.model.callback;

import cc.seeed.sensecap.config.mqtt.CallBackMapCache;
import cc.seeed.sensecap.config.mqtt.MQTTClient;
import cc.seeed.sensecap.config.mqtt.MapCache;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.data.TelemetryDataResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.*;

import java.util.List;
import java.util.Map;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/4 17:05
 * @Version V1.0
 */
public class SensorMqttCallback implements MqttCallback {

    Log logger = LogFactory.getLog(this.getClass());
    public MqttClient client;
    private MqttConnectOptions options;
    public SensorMqttCallback(MqttClient client, MqttConnectOptions options) {
        this.client = client;
        this.options = options;

    }

    @Override
    public void connectionLost(Throwable cause) {
        //logger.warn("Disconnect，" + cause.getMessage());
        String clientId = client.getClientId();
        String[] split = clientId.split("-");
       // MQTTClient mqttClient = MapCache.getSimpleCache().get(split[1]);
        int i=1;
        while(true){
            try {
                if(!client.isConnected()){
                    client.connect(options);
                }/*else {
                    mqttClient.client.disconnect();
                    mqttClient.client.connect(options);
                }*/
                logger.warn("******* <"+client.getClientId()+"> 第"+i+"次重连成功********   topic:{}");
                break;
            } catch (Exception e) {
                logger.warn("------- <"+client.getClientId()+"> 第"+i+"次重连失败--------  topic:{}");
                try {
                    long time=3000L*i;
                    if(time>300000L){
                        time=300000L;
                    }
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                    logger.warn("<<<<<<<<<延迟重连失败>>>>>>>>");
                }
                i++;
                continue;
            }

        }

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
        logger.warn("mapList: "+mapList+" , topic: "+ topic);
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
