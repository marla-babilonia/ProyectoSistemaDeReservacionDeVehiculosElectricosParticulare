package CSVHandlers;
import information.Users.OWNER_OR_CLIENT;
import information.Vehicles.VEHICLE_TYPE;
import information.AvailableStations;
import information.EnumsHandler;
import information.Users;
import information.Vehicles;
import information.AvailableStations.LOCATION;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    public static List<AvailableStations> loadStations() {
        List<AvailableStations> stations = new ArrayList<>();

        try {
            InputStream inputStream = CSVLoader.class.getResourceAsStream("/stations.csv");
            if (inputStream == null) {
                throw new RuntimeException("File not found: /stations.csv");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    LOCATION location = EnumsHandler.getLocation(parts[0]);
                    int maxCapacity = Integer.parseInt(parts[1]);
                    int currentCapacity = Integer.parseInt(parts[2]);

                    stations.add(new AvailableStations(location, maxCapacity, currentCapacity));
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stations;
    }

    public static List<Users> loadUsers() {
        List<Users> users = new ArrayList<>();

        try {
            InputStream inputStream = CSVLoader.class.getResourceAsStream("/users.csv");
            if (inputStream == null) {
                throw new RuntimeException("File not found: /users.csv");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String studentName = parts[0];
                    int studentID = Integer.parseInt(parts[1]);
                    String studentEmail = parts[2];
                    int studentPhone = Integer.parseInt(parts[3]);
                    String ownerOrClientString = parts[4];
                    OWNER_OR_CLIENT ownerOrClient = null;
                    int credits = Integer.parseInt(parts[5]);
                    int vehiclesOwned = Integer.parseInt(parts[6]);

                    if (ownerOrClientString.equalsIgnoreCase("owner")){
                        ownerOrClient = OWNER_OR_CLIENT.OWNER;
                    } else if (ownerOrClientString.equalsIgnoreCase("client")){
                        ownerOrClient = OWNER_OR_CLIENT.CLIENT;
                    } else if (ownerOrClientString.equalsIgnoreCase("owner and client")){
                        ownerOrClient = OWNER_OR_CLIENT.OWNERANDCLIENT;
                    }

                    users.add(new Users(studentName, studentID, studentEmail, studentPhone, ownerOrClient, credits, vehiclesOwned));
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public static List<Vehicles> loadVehicles() {
        List<Vehicles> vehicles = new ArrayList<>();

        try {
            InputStream inputStream = CSVLoader.class.getResourceAsStream("/vehicles.csv");
            if (inputStream == null) {
                throw new RuntimeException("File not found: /users.csv");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int vehicleID = Integer.parseInt(parts[0]);
                    String vehicleTypeString = parts[1];
                    String description = parts[2];
                    int schedule = Integer.parseInt(parts[3]);
                    LOCATION location = EnumsHandler.getLocation(parts[4]);
                    Boolean available = Boolean.parseBoolean(parts[5]);

                    VEHICLE_TYPE vehicleType = null;

                    if (vehicleTypeString.equalsIgnoreCase("skooter")){
                        vehicleType = VEHICLE_TYPE.SKOOTER;
                    } else if (vehicleTypeString.equalsIgnoreCase("skateboard")){
                        vehicleType = VEHICLE_TYPE.SKATEBOARD;
                    } else if (vehicleTypeString.equalsIgnoreCase("bicycle")){
                        vehicleType = VEHICLE_TYPE.BICYCLE;
                    }

                    vehicles.add(new Vehicles(vehicleID, vehicleType, description, schedule, location, available));
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    // private static LOCATION getLocation(String string) {

    //     LOCATION location = null;

    //     if (string.equalsIgnoreCase("edificio stefani")){
    //         location = LOCATION.EDIFICIO_STEFANI;
    //     } else if (string.equalsIgnoreCase("centro de estudiantes")){
    //         location = LOCATION.CENTRO_DE_ESTUDIANTES;
    //     } else if (string.equalsIgnoreCase("edificio de biologia")){
    //         location = LOCATION.EDIFICIO_DE_BIOLOGIA;
    //     } else if (string.equalsIgnoreCase("edificio de biologia")){
    //         location = LOCATION.EDIFICIO_INGENIERIA_QUIMICA;
    //     } else if (string.equalsIgnoreCase("edificio de biologia")){
    //         location = LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS;
    //     }

    //     return location;
    // }
}
