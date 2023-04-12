package top.lctr.naive.wechat.service.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import project.extension.mybatis.edge.annotations.ColumnSetting;
import project.extension.mybatis.edge.annotations.TableSetting;
import project.extension.openapi.annotations.OpenApiDescription;
import project.extension.openapi.annotations.OpenApiSubTag;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 微信公众号消息
 *
 * @author LCTR
 * @date 2023-04-12
 */
@TableSetting("WechatUserInfo")
@Alias("WechatUserInfo")
@Data
public class WeChatMessage {
    /**
     * 主键
     */
    @OpenApiDescription("主键")
    @ColumnSetting(isPrimaryKey = true,
                   length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Edit",
                    "Base"})
    private String id;

    /**
     * 微信公众号标识
     */
    @OpenApiDescription("微信公众号标识")
    @ColumnSetting(length = 36,
                   isNullable = false)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String appId;

    /**
     * 公众平台微信号
     */
    @OpenApiDescription("公众平台微信号")
    @ColumnSetting(length = 50,
                   isNullable = false)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String toUserName;

    /**
     * 发送方账号
     * <p>一个OpenID</p>
     */
    @OpenApiDescription("发送方账号")
    @ColumnSetting(length = 50,
                   isNullable = false)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String fromUserName;

    /**
     * 消息类型
     *
     * @see me.chanjar.weixin.common.api.WxConsts.XmlMsgType
     */
    @OpenApiDescription("消息类型")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String msgType;

    //region 文本消息专属字段

    /**
     * 文本消息内容
     */
    @OpenApiDescription("文本消息内容")
    @ColumnSetting(length = -3)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String content;

    //endregion

    //region 图片/语音/视频/小视频消息专属字段

    /**
     * 图片/语音/视频/小视频消息媒体标识
     * <p>可以调用获取临时素材接口拉取数据</p>
     */
    @OpenApiDescription("图片/语音/视频/小视频消息媒体标识")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String mediaId;

    /**
     * 语音格式
     */
    @OpenApiDescription("语音格式")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String format;

    /**
     * 语音识别结果
     * <p>UTF8编码</p>
     */
    @OpenApiDescription("语音识别结果")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String recognition;

    //region 图片消息专属字段

    /**
     * 图片链接
     * <p>由系统生成</p>
     */
    @OpenApiDescription("图片链接")
    @ColumnSetting(length = 1024)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String picUrl;

    //endregion

    //region 视频/小视频消息专属字段

    /**
     * 视频消息缩略图的媒体标识
     * <p>可以调用获取临时素材接口拉取数据</p>
     */
    @OpenApiDescription("视频消息缩略图的媒体标识")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String thumbMediaId;

    //endregion

    //endregion

    //region 地理位置消息专属字段

    /**
     * 地理位置纬度
     */
    @OpenApiDescription("地理位置纬度")
    @ColumnSetting(precision = 10,
                   scale = 6)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private BigDecimal locationX;

    /**
     * 地理位置经度
     */
    @OpenApiDescription("地理位置经度")
    @ColumnSetting(precision = 10,
                   scale = 6)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private BigDecimal locationY;

    /**
     * 地图缩放大小
     */
    @OpenApiDescription("地图缩放大小")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private Integer scale;

    /**
     * 地理位置信息
     */
    @OpenApiDescription("地理位置信息")
    @ColumnSetting(length = 1024)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String label;

    //endregion

    //region 链接消息专属字段

    /**
     * 消息标题
     */
    @OpenApiDescription("消息标题")
    @ColumnSetting(length = 1024)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String title;

    /**
     * 消息描述
     */
    @OpenApiDescription("消息描述")
    @ColumnSetting(length = 1024)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String description;

    /**
     * 消息链接
     */
    @OpenApiDescription("消息链接")
    @ColumnSetting(length = 1024)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String url;

    //endregion

    /**
     * 消息标识
     */
    @OpenApiDescription("消息标识")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long msgId;

    /**
     * 消息的数据标识
     * <p>消息如果来自文章时才有</p>
     */
    @OpenApiDescription("消息的数据标识")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String msgDataId;

    /**
     * 多图文时第几篇文章
     * <p>从1开始，消息如果来自文章时才有</p>
     */
    @OpenApiDescription("多图文时第几篇文章")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private Integer idx;

    /**
     * 跟进状态
     */
    @OpenApiDescription("跟进状态")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit",
                    "Base"})
    private String followUpStatus;

    /**
     * 创建时间
     */
    @OpenApiDescription("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @OpenApiSubTag({"Detail"})
    private Date createTime;

    /**
     * 更新时间
     */
    @OpenApiDescription("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @OpenApiSubTag({"Detail",
                    "_Edit"})
    private Date updateTime;
}
