package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("temperatures")
public class TemperatureResources {

    private List<Temperature> temperatures = new ArrayList<>();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperature> addTemperature(Temperature temperature) {
        temperatures.add(temperature);
        return temperatures;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperature> list(){
        return temperatures;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/one")
    public Temperature measurement(){
        return new Temperature("Málaga",15,28);
    }
}
