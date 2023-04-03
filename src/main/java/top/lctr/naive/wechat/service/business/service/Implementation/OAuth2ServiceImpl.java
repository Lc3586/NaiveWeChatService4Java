package top.lctr.naive.wechat.service.business.service.Implementation;

import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import project.extension.standard.entity.IEntityExtension;
import project.extension.standard.exception.BusinessException;
import project.extension.wechat.config.MpConfig;
import project.extension.wechat.core.mp.handler.IWeChatOAuth2Handler;
import project.extension.wechat.core.mp.servlet.WeChatOAuth2Servlet;
import project.extension.wechat.core.mp.standard.IWeChatMpService;
import top.lctr.naive.wechat.service.business.service.Interface.IOAuth2Service;
import top.lctr.naive.wechat.service.business.service.Interface.IUserInfoService;
import top.lctr.naive.wechat.service.dto.StateInfo;
import top.lctr.naive.wechat.service.dto.WeChatOAuth2Type;
import top.lctr.naive.wechat.service.dto.userInfoDTO.RedisScopeInfo;
import top.lctr.naive.wechat.service.dto.userInfoDTO.RedisUserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信授权处理类
 *
 * @author LCTR
 * @date 2023-03-28
 */
@Service
@Scope("prototype")
public class OAuth2ServiceImpl
        implements IOAuth2Service,
                   IWeChatOAuth2Handler {
    public OAuth2ServiceImpl(IWeChatMpService weChatMpService,
                             IUserInfoService userInfoService,
                             RedisTemplate<String, RedisScopeInfo> scopeInfoRedisTemplate,
                             RedisTemplate<String, RedisUserInfo> userInfoRedisTemplate,
                             IEntityExtension entityExtension) {
        this.entityExtension = entityExtension;
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (servletRequestAttributes != null) {
            this.response = servletRequestAttributes.getResponse();
        } else {
//            throw new Exception("获取ServletRequestAttributes对象失败");
            this.response = null;
        }
        this.weChatMpService = weChatMpService;
        this.userInfoService = userInfoService;
        this.scopeInfoRedisTemplate = scopeInfoRedisTemplate;
        this.userInfoRedisTemplate = userInfoRedisTemplate;
    }

    /**
     * 响应对象
     */
    private final HttpServletResponse response;

    private final IWeChatMpService weChatMpService;

    private final IUserInfoService userInfoService;

    private final RedisTemplate<String, RedisScopeInfo> scopeInfoRedisTemplate;

    private final RedisTemplate<String, RedisUserInfo> userInfoRedisTemplate;

    private final IEntityExtension entityExtension;

    /**
     * state参数信息集合
     */
    private static final Map<String, StateInfo> stateInfoMap = new HashMap<>();

    /**
     * 创建参数
     *
     * @param type      网页授权类型
     * @param groupId   分组标识
     * @param returnUrl 回调地址
     * @return 参数
     */
    private String createState(WeChatOAuth2Type type,
                               String groupId,
                               String returnUrl) {
        String state = newState();
        StateInfo stateInfo = new StateInfo(type,
                                            groupId,
                                            returnUrl);
        stateInfoMap.put(state,
                         stateInfo);
        return state;
    }

    /**
     * 移除参数
     *
     * @param state 参数
     */
    private void removeState(String state) {
        stateInfoMap.remove(state);
    }

    /**
     * 更新缓存中的微信用户授权信息
     */
    private void updateRedisScopeInfo(String state,
                                      RedisScopeInfo scopeInfo) {
        scopeInfoRedisTemplate.opsForValue()
                              .set(state,
                                   scopeInfo);
    }

    /**
     * 更新缓存中的微信用户信息
     */
    private void updateRedisUserInfo(String state,
                                     RedisUserInfo userInfo) {
        userInfoRedisTemplate.opsForValue()
                             .set(state,
                                  userInfo);
    }

    /**
     * 检查state参数是否有效
     */
    private void checkState(String state) {
        if (!StringUtils.hasText(state))
            throw new BusinessException("state参数不可为空");

        if (stateInfoMap.containsKey(state))
            throw new BusinessException("无效的state参数");
    }

    /**
     * 获取新的state参数
     */
    private String newState() {
        return entityExtension.newStringId();
    }

    /**
     * 添加state参数至链接地址中
     *
     * @param url   链接地址
     * @param state 参数
     * @return 添加了state参数的链接地址
     */
    private String addStateParameter2Url(String url,
                                         String state) {
        return String.format("%s%s%s=%s",
                             url,
                             url.contains("?")
                             ? "&"
                             : "?",
                             WeChatOAuth2Servlet.STATE_PARAMETER,
                             state);
    }

    @Override
    public String Handler(HttpServletRequest request,
                          MpConfig config,
                          WxOAuth2AccessToken accessToken,
                          String state) {
        checkState(state);

        StateInfo stateInfo = stateInfoMap.get(state);

        if (accessToken.getSnapshotUser() != 1
                && !userInfoService.infoExist(config.getAppId(),
                                              accessToken.getOpenId(),
                                              stateInfo.getGroupId()))
            userInfoService.newInfo(config.getAppId(),
                                    accessToken.getOpenId(),
                                    stateInfo.getGroupId());

        Boolean authorized = userInfoService.infoAuthorized(config.getAppId(),
                                                            accessToken.getOpenId(),
                                                            stateInfo.getGroupId());

        if (authorized)
            updateRedisUserInfo(state,
                                userInfoService.getUserInfo(config.getAppId(),
                                                            accessToken.getOpenId(),
                                                            stateInfo.getGroupId()));

        updateRedisScopeInfo(state,
                             new RedisScopeInfo(config.getAppId(),
                                                stateInfo.getGroupId(),
                                                authorized,
                                                accessToken));

        return addStateParameter2Url(stateInfo.getReturnUrl(),
                                     state);
    }

    @Override
    public String Handler(HttpServletRequest request,
                          MpConfig config,
                          WxOAuth2UserInfo userinfo,
                          String state) {
        checkState(state);

        StateInfo stateInfo = stateInfoMap.get(state);

        if (!userInfoService.infoExist(config.getAppId(),
                                       userinfo.getOpenid(),
                                       stateInfo.getGroupId()))
            userInfoService.newInfo(config.getAppId(),
                                    userinfo.getOpenid(),
                                    stateInfo.getGroupId());

        userInfoService.update(config.getAppId(),
                               stateInfo.getGroupId(),
                               userinfo);

        updateRedisUserInfo(state,
                            new RedisUserInfo(config.getAppId(),
                                              userinfo,
                                              stateInfo.getGroupId(),
                                              config.getLanguage()));

        return addStateParameter2Url(stateInfo.getReturnUrl(),
                                     state);
    }

    @Override
    public void base(String groupId,
                     String returnUrl) {
        String state = createState(WeChatOAuth2Type.BASE,
                                   groupId,
                                   returnUrl);

        String url = addStateParameter2Url(weChatMpService.getBaseOAuth2Url(),
                                           state);

        try {
            response.sendRedirect(url);
        } catch (IOException ex) {
            removeState(state);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void userInfo(String groupId,
                         String returnUrl) {
        String state = createState(WeChatOAuth2Type.USER_INFO,
                                   groupId,
                                   returnUrl);

        String url = addStateParameter2Url(weChatMpService.getUserInfoOAuth2Url(),
                                           state);

        try {
            response.sendRedirect(url);
        } catch (IOException ex) {
            removeState(state);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
