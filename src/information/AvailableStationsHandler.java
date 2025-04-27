package information;

import CSVHandlers.CSVLoader;
import information.AvailableStations.LOCATION;

import java.util.List;

public class AvailableStationsHandler {

    private static final List<AvailableStations> stations = CSVLoader.loadStations();

    public static List<AvailableStations> getStations() {
        return stations;
    }

    public static AvailableStations getStationByLocation(LOCATION location) {
        for (AvailableStations station : stations) {
            if (station.getName() == location) {
                return station;
            }
        }
        return null;
    }
}