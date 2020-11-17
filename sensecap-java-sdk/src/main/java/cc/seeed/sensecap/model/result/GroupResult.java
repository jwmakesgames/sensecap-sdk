package cc.seeed.sensecap.model.result;

import cc.seeed.sensecap.actions.Group;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.group.GroupInfo;
import cc.seeed.sensecap.queries.group.GroupQuery;

import java.util.List;


/**
 * @Author AG
 * @Description
 * @Date 2020/9/9 14:30
 * @Version V1.0
 */
public class GroupResult extends GenericResult {


    private GroupQuery groupQuery;
    private Group group;

    public GroupResult(GroupQuery groupQuery, OpenApiConfig openApiConfig) {
        this.groupQuery = groupQuery;
        group = new Group(openApiConfig);
    }

    public GroupResult renameGroup(String groupUUID, String groupName) throws BaseException {
        group.renameGroup(groupUUID, groupName);
        return this;
    }


    public List<GroupInfo> toList() throws BaseException {
        List<GroupInfo> groupList = group.getGroupList();
        return groupList;
    }

    public GroupResult removeGroup(String groupUUID) throws BaseException {
        group.removeGroup(groupUUID);
        return this;
    }


}
