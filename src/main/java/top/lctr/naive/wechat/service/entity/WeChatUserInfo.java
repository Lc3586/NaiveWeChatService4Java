package top.lctr.naive.wechat.service.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import project.extension.mybatis.edge.annotations.ColumnSetting;
import project.extension.mybatis.edge.annotations.TableSetting;
import project.extension.openapi.annotations.OpenApiDescription;
import project.extension.openapi.annotations.OpenApiSubTag;

import java.util.Date;

/**
 * 微信用户信息
 *
 * @author LCTR
 * @date 2023-03-27
 */
@TableSetting
@Alias("WechatUserInfo")
@Data
public class WeChatUserInfo {
    /**
     * 主键
     */
    @OpenApiDescription("主键")
    @ColumnSetting(isPrimaryKey = true,
                   length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Edit",
                    "Base"})
    private String id;

    /**
     * 微信公众号标识
     */
    @OpenApiDescription("微信公众号标识")
    @ColumnSetting(length = 36,
                   isNullable = false)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String appId;

    /**
     * 用户公众号唯一标识
     */
    @OpenApiDescription("用户公众号唯一标识")
    @ColumnSetting(length = 36,
                   isNullable = false)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String openId;

    /**
     * 用户公众平台唯一标识
     */
    @OpenApiDescription("用户公众平台唯一标识")
    @ColumnSetting(length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String unionId;

    /**
     * 分组标识
     */
    @OpenApiDescription("分组标识")
    @ColumnSetting(length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String groupId;

    /**
     * 昵称
     */
    @OpenApiDescription("昵称")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String nickname;

    /**
     * 头像
     */
    @OpenApiDescription("头像")
    @ColumnSetting(length = 2048)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String headimgUrl;

    /**
     * 性别
     */
    @OpenApiDescription("性别")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private Byte sex;

    /**
     * 国家
     */
    @OpenApiDescription("国家")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String country;

    /**
     * 省份
     */
    @OpenApiDescription("省份")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String province;

    /**
     * 城市
     */
    @OpenApiDescription("城市")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String city;

    /**
     * 语言
     */
    @OpenApiDescription("语言")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String language;

    /**
     * 启用
     */
    @OpenApiDescription("启用")
    @ColumnSetting(isNullable = false)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean enable;

    /**
     * 已获取信息授权
     */
    @OpenApiDescription("已获取信息授权")
    @ColumnSetting(isNullable = false)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean authorized;

    /**
     * 备注
     */
    @OpenApiDescription("备注")
    @ColumnSetting(length = -4)
    @OpenApiSubTag({"Detail",
                    "Create",
                    "Edit"})
    private String remark;

    /**
     * 创建时间
     */
    @OpenApiDescription("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @OpenApiSubTag({"Detail"})
    private Date createTime;

    /**
     * 更新时间
     */
    @OpenApiDescription("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @OpenApiSubTag({"Detail",
                    "_Edit"})
    private Date updateTime;
}
