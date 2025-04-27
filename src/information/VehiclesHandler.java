package information;

import CSVHandlers.CSVLoader;
import HelpfulClasses.EnumsHandler;
import HelpfulClasses.ScheduleHelper;
import HelpfulClasses.TimeSlot;
import information.AvailableStations.LOCATION;
import information.Vehicles.VEHICLE_TYPE;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

public class VehiclesHandler {

    static Set<Vehicles> completeVehicles = CSVLoader.loadVehicles();

    public static Set<Vehicles> getVehicles() {
        return completeVehicles;
    }

    //Method to print out all the vehicles available using the overriden 
    //to string function :)

    public static void showAllVehicles() {
        for (Vehicles v : completeVehicles) {
            if (!v.getSchedule().isEmpty()){
                System.out.println(v);
            }
        }
    }

    // Buscar vehículo por ID. Se usa stream para poder manipular mejor el set
    public static Vehicles getVehicleById(int id) {

        return completeVehicles.stream()
            .filter(v -> v.getID() == id) //Keep the vehicles with matching ids 
            .findFirst() //retirn the first one 
            .orElse(null);
    }

    //Metodo que prints todos los vehiculos de un mismo owner
    public static Set<Vehicles> findByOwner(Users owner) {

        Set<Vehicles> results = new HashSet<>();
        for (Vehicles v : completeVehicles) {
            if (v.getOwner().equals(owner)) {
                results.add(v);
            }
        }
        return results;
    }

    //Metodo que prints todos los vehiculos en el mismo location
    public static Set<Vehicles> findByStation(LOCATION location) {
        Set<Vehicles> results = new HashSet<>();
        for (Vehicles v : completeVehicles) {
            if (v.getLocation() == location) {
                results.add(v);
            }
        }
        return results;
    }

  // Editar vehículo 
  public void editVehicleDesc(int id, Set<Vehicles> list) {
    Vehicles v = getVehicleById(id);
    Scanner scan = new Scanner(System.in);
    System.out.println("New Descripcion");
    String descripción = scan.nextLine();
    v.setdescription(descripción); 
    System.out.println("New ID");
    int newid = scan.nextInt();
    v.setID(newid);
  }

  //anadir vehiculo
  public static void addVehicle() {

     Scanner scanner = new Scanner(System.in);

        //Enter Owner
        System.out.print("Enter Owner ID: ");
        int ownerId = Integer.parseInt(scanner.nextLine().trim());
        Users owner = UsersHandler.getUserById(ownerId);
        if (owner == null) {
            System.out.println("Owner not found");
            return;
        }
        if (owner.getVehiclesOwned() >= 2) {
            System.out.println("Owner already owns 2 vehicles!");
            return;
        }

        // Create the id, adds 1 to the last vehicle id in the set
        int newId = 1;
        for (Vehicles v : completeVehicles) {
            if (v.getID() >= newId) {
                newId = v.getID() + 1;
            }
        }
        

        // Enter Vehicle Type
        VEHICLE_TYPE type = EnumsHandler.askVehicleTypeOption();
        if (type == null) {
            System.out.println("No vehicle type selected");
            return;
        }

        // Enter Description
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        // Enter Schedule
        System.out.print("Will you add availability? (y/n): ");

        Map<Integer, Map<Integer, Set<TimeSlot>>> schedule = ScheduleHelper.createEmptySchedule();

        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            boolean adding = true;

            while (adding) {
                int month = ScheduleHelper.askMonthAndValidate(scanner);
                int day = ScheduleHelper.askDateAndValidate(scanner);
                int startTime = ScheduleHelper.askStartTimeAndValidate(scanner);
                int endTime = ScheduleHelper.askEndTimeAndValidate(scanner, startTime);

                ScheduleHelper.addAvailability(schedule, month, day, startTime, endTime);

                System.out.print("Add another time slot? (y/n): ");
                adding = scanner.nextLine().trim().equalsIgnoreCase("y");
            }
        }

        // Enter Location
        LOCATION location = EnumsHandler.askLocationOption();
        if (location == null) {
            System.out.println("No location found");
            return;
        }

        AvailableStations station = AvailableStationsHandler.getStationByLocation(location);
        if (station == null) {
        System.out.println("Station not found.");
            return;
        }

        // Enter Availability
        boolean available = !schedule.isEmpty();

        // System.out.print("Enter availability (true or false): ");
        // boolean available = Boolean.parseBoolean(scanner.nextLine().trim());

        // Create the vehicle
        Vehicles newVehicle = new Vehicles(owner, newId, type, description, schedule, location, available);
        completeVehicles.add(newVehicle);
        //increase the vehicle owned by one
        owner.setVehiclesOwned(owner.getVehiclesOwned() + 1);
        station.increaseCurrentCapacity();
        System.out.println("Vehicle added");
  }

  //remove vehicle by either the id of one vehicle , or remove all owners vehicles

  public static void removeVehicle() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Remove by (1) ID or (2) Owner? ");
    int option = Integer.parseInt(scanner.nextLine().trim());

    if (option == 1) {
        System.out.print("Enter Vehicle ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Vehicles vehicle = getVehicleById(id);

        if (vehicle != null) {
            AvailableStations station = AvailableStationsHandler.getStationByLocation(vehicle.getLocation());
                if (station != null) {
                    station.decreaseCurrentCapacity();  // 🚀
                }
            completeVehicles.remove(vehicle);
            vehicle.getOwner().setVehiclesOwned(vehicle.getOwner().getVehiclesOwned() - 1);
            System.out.println("SUCCESFULLY TERMINATED");
        } else {
            System.out.println("Vehicle not found");
        }

    } else {
        System.out.print("Enter Owner Student ID: ");

        int ownerId = Integer.parseInt(scanner.nextLine().trim());
        Users owner = UsersHandler.getUserById(ownerId);
        if (owner != null) {
            Set<Vehicles> ownerVehicles = findByOwner(owner);
            if (ownerVehicles.isEmpty()) {
                System.out.println("No vehicles found");
            } else {
                for (Vehicles v : ownerVehicles) {
                    AvailableStations station = AvailableStationsHandler.getStationByLocation(v.getLocation());
                    if (station != null) {
                        station.decreaseCurrentCapacity();
                    }
                    owner.setVehiclesOwned(owner.getVehiclesOwned() - 1);
                }
            

                completeVehicles.removeAll(ownerVehicles);
                System.out.println(ownerVehicles.size() + "TWAS REMOVED");
            }

        } else {
            System.out.println("Owner not found");
        }
    }
  }

  public static void modifyVehicle() {

    Scanner scanner = new Scanner(System.in);
    System.out.print("Let's Modify - Enter the Id: ");
    int vehicleId = Integer.parseInt(scanner.nextLine().trim());

    Vehicles v = getVehicleById(vehicleId);
    if (v == null) {
        System.out.println("No such thing exist");
        return;
    }

    boolean editing = true;

    while (editing) {
        System.out.println("\nWhat do yo want to modify?:");
        System.out.println("1. Description");
        System.out.println("2. Location");
        System.out.println("3. Vehicle Type");
        System.out.println("4. Quit");
        System.out.print("What will it be?  ");

        int option = Integer.parseInt(scanner.nextLine().trim());

        switch (option) {
            case 1:
                System.out.print("New Description: ");
                v.setdescription(scanner.nextLine().trim());
                System.out.println("Updated");
                break;
            case 2:
                AvailableStations.LOCATION newLocation = HelpfulClasses.EnumsHandler.askLocationOption();
                if (newLocation != null) {
                    v.setLocation(newLocation.name());
                    System.out.println("Updated");
                }
                break;
            case 3:
                Vehicles.VEHICLE_TYPE newType = HelpfulClasses.EnumsHandler.askVehicleTypeOption();
                if (newType != null) {
                    v.setVehicleType(newType);
                    System.out.println("Updated");
                }
                break;
            case 4:
                editing = false;
                System.out.println("Going back");
                break;
            default:
                System.out.println("Not an option. Try again");
        }
    }
}


  




}
