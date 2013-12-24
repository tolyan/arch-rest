package pro.example;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import pro.example.service.ForbiddenMapper;
import pro.example.service.TestService;
import pro.example.service.security.SecurityFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Anatoly Rybalchenko
 * Date: 11/29/13
 * Time: 3:30 PM
 */

@ApplicationPath("/api")
public class JaxRsApiApplication extends Application {

    public JaxRsApiApplication( ){

    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> classes = new HashSet<Object>();
        classes.add(new ForbiddenMapper());
        classes.add(new SecurityFilter());
        return classes;
    }

    @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(TestService.class);
        classes.add(JacksonJsonProvider.class);
        return classes;
    }

}
