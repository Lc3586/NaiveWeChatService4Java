package top.lctr.naive.wechat.service.entityFields;

/**
 * 微信配置实体字段
 *
 * @author LCTR
 * @date 2023-03-27
 * @see top.lctr.naive.wechat.service.entity.WeChatConfig
 */
public final class WC_Fields {
    /**
     * 主键
     */
    public static final String id = "id";

    /**
     * 微信公众号标识
     */
    public static final String appId = "appId";

    /**
     * 密钥
     * <p>在数据库中加密存储</p>
     */
    public static final String secret = "secret";

    /**
     * 令牌
     * <p>在数据库中加密存储</p>
     */
    public static final String token = "token";

    /**
     * 模板标识
     */
    public static final String templateId = "templateId";

    /**
     * 访问令牌
     * <p>在数据库中加密存储</p>
     */
    public static final String accessToken = "accessToken";

    /**
     * AES加密密钥
     * <p>在数据库中加密存储</p>
     */
    public static final String aesKey = "aesKey";

    /**
     * 过期时间
     */
    public static final String expiresTime = "expiresTime";

    /**
     * 身份验证跳转地址
     */
    public static final String oauth2redirectUri = "oauth2redirectUri";

    /**
     * http代理主机
     */
    public static final String httpProxyHost = "httpProxyHost";

    /**
     * http代理端口
     */
    public static final String httpProxyPort = "httpProxyPort";

    /**
     * http代理用户名
     */
    public static final String httpProxyUsername = "httpProxyUsername";

    /**
     * http代理密码
     * <p>在数据库中加密存储</p>
     */
    public static final String httpProxyPassword = "httpProxyPassword";

    /**
     * http请求重试间隔毫秒数
     */
    public static final String retrySleepMillis = "retrySleepMillis";

    /**
     * http请求最大重试次数
     */
    public static final String maxRetryTimes = "maxRetryTimes";

    /**
     * H5支付接口票据
     * <p>在数据库中加密存储</p>
     */
    public static final String jsapiTicket = "jsapiTicket";

    /**
     * H5支付接口票据过期时间
     */
    public static final String jsapiTicketExpiresTime = "jsapiTicketExpiresTime";

    /**
     * SDK票据
     * <p>在数据库中加密存储</p>
     */
    public static final String sdkTicket = "sdkTicket";

    /**
     * SDK票据过期时间
     */
    public static final String sdkTicketExpiresTime = "sdkTicketExpiresTime";

    /**
     * 刷卡支付接口票据
     * <p>在数据库中加密存储</p>
     */
    public static final String cardApiTicket = "cardApiTicket";

    /**
     * 刷卡支付接口票据过期时间
     */
    public static final String cardApiTicketExpiresTime = "cardApiTicketExpiresTime";

    /**
     * 临时文件存储目录
     */
    public static final String tmpDirFile = "tmpDirFile";

    /**
     * 创建时间
     */
    public static final String createTime = "createTime";

    /**
     * 更新时间
     */
    public static final String updateTime = "updateTime";
}
