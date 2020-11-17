package cc.seeed.sensecap;


/**
 * @Author AG
 * @Description
 * @Date 2020/9/1 15:36
 * @Version V1.0
 */
public interface SenseCAP {

    public  SenseCAPClient buildConfig(String accessId, String accessKey, int region);

}
