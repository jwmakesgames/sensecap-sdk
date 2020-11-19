package cc.seeed.sensecap.interfaces;

import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.group.GroupInfo;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/11 09:57
 * @Version V1.0
 */
public interface SenseCAPGroup {

    /**
     * group
     */

    GroupInfo create(String groupName) throws BaseException;

    void rename(String groupUUID, String groupName) throws BaseException;

    void remove(String groupUUID) throws BaseException;

    List<GroupInfo> getGroupList() throws BaseException;


}
