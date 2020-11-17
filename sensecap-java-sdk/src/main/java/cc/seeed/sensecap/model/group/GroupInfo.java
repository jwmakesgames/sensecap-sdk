package cc.seeed.sensecap.model.group;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Author ascgoogle
 * @Description
 * @Date 2020/8/13 15:57
 * @Version V1.0
 */
public class GroupInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "group_name",access = JsonProperty.Access.WRITE_ONLY)
    private String groupName;

    @JsonProperty(value = "group_uuid",access = JsonProperty.Access.WRITE_ONLY)
    private String groupUUID;

    public String getGroupName() {
        return groupName;
    }

    public GroupInfo setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getGroupUUID() {
        return groupUUID;
    }

    public GroupInfo setGroupUUID(String groupUUID) {
        this.groupUUID = groupUUID;
        return this;
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "groupName='" + groupName + '\'' +
                ", groupUUID='" + groupUUID + '\'' +
                '}';
    }


}
