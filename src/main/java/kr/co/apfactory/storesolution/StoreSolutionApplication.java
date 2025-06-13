package kr.co.apfactory.storesolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.internal.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@SpringBootApplication
public class StoreSolutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreSolutionApplication.class, args);
    }

    @Bean
    public Function<String, String> currentUrlWithoutParam() {
        return param -> {
            try {
                return URLDecoder.decode(ServletUriComponentsBuilder.fromCurrentRequest().replaceQueryParam(param).toUriString(),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        };
    }
}
