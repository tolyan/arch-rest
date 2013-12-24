package pro.example.service.security;

import org.apache.cxf.common.security.SimplePrincipal;
import org.apache.cxf.common.util.Base64Exception;
import org.apache.cxf.common.util.Base64Utility;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Anatoly Rybalchenko
 * Date: 12/3/13
 * Time: 4:29 PM
 */

@Provider
public class SecurityFilter implements RequestHandler {
    private static final String REALM = "TestService";
    @Context
    private HttpHeaders headers;
    private Map<String, String> users;


    @Override
    public Response handleRequest(Message message, ClassResourceInfo classResourceInfo) {

        users = getUsers();

        SecurityContext sc = message.get(SecurityContext.class);
        if (sc != null) {
            Principal principal = sc.getUserPrincipal();
            if (principal != null && users.containsKey(principal.getName())) {
                return null;
            }
        }

        List<String> authValues = headers.getRequestHeader("Authorization");
        if (authValues.size() != 1) {
            return createFaultResponse();
        }
        String[] values = authValues.get(0).split(" ");
        if (values.length != 2 || !"Basic".equals(values[0])) {
            return createFaultResponse();
        }

        String decodedValue = null;
        try {
            decodedValue = new String(Base64Utility.decode(values[1]));
        } catch (Base64Exception ex) {
            return createFaultResponse();
        }
        final String[] namePassword = decodedValue.split(":");
        if (namePassword.length != 2) {
            return createFaultResponse();
        }
        String password = users.get(namePassword[0]);
        if (password == null || !password.equals(namePassword[1])) {
            return createFaultResponse();
        }

        final SecurityContext newSc = new SecurityContext() {

            @Override
            public Principal getUserPrincipal() {
                return new SimplePrincipal(namePassword[0]);
            }

            @Override
            public boolean isUserInRole(String s) {
                return false;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public String getAuthenticationScheme() {
                return SecurityContext.BASIC_AUTH;
            }
        };

        message.put(SecurityContext.class, newSc);
        return null;
    }

    private Map<String, String> getUsers() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("testUser", "12345");

        return result;
    }


    private Response createFaultResponse() {
        return Response.status(401).header("WWW-Authenticate",
                "Basic realm=\"" + this.REALM + "\"").build();
    }
}
