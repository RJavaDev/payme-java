package uz.dual.paymejava.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@Configuration
public class PayMeConfig {

    @Value("${paycom.user.name}")
    private String username;

    @Value("${paycom.user.password}")
    private String password;

    public boolean isUnauthorized(String authHeader) {
        if (Objects.isNull(authHeader)) return false;

        String tokenBase64 = authHeader.substring(6);
        String token = new String(Base64.getDecoder().decode(tokenBase64), StandardCharsets.UTF_8);
        String[] auth = token.split(":");

        return username.equals(auth[0]) && password.equals(auth[1]);
    }
}
