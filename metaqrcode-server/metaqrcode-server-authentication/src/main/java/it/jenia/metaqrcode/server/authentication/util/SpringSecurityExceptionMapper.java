package it.jenia.metaqrcode.server.authentication.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.security.access.AccessDeniedException;

public class SpringSecurityExceptionMapper implements ExceptionMapper<AccessDeniedException> {

    public Response toResponse(AccessDeniedException exception) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}