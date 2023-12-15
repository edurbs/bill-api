package com.edurbs.bill.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("bill")
@Component
@Getter
@Setter
public class BillApiProperty {

    private String allowedOrigin = "http://localhost:8081";

    private final Security security = new Security();
    
    @Getter
    @Setter
    public static class Security{        
        private boolean enableHttps;
    }

}
