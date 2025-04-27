import CSVHandlers.CSVLoader;
import information.AvailableStations.LOCATION;
import information.Vehicles;
import java.util.HashSet;
import java.util.Set;

public class Stations {
    
    //CREATES THE SETS
    Set<Vehicles> compleatvehicles = (Set<Vehicles>) CSVLoader.loadVehicles();
    Set<Integer> allVehicles = new HashSet<>();
    
    public Stations() {

        populateVehicles();
    }
    
    private void populateVehicles(){
        Set<Vehicles> stefaniVehicles = new HashSet<>();
        for (Vehicles v : compleatvehicles) {
            if (v.getLocation() == LOCATION.EDIFICIO_STEFANI) {
                stefaniVehicles.add(v);
            }
        }
        Set<Vehicles> centroEstudiantesVehicles = new HashSet<>();
        for (Vehicles v : compleatvehicles) {
            if (v.getLocation() == LOCATION.CENTRO_DE_ESTUDIANTES) {
                stefaniVehicles.add(v);
            }
        }
        Set<Vehicles> biologiaVehicles = new HashSet<>();
        for (Vehicles v : compleatvehicles) {
            if (v.getLocation() == LOCATION.EDIFICIO_DE_BIOLOGIA) {
                stefaniVehicles.add(v);
            }
        }
        Set<Vehicles> quimicaVehicles = new HashSet<>();
        for (Vehicles v : compleatvehicles) {
            if (v.getLocation() == LOCATION.EDIFICIO_INGENIERIA_QUIMICA) {
                stefaniVehicles.add(v);
            }
        }
        Set<Vehicles> adminEmpresasVehicles = new HashSet<>();
        for (Vehicles v : compleatvehicles) {
            if (v.getLocation() == LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS) {
                stefaniVehicles.add(v);
            }
        }
    }


    public boolean isIdUsed(int id) {
        return allVehicles.contains(id);//checks if the ID is in any of the vehicles no matter if they are in diferent stations.
    }
}
