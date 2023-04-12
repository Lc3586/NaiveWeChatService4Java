package top.lctr.naive.wechat.service.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import project.extension.mybatis.edge.annotations.ColumnSetting;
import project.extension.mybatis.edge.annotations.TableSetting;
import project.extension.openapi.annotations.OpenApiDescription;
import project.extension.openapi.annotations.OpenApiIgnore;
import project.extension.openapi.annotations.OpenApiSubTag;

import java.util.Date;

/**
 * 微信配置
 *
 * @author LCTR
 * @date 2023-03-27
 */
@TableSetting("WechatConfig")
@Alias("WechatConfig")
@JSONType(ignores = "serialVersionUID")
@Data
public class WeChatConfig {
    @ColumnSetting(isIgnore = true)
    @OpenApiIgnore
    @JsonIgnore
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @OpenApiDescription("主键")
    @ColumnSetting(isPrimaryKey = true,
                   length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Edit"})
    private String id;

    /**
     * 微信公众号标识
     */
    @OpenApiDescription("微信公众号标识")
    @ColumnSetting(length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String appId;

    /**
     * 密钥
     * <p>在数据库中加密存储</p>
     */
    @OpenApiDescription("密钥")
    @ColumnSetting(length = 1000)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String secret;

    /**
     * 令牌
     * <p>在数据库中加密存储</p>
     */
    @OpenApiDescription("令牌")
    @ColumnSetting(length = 1000)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String token;

    /**
     * 模板标识
     */
    @OpenApiDescription("模板标识")
    @ColumnSetting(length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String templateId;

    /**
     * 访问令牌
     * <p>在数据库中加密存储</p>
     */
    @OpenApiDescription("访问令牌")
    @ColumnSetting(length = 1000)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String accessToken;

    /**
     * AES加密密钥
     * <p>在数据库中加密存储</p>
     */
    @OpenApiDescription("AES加密密钥")
    @ColumnSetting(length = 1000)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String aesKey;

    /**
     * 过期时间
     */
    @OpenApiDescription("过期时间")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long expiresTime;

    /**
     * 身份验证跳转地址
     */
    @OpenApiDescription("身份验证跳转地址")
    @ColumnSetting(length = 2048)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String oauth2redirectUri;

    /**
     * http代理主机
     */
    @OpenApiDescription("http代理主机")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String httpProxyHost;

    /**
     * http代理端口
     */
    @OpenApiDescription("http代理端口")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Integer httpProxyPort;

    /**
     * http代理用户名
     */
    @OpenApiDescription("http代理用户名")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String httpProxyUsername;

    /**
     * http代理密码
     * <p>在数据库中加密存储</p>
     */
    @OpenApiDescription("http代理密码")
    @ColumnSetting(length = 1000)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String httpProxyPassword;

    /**
     * http请求重试间隔毫秒数
     */
    @OpenApiDescription("http请求重试间隔毫秒数")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Integer retrySleepMillis;

    /**
     * http请求最大重试次数
     */
    @OpenApiDescription("http请求最大重试次数")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Integer maxRetryTimes;

    /**
     * H5支付接口票据
     * <p>在数据库中加密存储</p>
     */
    @OpenApiDescription("H5支付接口票据")
    @ColumnSetting(length = 1000)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String jsapiTicket;

    /**
     * H5支付接口票据过期时间
     */
    @OpenApiDescription("H5支付接口票据过期时间")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long jsapiTicketExpiresTime;

    /**
     * SDK票据
     * <p>在数据库中加密存储</p>
     */
    @OpenApiDescription("SDK票据")
    @ColumnSetting(length = 1000)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String sdkTicket;

    /**
     * SDK票据过期时间
     */
    @OpenApiDescription("SDK票据过期时间")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long sdkTicketExpiresTime;

    /**
     * 刷卡支付接口票据
     * <p>在数据库中加密存储</p>
     */
    @OpenApiDescription("刷卡支付接口票据")
    @ColumnSetting(length = 1000)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String cardApiTicket;

    /**
     * 刷卡支付接口票据过期时间
     */
    @OpenApiDescription("刷卡支付接口票据过期时间")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long cardApiTicketExpiresTime;

    /**
     * 临时文件存储目录
     */
    @OpenApiDescription("临时文件存储目录")
    @ColumnSetting(length = 2048)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String tmpDirFile;

    /**
     * 创建时间
     */
    @OpenApiDescription("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @OpenApiSubTag({"List",
                    "Detail"})
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
