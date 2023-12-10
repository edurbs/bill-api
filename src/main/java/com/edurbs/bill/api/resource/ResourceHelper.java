package com.edurbs.bill.api.resource;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResourceHelper {
    public static URI getUri(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        response.setHeader("Location", uri.toASCIIString());
        return uri;
    }
}
