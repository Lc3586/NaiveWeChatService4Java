package top.lctr.naive.wechat.service.dto.userInfoDTO;

import lombok.Data;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import project.extension.mybatis.edge.annotations.EntityMapping;
import project.extension.mybatis.edge.annotations.EntityMappingSetting;
import top.lctr.naive.wechat.service.entity.WeChatUserInfo;

/**
 * 微信用户授权信息
 *
 * @author LCTR
 * @date 2023-03-29
 */
@EntityMapping(WeChatUserInfo.class)
@Data
public class RedisScopeInfo {
    public RedisScopeInfo() {

    }

    public RedisScopeInfo(String appId,
                          String groupId,
                          Boolean authorized,
                          WxOAuth2AccessToken accessToken) {
        this(appId,
             accessToken.getOpenId(),
             accessToken.getUnionId(),
             groupId,
             accessToken.getScope(),
             accessToken.getSnapshotUser(),
             authorized);
    }

    public RedisScopeInfo(String appId,
                          String openId,
                          String unionId,
                          String groupId,
                          String scope,
                          Integer snapshotUser,
                          Boolean authorized) {
        this.appId = appId;
        this.openId = openId;
        this.unionId = unionId;
        this.groupId = groupId;
        this.scope = scope;
        this.snapshotUser = snapshotUser;
        this.authorized = authorized;
    }

    /**
     * 微信公众号标识
     */
    @EntityMappingSetting(self = true)
    private String appId;

    /**
     * 用户公众号唯一标识
     */
    @EntityMappingSetting(self = true)
    private String openId;

    /**
     * 用户公众平台唯一标识
     */
    @EntityMappingSetting(self = true)
    private String unionId;

    /**
     * 分组标识
     */
    @EntityMappingSetting(self = true)
    private String groupId;

    /**
     * 用户授权的作用域
     */
    @EntityMappingSetting(self = true)
    private String scope;

    /**
     * 虚拟用户
     */
    @EntityMappingSetting(self = true)
    private Integer snapshotUser;

    /**
     * 已获取信息授权
     * <p>如果此值为true，则可使用state参数直接从redis中过去用户信息</p>
     */
    private Boolean authorized;
}
