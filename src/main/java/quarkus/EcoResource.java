package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/greet")
public class EcoResource {

    @GET
    public String greet() {
        return "Hello Worlds!";
    }
}
