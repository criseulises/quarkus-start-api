package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/greet")
public class EcoResource {

    @GET
    @Path("greet")
    public String greet(){
        return "Hello!!";
    }

    @GET
    @Path("/day")
    public String day() {
        return "Good Morning!";
    }

    @GET
    @Path("/afternoon")
    public String afternoon() {
        return "Good Afternoon!";
    }

    @GET
    @Path("/night")
    public String night() {
        return "Good Night!";
    }
}
