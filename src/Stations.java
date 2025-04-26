import information.AvailableStations.LOCATION;
import information.Vehicles;
import information.Vehicles.VEHICLE_TYPE;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Stations {
    

    private Map<LOCATION, Set<Vehicles>> vehiclesPerStation = new HashMap<>();
    Set<Integer> allVehicles = new HashSet<>();
    Set<Vehicles> stefaniVehicles = new HashSet<>();
    Set<Vehicles> centroEstudiantesVehicles = new HashSet<>();
    Set<Vehicles> biologiaVehicles = new HashSet<>();
    Set<Vehicles> quimicaVehicles = new HashSet<>();
    Set<Vehicles> adminEmpresasVehicles = new HashSet<>();
    public Stations() {
        // Initialize empty sets for each location
       // for (LOCATION loc : LOCATION.values()) {
        //    vehiclesPerStation.put(loc, new HashSet<>());
        //}

        populateVehicles();
    }

    private void populateVehicles() {
        int id = 1;

        // EDIFICIO_STEFANI
        
        stefaniVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Mountain Bike", 0, LOCATION.EDIFICIO_STEFANI, true));
        allVehicles.add(id - 1);
        stefaniVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Electric Scooter", 0, LOCATION.EDIFICIO_STEFANI, true));
        allVehicles.add(id - 1);
        stefaniVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKATEBOARD, "Skateboard", 0, LOCATION.EDIFICIO_STEFANI, false));
        allVehicles.add(id - 1);
        stefaniVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Road Bike", 0, LOCATION.EDIFICIO_STEFANI, true));
        allVehicles.add(id - 1);
        stefaniVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Kick Scooter", 0, LOCATION.EDIFICIO_STEFANI, true));
        
        // CENTRO_DE_ESTUDIANTES
        
        centroEstudiantesVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Hybrid Bike", 0, LOCATION.CENTRO_DE_ESTUDIANTES, true));
        allVehicles.add(id - 1);
        centroEstudiantesVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Electric Moped", 0, LOCATION.CENTRO_DE_ESTUDIANTES, true));
        allVehicles.add(id - 1);
        centroEstudiantesVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKATEBOARD, "Mini Cruiser", 0, LOCATION.CENTRO_DE_ESTUDIANTES, true));
        allVehicles.add(id - 1);
        centroEstudiantesVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Folding Bike", 0, LOCATION.CENTRO_DE_ESTUDIANTES, false));
        allVehicles.add(id - 1);
        centroEstudiantesVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Three-Wheel Scooter", 0, LOCATION.CENTRO_DE_ESTUDIANTES, true));
        allVehicles.add(id - 1);
        // EDIFICIO_DE_BIOLOGIA
        
        biologiaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Electric Bike", 0, LOCATION.EDIFICIO_DE_BIOLOGIA, true));
        allVehicles.add(id - 1);
        biologiaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Stand-up Scooter", 0, LOCATION.EDIFICIO_DE_BIOLOGIA, true));
        allVehicles.add(id - 1);
        biologiaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKATEBOARD, "Electric Skateboard", 0, LOCATION.EDIFICIO_DE_BIOLOGIA, true));
        allVehicles.add(id - 1);
        biologiaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "BMX Bike", 0, LOCATION.EDIFICIO_DE_BIOLOGIA, true));
        allVehicles.add(id - 1);
        biologiaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Foldable Scooter", 0, LOCATION.EDIFICIO_DE_BIOLOGIA, false));
        allVehicles.add(id - 1);
        // EDIFICIO_INGENIERIA_QUIMICA
        
        quimicaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Commuter Bike", 0, LOCATION.EDIFICIO_INGENIERIA_QUIMICA, true));
        allVehicles.add(id - 1);
        quimicaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Hoverboard", 0, LOCATION.EDIFICIO_INGENIERIA_QUIMICA, true));
        allVehicles.add(id - 1);
        quimicaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKATEBOARD, "Old School Board", 0, LOCATION.EDIFICIO_INGENIERIA_QUIMICA, true));
        allVehicles.add(id - 1);
        quimicaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Cargo Bike", 0, LOCATION.EDIFICIO_INGENIERIA_QUIMICA, false));
        allVehicles.add(id - 1);
        quimicaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Speed Scooter", 0, LOCATION.EDIFICIO_INGENIERIA_QUIMICA, true));
        allVehicles.add(id - 1);
        // EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS
        
        adminEmpresasVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Touring Bike", 0, LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS, true));
        allVehicles.add(id - 1);
        adminEmpresasVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "City Scooter", 0, LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS, true));
        allVehicles.add(id - 1);
        adminEmpresasVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKATEBOARD, "Freestyle Board", 0, LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS, false));
        allVehicles.add(id - 1);
        adminEmpresasVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Single Speed Bike", 0, LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS, true));
        allVehicles.add(id - 1);
        adminEmpresasVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Kids Scooter", 0, LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS, true));
        allVehicles.add(id - 1);
    }
    
    

    public Set<Vehicles> getVehiclesAtStation(LOCATION location) {
        return vehiclesPerStation.get(location);
    }

    public Map<LOCATION, Set<Vehicles>> getAllStationsWithVehicles() {
        return vehiclesPerStation;
    }
    public boolean isIdUsed(int id) {
        return allVehicles.contains(id);
    }
}
