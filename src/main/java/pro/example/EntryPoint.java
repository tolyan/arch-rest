package pro.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Author: Anatoly Rybalchenko
 * Date: 11/28/13
 * Time: 5:09 PM
 */
public class EntryPoint {
    public static void main(String args[]) throws Exception {
        Server server = new Server(8080);
        WebAppContext context = new WebAppContext();

        context.setDescriptor(EntryPoint.class.getClassLoader().getResource("web.xml").toString());
        context.setContextPath("/");
        context.setWar("src/main");

        server.setHandler(context);
        server.start();
        server.join();

    }

}
