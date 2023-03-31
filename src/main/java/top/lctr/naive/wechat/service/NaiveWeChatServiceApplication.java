package top.lctr.naive.wechat.service;

import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 微信服务
 *
 * @author LCTR
 * @date 2023-03-27
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},
                       scanBasePackages = {
                               "project.extension",
                               "top.lctr"
                       })
public class NaiveWeChatServiceApplication {
    public static void main(String[] args) {
        System.out.printf("\033[32mNaiveFileSystemApplication.main\033[0m \033[33margs\033[0m : %s\r\n%n",
                          String.join(" ",
                                      args));

        System.out.printf("\033[34mslf4j\033[0m ：%s%n",
                          LoggerFactory.class.getResource(""));
        System.out.printf("\033[34mlogback\033[0m ：%s%n",
                          ConsoleAppender.class.getResource(""));

        ConfigurableApplicationContext application = SpringApplication.run(NaiveWeChatServiceApplication.class,
                                                                           args);
    }
}