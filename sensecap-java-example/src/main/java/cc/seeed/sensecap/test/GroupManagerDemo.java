package cc.seeed.sensecap.test;

import cc.seeed.sensecap.SenseCAPClient;
import cc.seeed.sensecap.SenseCAPClientBuilder;
import cc.seeed.sensecap.common.enums.RegionType;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.manager.GroupManager;
import cc.seeed.sensecap.model.group.GroupInfo;
import cc.seeed.sensecap.model.result.GroupResult;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/25 13:44
 * @Version V1.0
 */
public class GroupManagerDemo {


    private static SenseCAPClient senseCAPClient;
    private static String deviceEui="";
    static {
        String accessId = "";
        String accessKey = "";
        int region = RegionType.SENSECAP_CC.getRegion();
        OpenApiConfig openApiConfig = new OpenApiConfig(accessId, accessKey, region);
        senseCAPClient = new SenseCAPClientBuilder().buildConfig(openApiConfig);
    }

    public static void main(String[] args) throws BaseException {
        //方法测试
        //createGroup( );
        //removeGroup();
        //renameGroup();
        getGroupList();
    }

    public static void createGroup() throws BaseException {
        GroupInfo result = senseCAPClient.getGroupManager().createGroupQuery().groupName("SDK-test").build().create();
        System.out.println(result);
    }

    public static void renameGroup() throws BaseException {
        senseCAPClient.getGroupManager().renameGroup("", "SDk-test");
    }

    public static void removeGroup() throws BaseException {
        senseCAPClient.getGroupManager().removeGroup(" ");
    }

    public static void getGroupList() throws BaseException {
        GroupResult groupResult = senseCAPClient.getGroupManager().createGroupQuery().build().execute();
        List<GroupInfo> groupInfos = groupResult.toList();
        groupInfos.forEach(groupInfo -> {
            System.out.println(groupInfo.toString());
            /*try {
                groupResult.renameGroup(groupInfo.getGroupUUID(), "新组名");
                groupResult.removeGroup(groupInfo.getGroupUUID());
            } catch (BaseException e) {
                e.printStackTrace();
            }*/
        });
    }

}

