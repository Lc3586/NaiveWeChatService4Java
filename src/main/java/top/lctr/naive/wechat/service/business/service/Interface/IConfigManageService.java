package top.lctr.naive.wechat.service.business.service.Interface;

import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import top.lctr.naive.wechat.service.dto.configDTO.*;
import top.lctr.naive.wechat.service.entity.WeChatConfig;

import java.util.Collection;

/**
 * 微信配置服务接口类
 *
 * @author LCTR
 * @date 2023-03-28
 */
public interface IConfigManageService {
    /**
     * 列表数据
     *
     * @param dataSearch 数据搜索参数
     * @return 列表数据集合
     */
    java.util.List<C_List> list(DataSearchDTO dataSearch);

    /**
     * 详情数据
     *
     * @param id 主键
     * @return 详情数据
     */
    C_Detail detail(String id);

    /**
     * 新增
     *
     * @param data 数据
     */
    void create(C_Create data);

    /**
     * 获取编辑数据
     *
     * @param id 主键
     * @return 编辑数据
     */
    C_Edit edit(String id);

    /**
     * 编辑
     *
     * @param data 数据
     */
    void edit(C_Edit data);

    /**
     * 删除
     *
     * @param ids 主键集合
     */
    void delete(Collection<String> ids);

    /**
     * 是否存在该配置
     *
     * @param appId 微信公众号标识
     */
    boolean configExist(String appId);

    /**
     * 创建新配置
     *
     * @param appId  微信公众号标识
     * @param secret 公众账号密钥
     * @param token  令牌
     * @param aesKey AES加密密钥
     * @return 配置
     */
    WeChatConfig newConfig(String appId,
                           String secret,
                           String token,
                           String aesKey);

    /**
     * 查询指定字段的值
     * <p>如果字段是加密存储的，则会自动加密</p>
     *
     * @param <T>       值类型
     * @param appId     微信公众号标识
     * @param fieldName 字段名
     * @param type      数据类型
     * @return 值
     */
    <T> T queryValue(String appId,
                     String fieldName,
                     Class<T> type);

    /**
     * 更新指定字段的值
     * <p>如果appId没有对应的配置，则会自动创建</p>
     * <p>如果字段是加密存储的，则会自动解密</p>
     *
     * @param <T>       值类型
     * @param appId     微信公众号标识
     * @param fieldName 字段名
     * @param value     值
     */
    <T> void updateValue(String appId,
                         String fieldName,
                         T value);
}
