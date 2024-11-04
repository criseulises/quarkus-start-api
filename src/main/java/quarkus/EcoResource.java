package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

import java.util.Optional;

@Path("/greet")
public class EcoResource {

    @GET
    public String greetings(@QueryParam("message") String message){
        return Optional.ofNullable(message).map(m -> "> " + m).orElse("There is not messages");
    }

    @GET
    @Path("/{name}")
    public String greet(@PathParam("name") String name){
        return "Hi, " + name;
    }

    @GET
    @Path("/{name}/uppercase")
    public String greetUpperCase(@PathParam("name") String name){
        return "HI, " + name.toUpperCase();
    }



}
