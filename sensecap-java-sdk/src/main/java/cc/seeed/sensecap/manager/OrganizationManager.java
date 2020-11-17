package cc.seeed.sensecap.manager;

import cc.seeed.sensecap.actions.Organization;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.interfaces.SenseCAPOrganization;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/25 14:46
 * @Version V1.0
 */
public class OrganizationManager implements SenseCAPOrganization {

    private final OpenApiConfig openApiConfig;

    private final Organization organization;

    public OrganizationManager(OpenApiConfig openApiConfig ) {
        this.openApiConfig = openApiConfig;
        this.organization = new Organization(openApiConfig);
    }

    public long getOrganizationId() throws BaseException {
        return organization.getOrganizationId();
    }
}
