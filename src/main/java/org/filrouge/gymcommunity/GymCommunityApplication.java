package org.filrouge.gymcommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class GymCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymCommunityApplication.class, args);
    }

}
