package cc.seeed.sensecap.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/17 09:49
 * @Version V1.0
 */
public class LatestTelemetryDataInfo {

    @JsonProperty(value = "channel_index", access = JsonProperty.Access.WRITE_ONLY)
    private int channelIndex;

    private List<PointsInfo> points;


    public int getChannelIndex() {
        return channelIndex;
    }

    public void setChannelIndex(int channelIndex) {
        this.channelIndex = channelIndex;
    }

    public List<PointsInfo> getPoints() {
        return points;
    }

    public void setPoints(List<PointsInfo> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "LatestTelemetryDataInfo{" +
                "channelIndex=" + channelIndex +
                ", points=" + points +
                '}';
    }
}
