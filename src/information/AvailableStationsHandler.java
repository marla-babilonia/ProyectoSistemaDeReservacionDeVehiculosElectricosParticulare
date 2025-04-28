package information;

import CSVHandlers.CSVLoader;
import HelpfulClasses.EnumsHandler;
import information.AvailableStations.LOCATION;

import java.util.List;
import java.util.Set;

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

    //Method to print the vehicles by station
    public static void showStationsWithVehicles() {

        List<AvailableStations> stations = getStations();

        Set<Vehicles> allVehicles = VehiclesHandler.getVehicles();
        for (AvailableStations station : stations) {

            String name = EnumsHandler.formatStationName(station.getName());
            System.out.printf("Station: %s (capacity %d/%d)%n",
                name, station.getCurrentCapacity(), station.getMaxCapacity());
            boolean any = false;

            for (Vehicles vehicle : allVehicles) {
                if (vehicle.getLocation() == station.getName()) {
                    System.out.printf("  – ID %d: %s%n", vehicle.getID(), vehicle.getdescription());
                    any = true;
                }
            }
            
            if (!any) System.out.println("  (no vehicles here)");
        }
    }

}