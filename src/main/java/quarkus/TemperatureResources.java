package quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import quarkus.services.impl.TemperatureService;

import java.util.List;
import java.util.NoSuchElementException;

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
    public List<Temperature> list() {
        return tempService.getTemperatures();
    }

    @GET
    @Path("/max")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMaxTemperature() {
        if (tempService.isEmpty()) {
            return Response.status(404).entity("There are no temperatures").build();
        }
        return Response.ok(tempService.getMaxTemperature()).build();
    }

    @GET
    @Path("/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperature searchTemperature(@PathParam("city") String city) {
        return tempService.searchTemperature(city)
                .orElseThrow(() -> new NoSuchElementException("There is no temperature with city " + city));
    }
}
