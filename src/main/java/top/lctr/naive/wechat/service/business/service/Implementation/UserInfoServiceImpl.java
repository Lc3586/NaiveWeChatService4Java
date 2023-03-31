package top.lctr.naive.wechat.service.business.service.Implementation;

import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.extension.mybatis.edge.core.provider.standard.INaiveSql;
import project.extension.mybatis.edge.dbContext.repository.IBaseRepository_Key;
import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import project.extension.mybatis.edge.extention.datasearch.DataSearchExtension;
import project.extension.mybatis.edge.model.FilterCompare;
import project.extension.mybatis.edge.model.NullResultException;
import project.extension.standard.entity.IEntityExtension;
import project.extension.standard.exception.BusinessException;
import top.lctr.naive.wechat.service.business.service.Interface.IUserInfoService;
import top.lctr.naive.wechat.service.dto.userInfoDTO.RedisScopeInfo;
import top.lctr.naive.wechat.service.dto.userInfoDTO.*;
import top.lctr.naive.wechat.service.entity.WeChatUserInfo;
import top.lctr.naive.wechat.service.entityFields.WUI_Fields;

import java.util.*;

/**
 * 微信用户信息服务实现类
 *
 * @author LCTR
 * @date 2023-03-28
 */
@Service
@Scope("prototype")
public class UserInfoServiceImpl
        implements IUserInfoService {
    public UserInfoServiceImpl(INaiveSql orm,
                               IEntityExtension entityExtension) {
        this.repository_Key = orm.getRepository_Key(WeChatUserInfo.class,
                                                    String.class);
        this.entityExtension = entityExtension;
        this.tableKeyAliasMap = new HashMap<>();
        this.tableKeyAliasMap.put(defaultTableKey,
                                  "a");
    }

    /**
     * 日志组件
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IBaseRepository_Key<WeChatUserInfo, String> repository_Key;

    private final Map<String, String> tableKeyAliasMap;

    private final String defaultTableKey = "main";

    private final IEntityExtension entityExtension;

    @Override
    public List<UI_List> list(DataSearchDTO dataSearch)
            throws
            BusinessException {
        return repository_Key.select()
                             .as(tableKeyAliasMap.get(defaultTableKey))
                             .where(x -> x.and(DataSearchExtension.toDynamicFilter(dataSearch.getFilters(),
                                                                                   tableKeyAliasMap)))
                             .orderBy(x -> dataSearch.getOrder() == null
                                           ? x.orderBy(WUI_Fields.createTime)
                                           : x.orderBy(DataSearchExtension.toDynamicOrder(dataSearch.getOrder(),
                                                                                          tableKeyAliasMap)))
                             .pagination(dataSearch.getPagination())
                             .toList(UI_List.class);
    }

    @Override
    public UI_Detail detail(String id)
            throws
            BusinessException {
        try {
            return repository_Key.select()
                                 .where(x -> x.and(WUI_Fields.id,
                                                   FilterCompare.Eq,
                                                   id))
                                 .mainTagLevel(1)
                                 .first(UI_Detail.class);
        } catch (NullResultException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    @Override
    public void create(UI_Create data)
            throws
            BusinessException {
        repository_Key.insert(entityExtension.initialization(data),
                              UI_Create.class,
                              1);
    }

    @Override
    public UI_Edit edit(String id)
            throws
            BusinessException {
        return repository_Key.getByIdAndCheckNull(id,
                                                  UI_Edit.class,
                                                  1);
    }

    @Override
    public void edit(UI_Edit data)
            throws
            BusinessException {
        repository_Key.update(entityExtension.modify(data),
                              UI_Edit.class,
                              1);
    }

    @Override
    @Transactional
    public void delete(Collection<String> ids)
            throws
            BusinessException {
        repository_Key.deleteByIds(ids);
    }

    @Override
    public boolean infoExist(String appId,
                             String openId,
                             String groupId) {
        return repository_Key.select()
                             .where(x -> x.and(WUI_Fields.appId,
                                               FilterCompare.Eq,
                                               appId)
                                          .and(WUI_Fields.openId,
                                               FilterCompare.Eq,
                                               openId)
                                          .and(WUI_Fields.groupId,
                                               FilterCompare.Eq,
                                               groupId))
                             .any();
    }

    @Override
    public Boolean infoAuthorized(String appId,
                                  String openId,
                                  String groupId) {
        return repository_Key.select()
                             .where(x -> x.and(WUI_Fields.appId,
                                               FilterCompare.Eq,
                                               appId)
                                          .and(WUI_Fields.openId,
                                               FilterCompare.Eq,
                                               openId)
                                          .and(WUI_Fields.groupId,
                                               FilterCompare.Eq,
                                               groupId))
                             .first(WUI_Fields.authorized,
                                    Boolean.class);
    }

    @Override
    public void newInfo(String appId,
                        String openId,
                        String groupId) {
        WeChatUserInfo data = new WeChatUserInfo();
        entityExtension.initialization(data);
        data.setAppId(appId);
        data.setOpenId(openId);
        data.setGroupId(groupId);
        data.setEnable(true);
        data.setAuthorized(false);
        repository_Key.insert(data);
    }

    @Override
    public RedisScopeInfo getScopeInfo(String appId,
                                       String openId,
                                       String groupId) {
        return repository_Key.select()
                             .where(x -> x.and(WUI_Fields.appId,
                                               FilterCompare.Eq,
                                               appId)
                                          .and(WUI_Fields.openId,
                                               FilterCompare.Eq,
                                               openId)
                                          .and(WUI_Fields.groupId,
                                               FilterCompare.Eq,
                                               groupId))
                             .first(RedisScopeInfo.class);
    }

    @Override
    public RedisUserInfo getUserInfo(String appId,
                                     String openId,
                                     String groupId) {
        return repository_Key.select()
                             .where(x -> x.and(WUI_Fields.appId,
                                               FilterCompare.Eq,
                                               appId)
                                          .and(WUI_Fields.openId,
                                               FilterCompare.Eq,
                                               openId)
                                          .and(WUI_Fields.groupId,
                                               FilterCompare.Eq,
                                               groupId))
                             .first(RedisUserInfo.class);
    }

    @Override
    public void update(String appId,
                       String groupId,
                       WxOAuth2UserInfo oAuth2UserInfo) {
        repository_Key.updateDiy()
                      .where(x -> x.and(WUI_Fields.appId,
                                        FilterCompare.Eq,
                                        appId)
                                   .and(WUI_Fields.openId,
                                        FilterCompare.Eq,
                                        oAuth2UserInfo.getOpenid())
                                   .and(WUI_Fields.groupId,
                                        FilterCompare.Eq,
                                        groupId))
                      .set(WUI_Fields.authorized,
                           true)
                      .set(WUI_Fields.unionId,
                           oAuth2UserInfo.getUnionId())
                      .set(WUI_Fields.nickname,
                           oAuth2UserInfo.getNickname())
                      .set(WUI_Fields.headimgUrl,
                           oAuth2UserInfo.getHeadImgUrl())
                      .set(WUI_Fields.sex,
                           oAuth2UserInfo.getSex())
                      .set(WUI_Fields.country,
                           oAuth2UserInfo.getCountry())
                      .set(WUI_Fields.province,
                           oAuth2UserInfo.getProvince())
                      .set(WUI_Fields.city,
                           oAuth2UserInfo.getCity())
                      .executeAffrows();
    }
}
