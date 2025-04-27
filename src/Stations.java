// import CSVHandlers.CSVLoader;
// import information.Vehicles;
// import java.util.HashSet;
// import java.util.Set;

// public class Stations {
    
//     //CREATES THE SETS
//     static Set<Vehicles> compleatvehicles = CSVLoader.loadVehicles();
    
//     Set<Integer> allVehicles = new HashSet<>();
//     Set<Vehicles> stefaniVehicles = new HashSet<>();
//     Set<Vehicles> centroEstudiantesVehicles = new HashSet<>();
//     Set<Vehicles> biologiaVehicles = new HashSet<>();
//     Set<Vehicles> quimicaVehicles = new HashSet<>();
//     Set<Vehicles> adminEmpresasVehicles = new HashSet<>();
    
//     public Stations() {

//         populateVehicles();
//     }
    
//     public void populateVehicles(){
//         for (Vehicles v : compleatvehicles) {
//             switch (v.getLocation()) {
//                 case EDIFICIO_STEFANI:
//                     stefaniVehicles.add(v);
//                     break;
//                 case CENTRO_DE_ESTUDIANTES:
//                     centroEstudiantesVehicles.add(v);
//                     break;
//                 case EDIFICIO_DE_BIOLOGIA:
//                     biologiaVehicles.add(v);
//                     break;
//                 case EDIFICIO_INGENIERIA_QUIMICA:
//                     quimicaVehicles.add(v);
//                     break;
//                 case EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS:
//                     adminEmpresasVehicles.add(v);
//                     break;
//                 default:
//                     break;
//             }
//             allVehicles.add(v.getID());
//         }
//     }


//     public boolean isIdUsed(int id) {
//         return allVehicles.contains(id);//checks if the ID is in any of the vehicles no matter if they are in diferent stations.
//     }
// }
