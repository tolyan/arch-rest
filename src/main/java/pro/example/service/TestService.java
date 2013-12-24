package pro.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pro.example.dao.MyBatisSqlSessionFactory;
import pro.example.entity.TestPojo;
import pro.example.mappers.TestMapper;
import pro.example.util.ForbiddenResourceException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * Author: Anatoly Rybalchenko
 * Date: 11/28/13
 * Time: 5:36 PM
 */

@Path("/testservice")
public class TestService {
    public SqlSessionFactory factory = MyBatisSqlSessionFactory.getSqlSessionFactory();

    @Context
    SecurityContext sc;

    @Produces("application/json")
    @GET
    @Path("/test/{id}")
    public TestPojo getPerson(@PathParam("id") int id) throws ForbiddenResourceException {
        System.out.println("Got call with id: " + id);
        SqlSession session = factory.openSession();
        TestMapper mapper = session.getMapper(TestMapper.class);
        TestPojo testPojo = mapper.getPerson(id);

        if(!sc.getUserPrincipal().getName().equals("testUser")) {
            throw new ForbiddenResourceException();
        }
        return testPojo;
    }
}
