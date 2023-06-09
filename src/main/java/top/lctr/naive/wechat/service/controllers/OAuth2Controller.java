package top.lctr.naive.wechat.service.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import project.extension.openapi.annotations.*;
import top.lctr.naive.wechat.service.business.service.Interface.IOAuth2Service;

/**
 * 微信网页授权服务控制器
 *
 * @author LCTR
 * @date 2023-03-31
 */
@RestController
@RequestMapping(path = "/wechat/oauth2",
                consumes = "*/*",
                produces = "*/*")
@Scope("prototype")
@Api(tags = "网页授权")
@OpenApiGroup("微信服务")
public class OAuth2Controller
        extends BaseController {
    public OAuth2Controller(IOAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }

    private final IOAuth2Service oAuth2Service;

    /**
     * 发起基础授权
     * <p>跳转至微信的授权页面</p>
     * <p>结束后重定向至回调地址，并附带以下参数：</p>
     * <p>state，用于获取授权信息</p>
     *
     * @param groupId   分组标识（可选）
     * @param returnUrl 回调地址
     */
    @GetMapping(value = "/base")
    @ApiOperation(value = "发起基础授权",
                  notes = "跳转至微信的授权页面"
                          + "\r\n结束后重定向至回调地址，并附带以下参数："
                          + "\r\n\tstate，用于获取授权信息",
                  response = Object.class)
    public void base(
            @Parameter(name = "groupId",
                       description = "分组标识")
                    String groupId,
            @Parameter(name = "returnUrl",
                       description = "回调地址",
                       required = true)
                    String returnUrl) {
        oAuth2Service.base(groupId,
                           returnUrl);
    }

    /**
     * 发起用户信息授权
     * <p>跳转至微信的授权页面</p>
     * <p>结束后重定向至回调地址，并附带以下参数：</p>
     * <p>state，用于获取用户信息</p>
     *
     * @param groupId   分组标识（可选）
     * @param returnUrl 回调地址
     */
    @GetMapping(value = "/user-info")
    @ApiOperation(value = "发起用户信息授权",
                  notes = "跳转至微信的授权页面"
                          + "\r\n结束后重定向至回调地址，并附带以下参数："
                          + "\r\n\tstate，用于获取用户信息",
                  response = Object.class)
    public void userInfo(
            @Parameter(name = "groupId",
                       description = "分组标识")
                    String groupId,
            @Parameter(name = "returnUrl",
                       description = "回调地址",
                       required = true)
                    String returnUrl) {
        oAuth2Service.userInfo(groupId,
                               returnUrl);
    }
}
