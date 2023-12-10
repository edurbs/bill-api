package com.edurbs.bill.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class ResourceCreatedEvent extends ApplicationEvent {

    private HttpServletResponse response;
    private Long id;

    public ResourceCreatedEvent(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }

    

}
