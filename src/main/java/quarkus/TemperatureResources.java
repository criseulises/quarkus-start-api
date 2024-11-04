package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.List;

@Path("temperatures")
public class TemperatureResources {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperature> list(){
        return Arrays.asList(
                new Temperature("Málaga",15,34),
                new Temperature("RD",24,32),
                new Temperature("Madrid", 32,43),
                new Temperature("Mexico", 34,23)
        );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/one")
    public Temperature measurement(){
        return new Temperature("Málaga",15,28);
    }
}
