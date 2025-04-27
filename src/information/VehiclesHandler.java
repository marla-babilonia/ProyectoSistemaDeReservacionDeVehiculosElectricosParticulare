package information;
import CSVHandlers.CSVLoader;
import information.AvailableStations.LOCATION;
import information.Vehicles.VEHICLE_TYPE;
import java.util.Scanner;
import java.util.Set;

public class VehiclesHandler {

    static Set<Vehicles> compleatvehicles = CSVLoader.loadVehicles();
  // Buscar vehículo por ID
  public Vehicles findById(int id, Set<Vehicles> list) {
      for (Vehicles v : list) {
          if (v.getID() == id) {
              return v;
          }
      }
      return null;
  }

  // Editar vehículo 
  public void editVehicleDesc(int id, Set<Vehicles> list) {
    Vehicles v = findById(id, list);
    Scanner scan = new Scanner(System.in);
    System.out.println("New Descripcion");
    String descripción = scan.nextLine();
    v.setdescription(descripción); 
    System.out.println("New ID");
    int newid = scan.nextInt();
    v.setID(newid);
  }

  // Remover vehículo manualmente
  public void removeVehicle(int id, Set<Vehicles> list) {
      Vehicles exists = findById(id, list);
      if (exists != null) {
          list.remove(exists);
      }
  }
  //anadir vehiculo
  public void addVehicle(Set<Vehicles> list) {
     Scanner scanner = new Scanner(System.in);

        // Enter Vehicle ID
        System.out.print("Enter vehicle ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        // Enter Vehicle Type
        System.out.print("Enter vehicle type (SKOOTER, BICYCLE, SKATEBOARD): ");
        String vehicleTypeStr = scanner.nextLine().trim().toUpperCase();
        VEHICLE_TYPE vehicleType = VEHICLE_TYPE.valueOf(vehicleTypeStr);

        // Enter Description
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        // Enter Schedule
        System.out.print("Enter schedule (0 for no schedule): ");
        int schedule = Integer.parseInt(scanner.nextLine().trim());

        // Enter Location
        System.out.print("Enter location (EDIFICIO_STEFANI, CENTRO_DE_ESTUDIANTES, EDIFICIO_DE_BIOLOGIA, EDIFICIO_INGENIERIA_QUIMICA, EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS): ");
        String locationStr = scanner.nextLine().trim().toUpperCase();
        LOCATION location = EnumsHandler.getLocation(locationStr);

        // Enter Availability
        System.out.print("Enter availability (true or false): ");
        boolean available = Boolean.parseBoolean(scanner.nextLine().trim());

        // Create the vehicle
        Vehicles newVehicle = new Vehicles(id, vehicleType, description, schedule, location, available);
        compleatvehicles.add(newVehicle);
  }



  
  //metodo para crear set de available vehicles
// editar el set available method para que si es falso quitarlo de la lista y si es cierto anadirlo a la lista

//para marla - buscar manera de editar, remover, y almacenar los vehiculos respondientes  
}
