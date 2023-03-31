package top.lctr.naive.wechat.service.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import project.extension.openapi.annotations.*;
import project.extension.openapi.fastjson.ApiDataSchemaExtension;
import project.extension.openapi.fastjson.JsonExtension;
import project.extension.openapi.model.ApiData.*;
import project.extension.standard.api.response.ApiResultData;
import top.lctr.naive.wechat.service.business.service.Interface.IConfigManageService;
import top.lctr.naive.wechat.service.dto.configDTO.*;

import java.util.Collection;

/**
 * 微信配置服务控制器
 *
 * @author LCTR
 * @date 2023-03-30
 */
@RestController
@RequestMapping(path = "/wechat/config",
                consumes = "application/json",
                produces = "application/json")
@Scope("prototype")
@Api(tags = "微信服务")
@OpenApiGroup("微信配置")
public class ConfigController
        extends BaseController {
    public ConfigController(IConfigManageService configManageService) {
        this.configManageService = configManageService;
    }

    private final IConfigManageService configManageService;

    /**
     * 列表数据
     *
     * @param dataSearch 搜索参数
     * @return 列表数据
     */
    @PreAuthorize("@aph.isPass('wechat:config:list')")
    @PostMapping("/list")
    @ApiOperation("获取列表数据")
    @OpenApiModels(defaultGenericTypes = {@OpenApiGenericType(arguments = {@OpenApiGenericTypeArgument(C_List.class)})},
                   defaultDescription = "列表数据",
                   value = {@OpenApiModel(type = RuoyiResult.class,
                                          summary = "Ruoyi框架数据结构方案（默认）",
                                          defaultModel = true),
                            @OpenApiModel(type = LayuiResult.class,
                                          summary = "Layui数据结构方案"),
                            @OpenApiModel(type = JqGridResult.class,
                                          summary = "JqGrid数据结构方案"),
                            @OpenApiModel(type = EasyuiResult.class,
                                          summary = "Easyui、BootstrapTable数据结构方案"),
                            @OpenApiModel(type = AntdVueResult.class,
                                          summary = "AntdVue数据结构方案"),
                            @OpenApiModel(type = ElementVueResult.class,
                                          summary = "ElementVue数据结构方案")})
    public Object list(
            @OpenApiModel(description = "搜索参数")
            @RequestBody
                    DataSearchDTO dataSearch) {
        return JsonExtension.toOpenApiJson(ApiDataSchemaExtension.buildResult(dataSearch.getSchema(),
                                                                              configManageService.list(dataSearch),
                                                                              dataSearch.getPagination()),
                                           C_List.class);
    }

    /**
     * 详情数据
     *
     * @param id 主键
     * @return 详情数据
     */
    @PreAuthorize("@aph.isPass('wechat:config:detail')")
    @GetMapping(value = "/detail/{id}",
                consumes = "*/*")
    @ApiOperation("获取详情数据")
    @OpenApiModel(value = C_Detail.class,
                  description = "详情数据")
    public Object detail(
            @Parameter(name = "id",
                       description = "主键")
            @PathVariable("id")
                    String id) {
        return JsonExtension.toOpenApiJson(ApiResultData.success(configManageService.detail(id)),
                                           C_Detail.class);
    }

    /**
     * 新增
     *
     * @param data 数据
     */
    @PreAuthorize("@aph.isPass('wechat:config:create')")
    @PostMapping("/create")
    @ApiOperation("新增")
    public Object create(
            @OpenApiModel(description = "数据")
            @RequestBody
                    C_Create data) {
        configManageService.create(data);
        return ApiResultData.success();
    }

    /**
     * 获取编辑数据
     *
     * @param id 主键
     * @return 编辑数据
     */
    @PreAuthorize("@aph.isPass('wechat:config:edit')")
    @GetMapping(value = "/edit/{id}",
                consumes = "*/*")
    @ApiOperation("获取编辑数据")
    @OpenApiModel(value = C_Edit.class,
                  description = "编辑数据")
    public Object edit(
            @Parameter(name = "id",
                       description = "主键")
            @PathVariable("id")
                    String id) {
        return JsonExtension.toOpenApiJson(ApiResultData.success(configManageService.edit(id)),
                                           C_Edit.class);
    }

    /**
     * 编辑
     *
     * @param data 数据
     */
    @PreAuthorize("@aph.isPass('wechat:config:edit')")
    @PostMapping("/edit")
    @ApiOperation("编辑")
    public Object edit(
            @OpenApiModel(description = "数据")
            @RequestBody
                    C_Edit data) {
        configManageService.edit(data);
        return ApiResultData.success();
    }

    /**
     * 删除
     *
     * @param ids 主键集合
     */
    @PreAuthorize("@aph.isPass('wechat:config:delete')")
    @PostMapping("/delete")
    @ApiOperation("删除")
    public Object delete(
            @RequestBody
                    Collection<String> ids) {
        configManageService.delete(ids);
        return ApiResultData.success();
    }
}
