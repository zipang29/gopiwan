package src.server.fulrest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.glassfish.jersey.servlet.ServletContainer;
 
public class HttpServer {
 
     public HttpServer( int port ) throws Exception {
         ServletHolder sh = new ServletHolder(ServletContainer.class);    
         sh.setInitParameter("org.glassfish.jersey.config.property.resourceConfigClass", "org.glassfish.jersey.api.core.PackagesResourceConfig");
         // TODO nom du package ou se trouve les services à deployer
         sh.setInitParameter("org.glassfish.jersey.config.property.packages", "src.server.fulrest.service");
         sh.setInitParameter("org.glassfish.jersey.api.json.POJOMappingFeature", "true");
       
         Server server = new Server(port);
         ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
         context.addServlet(sh, "/*");
         server.start();
         server.join();      
      }
}
