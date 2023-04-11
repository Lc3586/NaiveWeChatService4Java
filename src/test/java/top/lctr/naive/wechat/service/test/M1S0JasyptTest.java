package top.lctr.naive.wechat.service.test;

import org.junit.jupiter.api.*;
import project.extension.cryptography.JasyptUtils;

/**
 * Jasypt加密解密测试
 *
 * @author LCTR
 * @date 2022-12-14
 */
@Disabled
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class M1S0JasyptTest {
    /**
     * 加密
     */
    @Test
    @DisplayName("加密")
    @Order(200)
    public void encrypt() {
        String password = JasyptUtils.getPassword();
        String text = "123456";
        System.out.printf("明文：%s%n",
                          text);
        String data1 = JasyptUtils.encrypt(text,
                                           password);
        System.out.printf("密文1：%s%n",
                          data1);
        String data2 = String.format("ENC(%s)",
                                     data1);
        System.out.printf("密文2：%s%n",
                          data2);
    }

    /**
     * 解密
     */
    @Test
    @DisplayName("解密")
    @Order(210)
    public void decrypt()
            throws
            Throwable {
        String password = JasyptUtils.getPassword();
        String text = "ENC(iXb0v7Wnr1ZgfGZ1eAyCOg==)";
        System.out.printf("密文：%s%n",
                          text);
        String data1 = JasyptUtils.decrypt(text,
                                           password);
        System.out.printf("明文：%s%n",
                          data1);
    }
}
