package cc.seeed.sensecap.test;

import cc.seeed.sensecap.config.mqtt.MqttConnectionInfo;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.manager.MqttManager;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/4 09:14
 * @Version V1.0
 */
public class MqttDemo {

    public static void main(String[] args) throws BaseException {


        /*MqttManager mqttManager=new MqttManager();
        mqttManager.initMqtt(new MqttConnectionInfo()
                .setClientId("org-919136109886-sdktest")
                .setHost("tcp://sensecap-openstream.seeed.cc")
                //.setOrgId(919136109886L)
                .setUserName("org-919136109886")
                .setPassWord("195957AE1F95469EB822E2944CBC95BEC0C7422F2028476F948B8436F4578D8F")
                .setTopic("/device_sensor_data/919136109886/+/+/+/+")
                .setMqttCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable throwable) {
                        System.out.println("connectionLost "+throwable);
                    }

                    @Override
                    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                        System.out.println("messageArrived: "+s+"  \n  "+mqttMessage);
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                        System.out.println("deliveryComplete");
                    }
                })

        );*/

    }



}
