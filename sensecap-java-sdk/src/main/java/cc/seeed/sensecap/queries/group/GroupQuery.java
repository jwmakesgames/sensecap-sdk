package cc.seeed.sensecap.queries.group;

import cc.seeed.sensecap.actions.Group;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.group.GroupInfo;
import cc.seeed.sensecap.model.result.GroupResult;
import cc.seeed.sensecap.queries.ApiRequest;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/11 13:55
 * @Version V1.0
 */
public class GroupQuery extends ApiRequest<GroupResult> {


    private String groupName;
    private String groupUUID;
    private OpenApiConfig openApiConfig;


    public GroupQuery(GroupQueryBuilder builder) {
        this.groupName = builder.getGroupName();
        this.groupUUID = builder.getGroupUUID();
        this.openApiConfig = builder.getOpenApiConfig();

    }

    public String getGroupName() {
        return groupName;
    }


    public String getGroupUUID() {
        return groupUUID;
    }


    public static GroupQueryBuilder newBuilder(OpenApiConfig openApiConfig) {
        return new GroupQueryBuilder(openApiConfig);
    }

    @Override
    public GroupResult execute() {
        return new GroupResult(this, this.openApiConfig);
    }


    public GroupInfo create() throws BaseException {
        GroupInfo group = new Group(openApiConfig).create(this.getGroupName());
        return group;
    }


}
