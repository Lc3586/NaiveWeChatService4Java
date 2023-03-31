package top.lctr.naive.wechat.service.dto.userInfoDTO;

import lombok.Data;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import project.extension.mybatis.edge.annotations.EntityMapping;
import project.extension.mybatis.edge.annotations.EntityMappingSetting;
import top.lctr.naive.wechat.service.entity.WeChatUserInfo;

/**
 * 微信用户信息
 *
 * @author LCTR
 * @date 2023-03-29
 */
@EntityMapping(WeChatUserInfo.class)
@Data
public class RedisUserInfo {
    public RedisUserInfo() {

    }

    public RedisUserInfo(String appId,
                         WxOAuth2UserInfo userInfo,
                         String groupId,
                         String language) {
        this(appId,
             userInfo.getOpenid(),
             userInfo.getUnionId(),
             groupId,
             userInfo.getNickname(),
             userInfo.getHeadImgUrl(),
             Byte.parseByte(userInfo.getSex()
                                    .toString()),
             userInfo.getCountry(),
             userInfo.getProvince(),
             userInfo.getCity(),
             language);
    }

    public RedisUserInfo(String appId,
                         String openId,
                         String unionId,
                         String groupId,
                         String nickname,
                         String headimgUrl,
                         Byte sex,
                         String country,
                         String province,
                         String city,
                         String language) {
        this.appId = appId;
        this.openId = openId;
        this.unionId = unionId;
        this.groupId = groupId;
        this.nickname = nickname;
        this.headimgUrl = headimgUrl;
        this.sex = sex;
        this.country = country;
        this.province = province;
        this.city = city;
        this.language = language;
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
     * 昵称
     */
    @EntityMappingSetting(self = true)
    private String nickname;

    /**
     * 头像
     */
    @EntityMappingSetting(self = true)
    private String headimgUrl;

    /**
     * 性别
     */
    @EntityMappingSetting(self = true)
    private Byte sex;

    /**
     * 国家
     */
    @EntityMappingSetting(self = true)
    private String country;

    /**
     * 省份
     */
    @EntityMappingSetting(self = true)
    private String province;

    /**
     * 城市
     */
    @EntityMappingSetting(self = true)
    private String city;

    /**
     * 语言
     */
    @EntityMappingSetting(self = true)
    private String language;
}
