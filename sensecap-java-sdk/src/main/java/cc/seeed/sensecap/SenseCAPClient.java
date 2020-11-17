package cc.seeed.sensecap;

import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.manager.*;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/1 15:38
 * @Version V1.0
 */

public class SenseCAPClient {

    private final OpenApiConfig openApiConfig;

    private DeviceManager deviceManager;
    private GroupManager groupManager;
    private OrganizationManager organizationManager;
    private DataManager dataManager;


    public SenseCAPClient(String accessId, String accessKey, int region) {
        this.openApiConfig = new OpenApiConfig(accessId, accessKey, region);
        initManager();
    }

    public SenseCAPClient(OpenApiConfig openApiConfig) {
        this.openApiConfig = openApiConfig;
        initManager();
    }

    private void initManager() {
        this.deviceManager = new DeviceManager(openApiConfig);
        this.groupManager = new GroupManager(openApiConfig);
        this.organizationManager = new OrganizationManager(openApiConfig);
        this.dataManager = new DataManager(openApiConfig);
    }

    public DeviceManager getDeviceManager() {
        return deviceManager;
    }

    public GroupManager getGroupManager() {
        return groupManager;
    }

    public OrganizationManager getOrganizationManager() {
        return organizationManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
