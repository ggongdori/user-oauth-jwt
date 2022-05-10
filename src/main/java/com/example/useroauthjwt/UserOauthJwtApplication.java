package com.example.useroauthjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        CorsProperties.class
})
public class UserOauthJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserOauthJwtApplication.class, args);
    }

}
