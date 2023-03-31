package top.lctr.naive.wechat.service.business.service.Interface;

import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import top.lctr.naive.wechat.service.dto.userInfoDTO.RedisScopeInfo;
import top.lctr.naive.wechat.service.dto.userInfoDTO.*;

import java.util.Collection;

/**
 * 微信用户信息服务接口类
 *
 * @author LCTR
 * @date 2023-03-28
 */
public interface IUserInfoService {
    /**
     * 列表数据
     *
     * @param dataSearch 数据搜索参数
     * @return 列表数据集合
     */
    java.util.List<UI_List> list(DataSearchDTO dataSearch);

    /**
     * 详情数据
     *
     * @param id 主键
     * @return 详情数据
     */
    UI_Detail detail(String id);

    /**
     * 新增
     *
     * @param data 数据
     */
    void create(UI_Create data);

    /**
     * 获取编辑数据
     *
     * @param id 主键
     * @return 编辑数据
     */
    UI_Edit edit(String id);

    /**
     * 编辑
     *
     * @param data 数据
     */
    void edit(UI_Edit data);

    /**
     * 删除
     *
     * @param ids 主键集合
     */
    void delete(Collection<String> ids);

    /**
     * 是否存在该信息
     *
     * @param appId   微信公众号标识
     * @param openId  用户公众号唯一标识
     * @param groupId 分组标识
     */
    boolean infoExist(String appId,
                      String openId,
                      String groupId);

    /**
     * 是否已授权信息
     *
     * @param appId   微信公众号标识
     * @param openId  用户公众号唯一标识
     * @param groupId 分组标识
     * @return null：信息不存在，true：已授权，false：未授权
     */
    Boolean infoAuthorized(String appId,
                           String openId,
                           String groupId);

    /**
     * 创建信息
     *
     * @param appId   微信公众号标识
     * @param openId  用户公众号唯一标识
     * @param groupId 分组标识
     */
    void newInfo(String appId,
                 String openId,
                 String groupId);

    /**
     * 获取授权信息
     *
     * @param appId   微信公众号标识
     * @param openId  用户公众号唯一标识
     * @param groupId 分组标识
     */
    RedisScopeInfo getScopeInfo(String appId,
                                String openId,
                                String groupId);

    /**
     * 获取用户信息
     *
     * @param appId   微信公众号标识
     * @param openId  用户公众号唯一标识
     * @param groupId 分组标识
     */
    RedisUserInfo getUserInfo(String appId,
                              String openId,
                              String groupId);

    /**
     * 编辑
     *
     * @param appId          微信公众号标识
     * @param groupId        分组标识
     * @param oAuth2UserInfo 微信用户信息
     */
    void update(String appId,
                String groupId,
                WxOAuth2UserInfo oAuth2UserInfo);
}
