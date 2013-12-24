package pro.example;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Author: Anatoly Rybalchenko
 * Date: 12/4/13
 * Time: 7:08 PM
 */

@Test
public class IntegrationTest {
    private HttpClient client = new HttpClient();
    private Logger log = LogManager.getLogger(this.getClass());

    @BeforeTest
    public void initClient(){
        Credentials creds = new UsernamePasswordCredentials("tolyan", "12345");
        AuthScope scope = new AuthScope("localhost", 8080, "TestService");
        client.getState().setCredentials(scope, creds);
    }


    @Test
    public void testClient() throws  IOException{
        GetMethod get = new GetMethod("http://localhost:8080/api/testservice/person/1");
        get.setDoAuthentication(true);
        int status = 0;
        try {
            log.info("Credentials: " + client.getState().getCredentials(new AuthScope("localhost", 8080, "TestService")));
            status = client.executeMethod(get);
        } finally {
            get.releaseConnection();
        }
        assert status == 200;
    }

}
