package com.edurbs.bill.api.config.property;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class BillApiProperty {

    private final Security security = new Security();
    
    @Getter
    public static class Security{
        
        private boolean enableHttps;

    }

}
