package cc.seeed.sensecap.queries.data;

import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.model.callback.TelemetryDataCallback;
import cc.seeed.sensecap.model.result.DataResult;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/16 10:12
 * @Version V1.0
 */
public class DataQuery {


    private String deviceEui;
    private int channelIndex;
    private int measurementId;
    private int interval;
    private long startTime;
    private long endTime;
    private int limit;
    private TelemetryDataCallback callback;
    private OpenApiConfig openApiConfig;

    public DataQuery(DataQueryBuilder builder) {
        this.deviceEui = builder.getDeviceEui();
        this.channelIndex = builder.getChannelIndex();
        this.measurementId = builder.getMeasurementId();
        this.interval = builder.getInterval();
        this.startTime = builder.getStartTime();
        this.endTime = builder.getEndTime();
        this.limit = builder.getLimit();
        this.openApiConfig = builder.getOpenApiConfig();
        this.callback=builder.getCallback();

    }


    public static DataQueryBuilder newBuilder(OpenApiConfig openApiConfig) {
        return new DataQueryBuilder(openApiConfig);
    }

    public DataResult execute(){
        return new DataResult(this);
    }

    public String getDeviceEui() {
        return deviceEui;
    }

    public int getChannelIndex() {
        return channelIndex;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public int getInterval() {
        return interval;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public int getLimit() {
        return limit;
    }

    public TelemetryDataCallback getCallback() {
        return callback;
    }

    public OpenApiConfig getOpenApiConfig() {
        return openApiConfig;
    }

    public static class DataQueryBuilder {
        private String deviceEui;
        private int channelIndex;
        private int measurementId;
        private int interval;
        private long startTime;
        private long endTime;
        private int limit;
        private TelemetryDataCallback callback;
        private OpenApiConfig openApiConfig;

        public DataQueryBuilder(OpenApiConfig openApiConfig) {
            this.openApiConfig = openApiConfig;
        }

        public String getDeviceEui() {
            return deviceEui;
        }

        public DataQueryBuilder deviceEui(String deviceEui) {
            this.deviceEui = deviceEui;
            return this;
        }

        public int getChannelIndex() {
            return channelIndex;
        }

        public DataQueryBuilder channelIndex(int channelIndex) {
            this.channelIndex = channelIndex;
            return this;
        }

        public int getMeasurementId() {
            return measurementId;
        }

        public DataQueryBuilder measurementId(int measurementId) {
            this.measurementId = measurementId;
            return this;
        }

        public int getInterval() {
            return interval;
        }

        public DataQueryBuilder interval(int interval) {
            this.interval = interval;
            return this;
        }

        public long getStartTime() {
            return startTime;
        }

        public DataQueryBuilder startTime(long startTime) {
            this.startTime = startTime;
            return this;
        }

        public long getEndTime() {
            return endTime;
        }

        public DataQueryBuilder endTime(long endTime) {
            this.endTime = endTime;
            return this;
        }

        public int getLimit() {
            return limit;
        }

        public DataQueryBuilder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public OpenApiConfig getOpenApiConfig() {
            return openApiConfig;
        }

        public DataQueryBuilder openApiConfig(OpenApiConfig openApiConfig) {
            this.openApiConfig = openApiConfig;
            return this;
        }

        public TelemetryDataCallback getCallback() {
            return callback;
        }

        public DataQueryBuilder callback(TelemetryDataCallback callback) {
            this.callback = callback;
            return this;
        }

        public DataQuery build() {
            return new DataQuery(this);
        }
    }
}
