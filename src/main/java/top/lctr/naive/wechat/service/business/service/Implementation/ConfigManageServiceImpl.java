package top.lctr.naive.wechat.service.business.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import project.extension.cryptography.JasyptUtils;
import project.extension.mybatis.edge.core.provider.standard.INaiveSql;
import project.extension.mybatis.edge.dbContext.repository.IBaseRepository_Key;
import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import project.extension.mybatis.edge.extention.datasearch.DataSearchExtension;
import project.extension.mybatis.edge.model.FilterCompare;
import project.extension.mybatis.edge.model.NullResultException;
import project.extension.standard.entity.IEntityExtension;
import project.extension.standard.exception.BusinessException;
import top.lctr.naive.wechat.service.dto.configDTO.*;
import top.lctr.naive.wechat.service.entity.WeChatConfig;
import top.lctr.naive.wechat.service.entityFields.WC_Fields;
import top.lctr.naive.wechat.service.business.service.Interface.IConfigManageService;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 微信配置服务实现类
 *
 * @author LCTR
 * @date 2023-03-28
 */
@Service
@Scope("prototype")
public class ConfigManageServiceImpl
        implements IConfigManageService {
    public ConfigManageServiceImpl(INaiveSql orm,
                                   IEntityExtension entityExtension) {
        this.repository_Key = orm.getRepository_Key(WeChatConfig.class,
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

    private final IBaseRepository_Key<WeChatConfig, String> repository_Key;

    private final Map<String, String> tableKeyAliasMap;

    private final String defaultTableKey = "main";

    private final IEntityExtension entityExtension;

    /**
     * 缓存，用于直接读取数据
     */
    private final static Map<String, Map<String, Object>> cacheValueMap = new HashMap<>();

    /**
     * 更新至缓存
     *
     * @param config 配置信息
     */
    private void updateCache(WeChatConfig config) {
        if (!cacheValueMap.containsKey(config.getAppId()))
            cacheValueMap.put(config.getAppId(),
                              new HashMap<>());
        Map<String, Object> valueMap = cacheValueMap.get(config.getAppId());
        valueMap.put(WC_Fields.secret,
                     config.getSecret());
        valueMap.put(WC_Fields.token,
                     config.getToken());
        valueMap.put(WC_Fields.aesKey,
                     config.getAesKey());
        valueMap.put(WC_Fields.templateId,
                     config.getTemplateId());
        valueMap.put(WC_Fields.accessToken,
                     config.getAccessToken());
        valueMap.put(WC_Fields.expiresTime,
                     config.getExpiresTime());
        valueMap.put(WC_Fields.oauth2redirectUri,
                     config.getOauth2redirectUri());
        valueMap.put(WC_Fields.httpProxyHost,
                     config.getHttpProxyHost());
        valueMap.put(WC_Fields.httpProxyPort,
                     config.getHttpProxyPort());
        valueMap.put(WC_Fields.httpProxyUsername,
                     config.getHttpProxyUsername());
        valueMap.put(WC_Fields.retrySleepMillis,
                     config.getRetrySleepMillis());
        valueMap.put(WC_Fields.maxRetryTimes,
                     config.getMaxRetryTimes());
        valueMap.put(WC_Fields.jsapiTicket,
                     config.getJsapiTicket());
        valueMap.put(WC_Fields.jsapiTicketExpiresTime,
                     config.getJsapiTicketExpiresTime());
        valueMap.put(WC_Fields.sdkTicket,
                     config.getSdkTicket());
        valueMap.put(WC_Fields.sdkTicketExpiresTime,
                     config.getSdkTicketExpiresTime());
        valueMap.put(WC_Fields.cardApiTicket,
                     config.getCardApiTicket());
        valueMap.put(WC_Fields.cardApiTicketExpiresTime,
                     config.getCardApiTicketExpiresTime());
        valueMap.put(WC_Fields.tmpDirFile,
                     config.getTmpDirFile());
    }

    /**
     * 更新至缓存
     *
     * @param appId     微信公众号标识
     * @param fieldName 字段名
     * @param value     值
     */
    private void updateCache(String appId,
                             String fieldName,
                             Object value) {
        //更新缓存
        if (!cacheValueMap.containsKey(appId))
            cacheValueMap.put(appId,
                              new HashMap<>());
        cacheValueMap.get(appId)
                     .put(fieldName,
                          value);
    }

    /**
     * 是否为加密存储字段
     *
     * @param fieldName 字段名称
     */
    private boolean isEncryptField(String fieldName) {
        switch (fieldName) {
            case WC_Fields.secret:
            case WC_Fields.token:
            case WC_Fields.accessToken:
            case WC_Fields.aesKey:
            case WC_Fields.httpProxyPassword:
            case WC_Fields.jsapiTicket:
            case WC_Fields.sdkTicket:
            case WC_Fields.cardApiTicket:
                //需要解密
                return true;
            default:
                //无需解密
                return false;
        }
    }

    /**
     * 解密
     *
     * @param config 配置信息
     */
    private void decrypt(WeChatConfig config) {
        try {
            for (Field field : WeChatConfig.class.getDeclaredFields()) {
                if (!isEncryptField(field.getName()))
                    continue;

                field.setAccessible(true);
                Object value = decrypt((String) field.get(config));
                field.set(config,
                          value);
            }
        } catch (Exception ex) {
            throw new BusinessException("Jasypt解密失败",
                                        ex);
        }
    }

    /**
     * 解密
     *
     * @param value 值
     * @return 解密后的值
     */
    private String decrypt(String value) {
        if (!StringUtils.hasText(value))
            return value;

        try {
            return JasyptUtils.decrypt(value,
                                       JasyptUtils.getPassword());
        } catch (Exception ex) {
            throw new BusinessException("Jasypt解密失败",
                                        ex);
        }
    }

    /**
     * 加密
     *
     * @param config 配置信息
     */
    private void encrypt(WeChatConfig config) {
        try {
            for (Field field : WeChatConfig.class.getDeclaredFields()) {
                if (!isEncryptField(field.getName()))
                    continue;

                field.setAccessible(true);
                Object value = encrypt((String) field.get(config));
                field.set(config,
                          value);
            }
        } catch (Exception ex) {
            throw new BusinessException("Jasypt加密失败",
                                        ex);
        }
    }

    /**
     * 加密
     *
     * @param value 值
     * @return 加密后的值
     */
    private String encrypt(String value) {
        if (!StringUtils.hasText(value))
            return value;

        try {
            return JasyptUtils.encrypt(value,
                                       JasyptUtils.getPassword());
        } catch (Exception ex) {
            throw new BusinessException("Jasypt加密失败",
                                        ex);
        }
    }

    @Override
    public List<C_List> list(DataSearchDTO dataSearch)
            throws
            BusinessException {
        try {
            List<C_List> result = repository_Key.select()
                                                .as(tableKeyAliasMap.get(defaultTableKey))
                                                .where(x -> x.and(DataSearchExtension.toDynamicFilter(dataSearch.getFilters(),
                                                                                                      tableKeyAliasMap)))
                                                .orderBy(x -> dataSearch.getOrder() == null
                                                              ? x.orderBy(WC_Fields.createTime)
                                                              : x.orderBy(DataSearchExtension.toDynamicOrder(dataSearch.getOrder(),
                                                                                                             tableKeyAliasMap)))
                                                .pagination(dataSearch.getPagination())
                                                .mainTagLevel(1)
                                                .toList(C_List.class);

            result.forEach(this::decrypt);

            return result;
        } catch (Throwable ex) {
            throw new BusinessException("查询数据失败",
                                        ex);
        }
    }

    @Override
    public C_Detail detail(String id)
            throws
            BusinessException {
        try {
            C_Detail result = repository_Key.select()
                                            .where(x -> x.and(WC_Fields.id,
                                                              FilterCompare.Eq,
                                                              id))
                                            .mainTagLevel(1)
                                            .first(C_Detail.class);

            decrypt(result);

            return result;
        } catch (NullResultException ex) {
            throw new BusinessException(ex.getMessage());
        } catch (Throwable ex) {
            throw new BusinessException("获取详情数据失败",
                                        ex);
        }
    }

    @Override
    public void create(C_Create data)
            throws
            BusinessException {
        try {
            entityExtension.initialization(data);
            encrypt(data);
            repository_Key.insert(data,
                                  C_Create.class,
                                  1);
        } catch (Exception ex) {
            throw new BusinessException("新增数据失败",
                                        ex);
        }
    }

    @Override
    public C_Edit edit(String id)
            throws
            BusinessException {
        try {
            return repository_Key.getByIdAndCheckNull(id,
                                                      C_Edit.class,
                                                      1);
        } catch (Exception ex) {
            throw new BusinessException("获取编辑数据失败",
                                        ex);
        }
    }

    @Override
    public void edit(C_Edit data)
            throws
            BusinessException {
        try {
            entityExtension.modify(data);
            encrypt(data);
            repository_Key.update(data,
                                  C_Edit.class,
                                  1);
        } catch (Exception ex) {
            throw new BusinessException("编辑数据失败",
                                        ex);
        }
    }

    @Override
    @Transactional
    public void delete(Collection<String> ids)
            throws
            BusinessException {
        try {
            repository_Key.deleteByIds(ids);
        } catch (Exception ex) {
            throw new BusinessException("删除数据失败",
                                        ex);
        }
    }

    @Override
    public boolean configExist(String appId) {
        return repository_Key.select()
                             .where(x -> x.and(WC_Fields.appId,
                                               FilterCompare.Eq,
                                               appId))
                             .any();
    }

    @Override
    public WeChatConfig newConfig(String appId,
                                  String secret,
                                  String token,
                                  String aesKey) {
        WeChatConfig config = new WeChatConfig();
        entityExtension.initialization(config);
        config.setAppId(appId);
        config.setSecret(secret);
        config.setToken(token);
        config.setAesKey(aesKey);
//        config.setExpiresTime(-1L);
//        config.setHttpProxyPort(-1);
//        config.setRetrySleepMillis(-1);
//        config.setMaxRetryTimes(-1);
//        config.setJsapiTicketExpiresTime(-1L);
//        config.setSdkTicketExpiresTime(-1L);
//        config.setCardApiTicketExpiresTime(-1L);
        repository_Key.insert(config);

        updateCache(config);

        return config;
    }

    @Override
    public <T> T queryValue(String appId,
                            String fieldName,
                            Class<T> type) {
        Object result;
        //读取缓存
        if (cacheValueMap.containsKey(appId)
                && cacheValueMap.get(appId)
                                .containsKey(fieldName))
            result = (T) cacheValueMap.get(appId)
                                      .get(fieldName);
        else {
            result = repository_Key.select()
                                   .where(x -> x.and(WC_Fields.appId,
                                                     FilterCompare.Eq,
                                                     appId))
                                   .first(fieldName,
                                          type);
        }

        if (result != null) {
            if (isEncryptField(fieldName))
                return (T) decrypt((String) result);
            else
                return (T) result;
        } else
            return null;
    }

    @Override
    public <T> void updateValue(String appId,
                                String fieldName,
                                T value) {
        T finalValue;
        if (value != null && isEncryptField(fieldName))
            finalValue = (T) encrypt((String) value);
        else
            finalValue = value;

        repository_Key.updateDiy()
                      .where(x -> x.and(WC_Fields.appId,
                                        FilterCompare.Eq,
                                        appId))
                      .set(fieldName,
                           finalValue)
                      .set(WC_Fields.updateTime,
                           new Date())
                      .executeAffrows();

        updateCache(appId,
                    fieldName,
                    value);
    }
}
