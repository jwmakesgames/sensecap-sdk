package cc.seeed.sensecap.model.callback;

import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.data.TelemetryDataResult;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/4 15:39
 * @Version V1.0
 */
public interface TelemetryDataCallback {

    public void messageArrived(TelemetryDataResult var) throws BaseException;

}
