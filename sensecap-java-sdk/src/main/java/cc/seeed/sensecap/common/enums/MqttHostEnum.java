package cc.seeed.sensecap.common.enums;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/27 11:31
 * @Version V1.0
 */
public enum MqttHostEnum {

    CC_HOST("tcp://sensecap-openstream.seeed.cc"),
    CN_HOST("tcp://sensecap-openstream.seeed.cn");

    private String host;

    MqttHostEnum(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
