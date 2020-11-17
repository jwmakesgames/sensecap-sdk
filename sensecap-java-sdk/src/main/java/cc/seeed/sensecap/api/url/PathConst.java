package cc.seeed.sensecap.api.url;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/14 11:17
 * @Version V1.0
 */
public class PathConst {

    /**
     * organization
     */
    public final  static String VIEW_ORGANIZATION="/view_organization";

    /**
     * group
     */
    public final  static String CREATE_GROUP="/create_group";
    public final  static String UPDATE_GROUP="/update_group";
    public final  static String DELETE_GROUP="/delete_group";
    public final  static String LIST_GROUP="/list_groups";
    public final  static String MOVE_DEVICES_TO="/move_devices_to_group";


    /**
     * device
     */
    public final  static String LIST_DEVICES="/list_devices";
    public final  static String VIEW_DEVICES="/view_devices";
    public final  static String LIST_DEVICE_CHANNELS="/list_device_channels";
    public final  static String VIEW_DEVICE_RUNNING_STATUS="/view_device_running_status";
    public final  static String LIST_MEASUREMENT_REFERENCE="/list_measurement_reference";
    public final  static String BIND_DEVICE="/bind_device";
    public final  static String DELETE_DEVICES="/delete_devices";


    /**
     *  telemetry data
     */
    public final  static String VIEW_LATEST_TELEMETRY_DATA="/view_latest_telemetry_data";
    public final  static String LIST_TELEMETRY_DATA="/list_telemetry_data";
    public final  static String AGGREGATE_CHART_POINTS="/aggregate_chart_points";


}
