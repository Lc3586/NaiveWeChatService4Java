package top.lctr.naive.wechat.service.business.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import project.extension.wechat.core.INaiveWeChatService;
import project.extension.wechat.core.mp.standard.IWeChatMpService;
import top.lctr.naive.wechat.service.business.service.Interface.IMessageService;

/**
 * 微信消息路由服务实现类
 *
 * @author LCTR
 * @date 2023-04-12
 */
@Service
@Scope("prototype")
public class MessageServiceImpl
        implements IMessageService {
    /**
     * 日志组件
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


}
