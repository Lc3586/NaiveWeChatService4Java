package top.lctr.naive.wechat.service.dto;

import lombok.Data;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

import java.util.Map;

/**
 * 微信公众号消息信息
 *
 * @author LCTR
 * @date 2023-04-12
 */
@Data
public class MpMessageInfo {
    public MpMessageInfo() {

    }

    public MpMessageInfo(WxMpXmlMessage wxMessage,
                         Map<String, Object> context,
                         WxMpService wxMpService,
                         WxSessionManager sessionManager) {
        setWxMessage(wxMessage);
        setContext(context);
        setWxMpService(wxMpService);
        setSessionManager(sessionManager);
    }

    /**
     * 微信推送消息
     */
    private WxMpXmlMessage wxMessage;

    /**
     * 上下文，如果handler或interceptor之间有信息要传递，可以用这个
     */
    private Map<String, Object> context;

    /**
     * 服务类
     */
    private WxMpService wxMpService;

    /**
     * session管理器
     */
    private WxSessionManager sessionManager;
}
