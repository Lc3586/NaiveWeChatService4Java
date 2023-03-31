package top.lctr.naive.wechat.service.dto;

import lombok.Data;

/**
 * 微信网页授权时携带的参数信息
 *
 * @author LCTR
 * @date 2023-03-29
 */
@Data
public class StateInfo {
    public StateInfo() {

    }

    public StateInfo(WeChatOAuth2Type type,
                     String groupId,
                     String returnUrl) {
        setType(type);
        setGroupId(groupId);
        setReturnUrl(returnUrl);
    }

    /**
     * 分组标识
     */
    private WeChatOAuth2Type type;

    /**
     * 分组标识
     */
    private String groupId;

    /**
     * 回调地址
     */
    private String returnUrl;
}
