package top.lctr.naive.wechat.service.business.utils;

import me.chanjar.weixin.common.enums.TicketType;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import project.extension.wechat.config.MpConfig;
import top.lctr.naive.wechat.service.business.service.Interface.IConfigManageService;
import top.lctr.naive.wechat.service.entityFields.WC_Fields;

import java.io.File;

/**
 * 微信配置数据持久化到数据库存储类
 *
 * @author LCTR
 * @date 2023-03-28
 */
public class ConfigDatabaseStorage
        extends WxMpDefaultConfigImpl {
    public ConfigDatabaseStorage(MpConfig mpConfig,
                                 IConfigManageService configManageService) {
        this.mpConfig = mpConfig;
        this.configManageService = configManageService;
        initial();
    }

    private final MpConfig mpConfig;

    private final IConfigManageService configManageService;

    /**
     * 日志组件
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 初始化
     */
    private void initial() {
        this.setAppId(mpConfig.getAppId());
        this.setSecret(mpConfig.getAppSecret());
        this.setToken(mpConfig.getToken());
        this.setAesKey(mpConfig.getAesKey());

        if (!configManageService.configExist(appId))
            configManageService.newConfig(appId,
                                          secret,
                                          token,
                                          aesKey);
    }

    @Override
    public void setAppId(String appId) {
        super.setAppId(appId);
    }

    @Override
    public String getSecret() {
        if (super.getSecret() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.secret,
                                                              String.class);
                super.setSecret(value);
            }
        }

        return super.getSecret();
    }

    @Override
    public void setSecret(String secret) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.secret,
                                            secret);
        }

        super.setSecret(secret);
    }

    @Override
    public String getToken() {
        if (super.getToken() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.token,
                                                              String.class);
                super.setToken(value);
            }
        }

        return super.getToken();
    }

    @Override
    public void setToken(String token) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.token,
                                            token);
        }

        super.setToken(token);
    }

    @Override
    public String getTemplateId() {
        if (super.getTemplateId() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.templateId,
                                                              String.class);
                super.setTemplateId(value);
            }
        }

        return super.getTemplateId();
    }

    @Override
    public void setTemplateId(String templateId) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.templateId,
                                            templateId);
        }

        super.setTemplateId(templateId);
    }

    @Override
    public String getAccessToken() {
        if (super.getAccessToken() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.accessToken,
                                                              String.class);
                super.setAccessToken(value);
            }
        }

        return super.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.accessToken,
                                            accessToken);
        }

        super.setAccessToken(accessToken);
    }

    @Override
    public String getAesKey() {
        if (super.getAesKey() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.aesKey,
                                                              String.class);
                super.setAesKey(value);
            }
        }

        return super.getAesKey();
    }

    @Override
    public void setAesKey(String aesKey) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.aesKey,
                                            aesKey);
        }

        super.setAesKey(aesKey);
    }

    @Override
    public long getExpiresTime() {
        if (super.getExpiresTime() == 0) {
            if (StringUtils.hasText(getAppId())) {
                Long value = configManageService.queryValue(getAppId(),
                                                            WC_Fields.expiresTime,
                                                            long.class);
                if (value != null)
                    super.setExpiresTime(value);
            }
        }

        return super.getExpiresTime();
    }

    @Override
    public void setExpiresTime(long expiresTime) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.expiresTime,
                                            expiresTime);
        }

        super.setExpiresTime(expiresTime);
    }

    @Override
    public String getOauth2redirectUri() {
        if (super.getOauth2redirectUri() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.oauth2redirectUri,
                                                              String.class);
                super.setOauth2redirectUri(value);
            }
        }

        return super.getOauth2redirectUri();
    }

    @Override
    public void setOauth2redirectUri(String oauth2redirectUri) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.oauth2redirectUri,
                                            oauth2redirectUri);
        }

        super.setOauth2redirectUri(oauth2redirectUri);
    }

    @Override
    public String getHttpProxyHost() {
        if (super.getHttpProxyHost() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.httpProxyHost,
                                                              String.class);
                super.setHttpProxyHost(value);
            }
        }

        return super.getHttpProxyHost();
    }

    @Override
    public void setHttpProxyHost(String httpProxyHost) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.httpProxyHost,
                                            httpProxyHost);
        }

        super.setHttpProxyHost(httpProxyHost);
    }

    @Override
    public int getHttpProxyPort() {
        if (super.getHttpProxyPort() == 0) {
            if (StringUtils.hasText(getAppId())) {
                Integer value = configManageService.queryValue(getAppId(),
                                                               WC_Fields.httpProxyPort,
                                                               int.class);
                if (value != null)
                    super.setHttpProxyPort(value);
            }
        }

        return super.getHttpProxyPort();
    }

    @Override
    public void setHttpProxyPort(int httpProxyPort) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.httpProxyPort,
                                            httpProxyPort);
        }

        super.setHttpProxyPort(httpProxyPort);
    }

    @Override
    public String getHttpProxyUsername() {
        if (super.getHttpProxyUsername() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.httpProxyUsername,
                                                              String.class);
                super.setHttpProxyUsername(value);
            }
        }

        return super.getHttpProxyUsername();
    }

    @Override
    public void setHttpProxyUsername(String httpProxyUsername) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.httpProxyUsername,
                                            httpProxyUsername);
        }

        super.setHttpProxyUsername(httpProxyUsername);
    }

    @Override
    public String getHttpProxyPassword() {
        if (super.getHttpProxyPassword() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.httpProxyPassword,
                                                              String.class);
                super.setHttpProxyPassword(value);
            }
        }

        return super.getHttpProxyPassword();
    }

    @Override
    public void setHttpProxyPassword(String httpProxyPassword) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.httpProxyPassword,
                                            httpProxyPassword);
        }

        super.setHttpProxyPassword(httpProxyPassword);
    }

    @Override
    public int getRetrySleepMillis() {
        if (super.getRetrySleepMillis() == 0) {
            if (StringUtils.hasText(getAppId())) {
                Integer value = configManageService.queryValue(getAppId(),
                                                               WC_Fields.retrySleepMillis,
                                                               int.class);
                if (value != null)
                    super.setRetrySleepMillis(value);
            }
        }

        return super.getRetrySleepMillis();
    }

    @Override
    public void setRetrySleepMillis(int retrySleepMillis) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.retrySleepMillis,
                                            retrySleepMillis);
        }

        super.setRetrySleepMillis(retrySleepMillis);
    }

    @Override
    public int getMaxRetryTimes() {
        if (super.getMaxRetryTimes() == 0) {
            if (StringUtils.hasText(getAppId())) {
                Integer value = configManageService.queryValue(getAppId(),
                                                               WC_Fields.maxRetryTimes,
                                                               int.class);
                if (value != null)
                    super.setMaxRetryTimes(value);
            }
        }

        return super.getMaxRetryTimes();
    }

    @Override
    public void setMaxRetryTimes(int maxRetryTimes) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.maxRetryTimes,
                                            maxRetryTimes);
        }

        super.setMaxRetryTimes(maxRetryTimes);
    }

    @Override
    public String getJsapiTicket() {
        if (super.getJsapiTicket() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.jsapiTicket,
                                                              String.class);
                super.setJsapiTicket(value);
            }
        }

        return super.getJsapiTicket();
    }

    @Override
    public void setJsapiTicket(String jsapiTicket) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.jsapiTicket,
                                            jsapiTicket);
        }

        super.setJsapiTicket(jsapiTicket);
    }

    @Override
    public long getJsapiTicketExpiresTime() {
        if (super.getJsapiTicketExpiresTime() == 0) {
            if (StringUtils.hasText(getAppId())) {
                Long value = configManageService.queryValue(getAppId(),
                                                            WC_Fields.jsapiTicketExpiresTime,
                                                            long.class);
                if (value != null)
                    super.setJsapiTicketExpiresTime(value);
            }
        }

        return super.getJsapiTicketExpiresTime();
    }

    @Override
    public void setJsapiTicketExpiresTime(long jsapiTicketExpiresTime) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.jsapiTicketExpiresTime,
                                            jsapiTicketExpiresTime);
        }

        super.setJsapiTicketExpiresTime(jsapiTicketExpiresTime);
    }

    @Override
    public String getSdkTicket() {
        if (super.getSdkTicket() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.sdkTicket,
                                                              String.class);
                super.setSdkTicket(value);
            }
        }

        return super.getSdkTicket();
    }

    @Override
    public void setSdkTicket(String sdkTicket) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.sdkTicket,
                                            sdkTicket);
        }

        super.setSdkTicket(sdkTicket);
    }

    @Override
    public long getSdkTicketExpiresTime() {
        if (super.getSdkTicketExpiresTime() == 0) {
            if (StringUtils.hasText(getAppId())) {
                Long value = configManageService.queryValue(getAppId(),
                                                            WC_Fields.sdkTicketExpiresTime,
                                                            long.class);
                if (value != null)
                    super.setSdkTicketExpiresTime(value);
            }
        }

        return super.getSdkTicketExpiresTime();
    }

    @Override
    public void setSdkTicketExpiresTime(long sdkTicketExpiresTime) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.sdkTicketExpiresTime,
                                            sdkTicketExpiresTime);
        }

        super.setSdkTicketExpiresTime(sdkTicketExpiresTime);
    }

    @Override
    public String getCardApiTicket() {
        if (super.getCardApiTicket() == null) {
            if (StringUtils.hasText(getAppId())) {
                String value = configManageService.queryValue(getAppId(),
                                                              WC_Fields.cardApiTicket,
                                                              String.class);
                super.setCardApiTicket(value);
            }
        }

        return super.getCardApiTicket();
    }

    @Override
    public void setCardApiTicket(String cardApiTicket) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.cardApiTicket,
                                            cardApiTicket);
        }

        super.setCardApiTicket(cardApiTicket);
    }

    @Override
    public long getCardApiTicketExpiresTime() {
        if (super.getCardApiTicketExpiresTime() == 0) {
            if (StringUtils.hasText(getAppId())) {
                Long value = configManageService.queryValue(getAppId(),
                                                            WC_Fields.cardApiTicketExpiresTime,
                                                            long.class);
                if (value != null)
                    super.setCardApiTicketExpiresTime(value);
            }
        }

        return super.getCardApiTicketExpiresTime();
    }

    @Override
    public void setCardApiTicketExpiresTime(long cardApiTicketExpiresTime) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.cardApiTicketExpiresTime,
                                            cardApiTicketExpiresTime);
        }

        super.setCardApiTicketExpiresTime(cardApiTicketExpiresTime);
    }

    @Override
    public File getTmpDirFile() {
        if (super.getTmpDirFile() == null) {
            if (StringUtils.hasText(getAppId())) {
                String path = configManageService.queryValue(getAppId(),
                                                             WC_Fields.tmpDirFile,
                                                             String.class);
                super.setTmpDirFile(new File(path));
            }
        }

        return super.getTmpDirFile();
    }

    @Override
    public void setTmpDirFile(File tmpDirFile) {
        if (StringUtils.hasText(getAppId())) {
            configManageService.updateValue(getAppId(),
                                            WC_Fields.tmpDirFile,
                                            tmpDirFile.getAbsolutePath());
        }

        super.setTmpDirFile(tmpDirFile);
    }

    @Override
    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > getExpiresTime();
    }

    @Override
    public synchronized void updateAccessToken(String accessToken,
                                               int expiresInSeconds) {
        setAccessToken(accessToken);
        setExpiresTime(System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L);
    }

    @Override
    public void expireAccessToken() {
        setExpiresTime(0);
    }

    @Override
    public String getTicket(TicketType type) {
        switch (type) {
            case SDK:
                return getSdkTicket();
            case JSAPI:
                return getJsapiTicket();
            case WX_CARD:
                return getCardApiTicket();
            default:
                return null;
        }
    }

    @Override
    public void setTicket(TicketType type,
                          String ticket) {
        switch (type) {
            case JSAPI:
                setJsapiTicket(ticket);
                break;
            case WX_CARD:
                setCardApiTicket(ticket);
                break;
            case SDK:
                setSdkTicket(ticket);
                break;
            default:
        }
    }

    @Override
    public boolean isTicketExpired(TicketType type) {
        switch (type) {
            case SDK:
                return System.currentTimeMillis() > getSdkTicketExpiresTime();
            case JSAPI:
                return System.currentTimeMillis() > getJsapiTicketExpiresTime();
            case WX_CARD:
                return System.currentTimeMillis() > getCardApiTicketExpiresTime();
            default:
                return false;
        }
    }

    @Override
    public synchronized void updateTicket(TicketType type,
                                          String ticket,
                                          int expiresInSeconds) {
        switch (type) {
            case JSAPI:
                setJsapiTicket(ticket);
                // 预留200秒的时间
                setJsapiTicketExpiresTime(System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L);
                break;
            case WX_CARD:
                setCardApiTicket(ticket);
                // 预留200秒的时间
                setCardApiTicketExpiresTime(System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L);
                break;
            case SDK:
                setSdkTicket(ticket);
                // 预留200秒的时间
                setSdkTicketExpiresTime(System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L);
                break;
            default:
        }
    }

    @Override
    public void expireTicket(TicketType type) {
        switch (type) {
            case JSAPI:
                setJsapiTicketExpiresTime(0);
                break;
            case WX_CARD:
                setCardApiTicketExpiresTime(0);
                break;
            case SDK:
                setSdkTicketExpiresTime(0);
                break;
            default:
        }
    }
}
