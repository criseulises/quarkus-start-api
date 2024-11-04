package quarkus.services;

import quarkus.Temperature;

import java.util.List;

public interface ITemperatureService {
    public void addTemperature(Temperature temperature);

    public List<Temperature> getTemperatures();

    boolean isEmpty();

    public Integer getMaxTemperature();

}
