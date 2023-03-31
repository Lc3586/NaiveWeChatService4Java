package top.lctr.naive.wechat.service.dto;

import project.extension.openapi.annotations.OpenApiDescription;

import java.util.Arrays;
import java.util.Optional;

/**
 * 微信网页授权类型
 *
 * @author LCTR
 * @date 2023-03-29
 */
public enum WeChatOAuth2Type {
    /**
     * 基础授权
     */
    @OpenApiDescription("基础授权")
    BASE(1,
         "BASE"),
    /**
     * 身份信息授权
     */
    @OpenApiDescription("身份信息授权")
    USER_INFO(2,
              "USER_INFO");

    /**
     * @param index 索引
     * @param value 值
     */
    WeChatOAuth2Type(int index,
                     String value) {
        this.index = index;
        this.value = value;
    }

    /**
     * 索引
     */
    final int index;

    /**
     * 值
     */
    final String value;

    /**
     * 获取索引
     *
     * @return 索引
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * 获取字符串
     *
     * @return 值
     */
    @Override
    public String toString() {
        return this.value;
    }

    /**
     * 获取枚举
     *
     * @param value 值
     * @return 枚举
     */
    public static WeChatOAuth2Type toEnum(String value)
            throws
            IllegalArgumentException {
        Optional<WeChatOAuth2Type> find = Arrays.stream(WeChatOAuth2Type.values())
                                                .filter(x -> x.value.equals(value))
                                                .findFirst();
        if (!find.isPresent())
            throw new IllegalArgumentException(String.format("未找到符合%s此值的WeChatOAuth2Type枚举中",
                                                             value));
        return find.get();
    }

    /**
     * 获取枚举
     *
     * @param index 索引
     * @return 枚举
     */
    public static WeChatOAuth2Type toEnum(int index)
            throws
            IllegalArgumentException {
        for (WeChatOAuth2Type value : WeChatOAuth2Type.values()) {
            if (value.getIndex() == index)
                return value;
        }

        throw new IllegalArgumentException(String.format("指定索引%s无效",
                                                         index));
    }
}
