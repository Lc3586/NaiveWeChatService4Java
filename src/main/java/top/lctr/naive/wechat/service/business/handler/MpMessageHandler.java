package top.lctr.naive.wechat.service.business.handler;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.slf4j.LoggerFactory;
import project.extension.ioc.IOCExtension;
import project.extension.task.TaskQueueHandler;
import project.extension.wechat.config.MpConfig;
import project.extension.wechat.core.mp.standard.IWeChatMpService;
import top.lctr.naive.wechat.service.business.service.Interface.IMessageService;
import top.lctr.naive.wechat.service.config.MpMessageConfig;
import top.lctr.naive.wechat.service.dto.MpMessageInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 微信公众号消息处理模块
 *
 * @author LCTR
 * @date 2023-04-12
 */
public class MpMessageHandler
        extends TaskQueueHandler
        implements WxMpMessageHandler {
    public MpMessageHandler(MpMessageConfig mpMessageConfig,
                            MpConfig mpConfig,
                            IWeChatMpService weChatMpService,
                            IMessageService messageRouterService) {
        super(String.format("微信公众号[%s]消息处理模块",
                            mpConfig.getName()),
              mpMessageConfig.getThreadPoolSize(),
              LoggerFactory.getLogger(MpMessageHandler.class));

        this.mpMessageConfig = mpMessageConfig;
        this.mpConfig = mpConfig;
        this.weChatMpService = weChatMpService;
        this.messageService = messageRouterService;
    }

    private final MpMessageConfig mpMessageConfig;

    private final MpConfig mpConfig;

    private final IWeChatMpService weChatMpService;

    private final IMessageService messageService;

    private static final ConcurrentMap<String, MpMessageInfo> messageMap = new ConcurrentHashMap<>();

    /**
     * 创建消息路由
     *
     * @param mpConfig        微信公众号配置
     * @param weChatMpService 微信公众号服务
     */
    public static WxMpMessageRouter createMessageRouter(MpConfig mpConfig,
                                                        IWeChatMpService weChatMpService) {
        MpMessageConfig getMpMessageConfig = IOCExtension.applicationContext.getBean(MpMessageConfig.class);
        IMessageService getMessageRouterService = IOCExtension.applicationContext.getBean(IMessageService.class);
        return new WxMpMessageRouter(weChatMpService.getMpService())
                .rule()
                .handler(new MpMessageHandler(getMpMessageConfig,
                                              mpConfig,
                                              weChatMpService,
                                              getMessageRouterService))
                .end();
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context,
                                    WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        logger.info(String.format("%s：接收消息：%s",
                                  getName(),
                                  wxMessage.toString()));

        String key = wxMessage.toString();
        messageMap.put(key,
                       new MpMessageInfo());
        addTask(key);
        return null;
    }

    @Override
    protected void processingTask(Object taskKey) {

    }
}
