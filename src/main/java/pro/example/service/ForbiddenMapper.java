package pro.example.service;

import pro.example.util.ForbiddenResourceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Author: Anatoly Rybalchenko
 * Date: 12/3/13
 * Time: 5:03 PM
 */

@Provider
public class ForbiddenMapper implements ExceptionMapper<ForbiddenResourceException> {
    @Override
    public Response toResponse(ForbiddenResourceException e) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
