package cc.seeed.sensecap.queries.group;

import cc.seeed.sensecap.config.OpenApiConfig;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/11 13:57
 * @Version V1.0
 */
public class GroupQueryBuilder {

    public String groupName;
    public String groupUUID;
    private OpenApiConfig openApiConfig;

    public GroupQueryBuilder(OpenApiConfig openApiConfig) {
        this.openApiConfig = openApiConfig;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupUUID() {
        return groupUUID;
    }

    public GroupQueryBuilder groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public OpenApiConfig getOpenApiConfig() {
        return openApiConfig;
    }

    public GroupQueryBuilder groupUUID(String groupUUID) {
        this.groupUUID = groupUUID;
        return this;
    }


    public GroupQuery build() {
        GroupQuery groupQuery = new GroupQuery(this);
        return groupQuery;
    }
}
