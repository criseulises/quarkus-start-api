package quarkus.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import quarkus.Temperature;
import quarkus.services.ITemperatureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TemperatureService implements ITemperatureService {
    private final List<Temperature> temperatures = new ArrayList<>();

    @Override
    public void addTemperature(Temperature temperature) {
        temperatures.add(temperature);
    }

    @Override
    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    @Override
    public boolean isEmpty() {
        return temperatures.isEmpty();
    }

    @Override
    public Integer getMaxTemperature() {
        return temperatures.stream().mapToInt(Temperature::getMax).max().getAsInt();
    }

    @Override
    public Optional<Temperature> searchTemperature(String city) {
        return temperatures.stream().filter(temperature -> temperature.getCity().equals(city)).findFirst();
    }

}
