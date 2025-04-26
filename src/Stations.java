import information.AvailableStations.LOCATION;
import information.Vehicles;
import information.Vehicles.VEHICLE_TYPE;
import java.util.HashSet;
import java.util.Set;

public class Stations {
    

    //CREATES THE SETS
    Set<Integer> allVehicles = new HashSet<>();
    Set<Vehicles> stefaniVehicles = new HashSet<>();
    Set<Vehicles> centroEstudiantesVehicles = new HashSet<>();
    Set<Vehicles> biologiaVehicles = new HashSet<>();
    Set<Vehicles> quimicaVehicles = new HashSet<>();
    Set<Vehicles> adminEmpresasVehicles = new HashSet<>();
    public Stations() {

        populateVehicles();
    }

    private void populateVehicles() {
        int id = 1;

        // EDIFICIO_STEFANI
        
        stefaniVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Mountain Bike", 0, LOCATION.EDIFICIO_STEFANI, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        stefaniVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Electric Scooter", 0, LOCATION.EDIFICIO_STEFANI, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        stefaniVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKATEBOARD, "Skateboard", 0, LOCATION.EDIFICIO_STEFANI, false));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        stefaniVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Road Bike", 0, LOCATION.EDIFICIO_STEFANI, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        stefaniVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Kick Scooter", 0, LOCATION.EDIFICIO_STEFANI, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle

        // CENTRO_DE_ESTUDIANTES
        
        centroEstudiantesVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Hybrid Bike", 0, LOCATION.CENTRO_DE_ESTUDIANTES, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        centroEstudiantesVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Electric Moped", 0, LOCATION.CENTRO_DE_ESTUDIANTES, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        centroEstudiantesVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKATEBOARD, "Mini Cruiser", 0, LOCATION.CENTRO_DE_ESTUDIANTES, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        centroEstudiantesVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Folding Bike", 0, LOCATION.CENTRO_DE_ESTUDIANTES, false));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        centroEstudiantesVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Three-Wheel Scooter", 0, LOCATION.CENTRO_DE_ESTUDIANTES, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        // EDIFICIO_DE_BIOLOGIA
        
        biologiaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Electric Bike", 0, LOCATION.EDIFICIO_DE_BIOLOGIA, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        biologiaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Stand-up Scooter", 0, LOCATION.EDIFICIO_DE_BIOLOGIA, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        biologiaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKATEBOARD, "Electric Skateboard", 0, LOCATION.EDIFICIO_DE_BIOLOGIA, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        biologiaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "BMX Bike", 0, LOCATION.EDIFICIO_DE_BIOLOGIA, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        biologiaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Foldable Scooter", 0, LOCATION.EDIFICIO_DE_BIOLOGIA, false));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        // EDIFICIO_INGENIERIA_QUIMICA
        
        quimicaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Commuter Bike", 0, LOCATION.EDIFICIO_INGENIERIA_QUIMICA, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        quimicaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Hoverboard", 0, LOCATION.EDIFICIO_INGENIERIA_QUIMICA, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        quimicaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKATEBOARD, "Old School Board", 0, LOCATION.EDIFICIO_INGENIERIA_QUIMICA, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        quimicaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Cargo Bike", 0, LOCATION.EDIFICIO_INGENIERIA_QUIMICA, false));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        quimicaVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Speed Scooter", 0, LOCATION.EDIFICIO_INGENIERIA_QUIMICA, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        // EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS
        
        adminEmpresasVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Touring Bike", 0, LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        adminEmpresasVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "City Scooter", 0, LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        adminEmpresasVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKATEBOARD, "Freestyle Board", 0, LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS, false));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        adminEmpresasVehicles.add(new Vehicles(id++, VEHICLE_TYPE.BICYCLE, "Single Speed Bike", 0, LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        adminEmpresasVehicles.add(new Vehicles(id++, VEHICLE_TYPE.SKOOTER, "Kids Scooter", 0, LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS, true));
        allVehicles.add(id - 1);// makes sure that the id matches with each vehicle
        //EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS
    }
    
    

    public boolean isIdUsed(int id) {
        return allVehicles.contains(id);//checks if the ID is in any of the vehicles no matter if they are in diferent stations.
    }
}
