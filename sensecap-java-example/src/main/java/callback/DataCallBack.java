package callback;

import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.callback.TelemetryDataCallback;
import cc.seeed.sensecap.model.data.TelemetryDataResult;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/16 13:58
 * @Version V1.0
 */
public class DataCallBack implements TelemetryDataCallback {


    @Override
    public void messageArrived(TelemetryDataResult var) throws BaseException {
        System.out.println("Test获取消息： "+var.toString());
    }
}
