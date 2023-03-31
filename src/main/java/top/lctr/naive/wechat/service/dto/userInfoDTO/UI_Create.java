package top.lctr.naive.wechat.service.dto.userInfoDTO;

import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.naive.wechat.service.entity.WeChatUserInfo;

/**
 * 微信用户信息业务模型
 * <p>新增数据</p>
 *
 * @author LCTR
 * @date 2023-03-28
 */
@OpenApiMainTags({@OpenApiMainTag("Create")})
public class UI_Create
        extends WeChatUserInfo {

}
