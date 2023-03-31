package top.lctr.naive.wechat.service.business.service.Interface;

/**
 * 微信授权处理类
 *
 * @author LCTR
 * @date 2023-03-31
 */
public interface IOAuth2Service {
    /**
     * 发起基础授权
     * <p>跳转至微信的授权页面</p>
     * <p>结束后重定向至回调地址，并附带以下参数：</p>
     * <p>state，用于获取授权信息</p>
     *
     * @param groupId   分组标识（可选）
     * @param returnUrl 回调地址
     */
    void base(String groupId,
              String returnUrl);

    /**
     * 发起用户信息授权
     * <p>跳转至微信的授权页面</p>
     * <p>结束后重定向至回调地址，并附带以下参数：</p>
     * <p>state，用于获取用户信息</p>
     *
     * @param groupId   分组标识（可选）
     * @param returnUrl 回调地址
     */
    void userInfo(String groupId,
                  String returnUrl);
}
