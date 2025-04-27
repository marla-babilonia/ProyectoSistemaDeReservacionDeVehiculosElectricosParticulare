import CSVHandlers.CSVLoader;
import information.AvailableStations.LOCATION;
import information.Vehicles;
import java.util.HashSet;
import java.util.Set;

public class Stations {
    
    //CREATES THE SETS
    Set<Vehicles> compleatvehicles = (Set<Vehicles>) CSVLoader.loadVehicles();
    Set<Integer> allVehicles = new HashSet<>();
    Set<Vehicles> stefaniVehicles = new HashSet<>();
    Set<Vehicles> centroEstudiantesVehicles = new HashSet<>();
    Set<Vehicles> biologiaVehicles = new HashSet<>();
    Set<Vehicles> quimicaVehicles = new HashSet<>();
    Set<Vehicles> adminEmpresasVehicles = new HashSet<>();
    
    public Stations() {

        populateVehicles();
    }
    
    private void populateVehicles(){
        for (Vehicles v : compleatvehicles) {
            if (v.getLocation() == LOCATION.EDIFICIO_STEFANI) {
                stefaniVehicles.add(v);
            }
        }
        for (Vehicles v : compleatvehicles) {
            if (v.getLocation() == LOCATION.CENTRO_DE_ESTUDIANTES) {
                centroEstudiantesVehicles.add(v);
            }
        }
        for (Vehicles v : compleatvehicles) {
            if (v.getLocation() == LOCATION.EDIFICIO_DE_BIOLOGIA) {
                biologiaVehicles.add(v);
            }
        }
        for (Vehicles v : compleatvehicles) {
            if (v.getLocation() == LOCATION.EDIFICIO_INGENIERIA_QUIMICA) {
                quimicaVehicles.add(v);
            }
        }
        for (Vehicles v : compleatvehicles) {
            if (v.getLocation() == LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS) {
                adminEmpresasVehicles.add(v);
            }
        }
        for (Vehicles v : compleatvehicles){
            allVehicles.add(v.getID());
        }
    }


    public boolean isIdUsed(int id) {
        return allVehicles.contains(id);//checks if the ID is in any of the vehicles no matter if they are in diferent stations.
    }
}
