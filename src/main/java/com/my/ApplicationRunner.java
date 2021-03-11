package com.my;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;

/**
 * @author chenming
 * @date 2021/3/11 13:28
 */
@Slf4j
@EnableAspectJAutoProxy
@EnableSwagger2
@SpringBootApplication
public class ApplicationRunner {
    public static void main(String[] args)throws Exception{
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
        Environment env = context.getEnvironment();

        StringBuffer sb = new StringBuffer("\r\n");
        sb.append("-------------------------------------------------------------------");
        sb.append("\r\n");
        sb.append("resource-downloader program started ! ");
        sb.append("\r\n");
        sb.append("local : http://" + InetAddress.getLocalHost().getHostAddress() + ":" + env.getProperty("server.port") + "/");
        sb.append("\r\n");
        sb.append("doc : http://" + InetAddress.getLocalHost().getHostAddress() + ":" + env.getProperty("server.port") + "/doc.html");
        sb.append("\r\n");
        sb.append("-------------------------------------------------------------------");
        sb.append("\r\n");

        log.info(sb.toString());

    }
}
