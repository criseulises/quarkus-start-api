package quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import quarkus.services.impl.TemperatureService;

import java.util.List;

@Path("temperatures")
public class TemperatureResources {

    @Inject
    private TemperatureService tempService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Temperature addTemperature(Temperature temperature) {
        tempService.addTemperature(temperature);
        return temperature;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperature> list(){
        return tempService.getTemperatures();
    }

    @GET
    @Path("/max")
    public Response getMaxTemperature() {
        if(tempService.isEmpty()) {
            return Response.status(404).entity("There are no temperatures").build();
        }

        return Response.ok(tempService.getMaxTemperature()).build();
    }
}
