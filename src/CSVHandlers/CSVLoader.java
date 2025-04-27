package CSVHandlers;

import information.*;
import information.AvailableStations.LOCATION;
import information.Users.OWNER_OR_CLIENT;
import information.Vehicles.VEHICLE_TYPE;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;

import HelpfulClasses.EnumsHandler;
import HelpfulClasses.ScheduleHelper;
import HelpfulClasses.TimeSlot;

public class CSVLoader {

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
                line = line.trim();
                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1); // Remove BOM character!
                }
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String studentName = parts[0];
                    int studentID = Integer.parseInt(parts[1]);
                    String studentEmail = parts[2];
                    String studentPhone = parts[3];
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

    

    // public static Set<Vehicles> loadVehicles() {
    //     Set<Vehicles> vehicles = new HashSet<>();

    //     try {
    //         InputStream inputStream = CSVLoader.class.getResourceAsStream("/vehicles.csv");
    //         if (inputStream == null) {
    //             throw new RuntimeException("File not found: /vehicles.csv");
    //         }

    //         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    //         String line;

    //         while ((line = reader.readLine()) != null) {
    //             if(line.trim().isEmpty()){
    //                 continue;
    //             }
    //             String[] parts = line.split(",");
    //             if (parts.length >= 6) {
    //                 int ownerId = Integer.parseInt(parts[0]);
    //                 int vehicleID = Integer.parseInt(parts[1]);
    //                 VEHICLE_TYPE vehicleType = VEHICLE_TYPE.valueOf(parts[2].toUpperCase());
    //                 String description = parts[3];
    //                 LOCATION location = EnumsHandler.getLocation(parts[4]);
    //                 boolean available = Boolean.parseBoolean(parts[5]);

    //                 // Find owner
    //                 Users owner = findUserById(ownerId);
    //                 if (owner == null) {
    //                     System.out.println("ID " + ownerId + " not found");
    //                     continue;
    //                 }

    //                 // Create empty schedule (basic for now — schedule expansion possible)
    //                 Map<Integer, Map<Integer, Set<TimeSlot>>> schedule = ScheduleHelper.createEmptySchedule();

    //                 Vehicles vehicle = new Vehicles(owner, vehicleID, vehicleType, description, schedule, location, available);
    //                 vehicles.add(vehicle);
    //             }
    //         }

    //         reader.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return vehicles;
    // }

    public static Set<Vehicles> loadVehicles() {
        Set<Vehicles> vehicles = new HashSet<>();
    
        try {
            InputStream inputStream = CSVLoader.class.getResourceAsStream("/vehicles.csv");
            if (inputStream == null) {
                throw new RuntimeException("File not found: /vehicles.csv");
            }
    
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
    
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                //Skip empty lines
                if (line.isEmpty()) continue;
    
                String[] parts = line.split(",");
                if (parts.length < 6) {
                    continue;
                }
    
                String ownerIdStr   = parts[0].trim();
                String vehicleIdStr = parts[1].trim();
    
                //owner id and vehicle id are numbers
                if (!ownerIdStr.matches("\\d+") || !vehicleIdStr.matches("\\d+")) {
                    continue;
                }
    
                //parse
                int ownerId   = Integer.parseInt(ownerIdStr);
                int vehicleID = Integer.parseInt(vehicleIdStr);
    
                //vehicle type
                VEHICLE_TYPE vehicleType;
                try {
                    vehicleType = VEHICLE_TYPE.valueOf(parts[2].trim().toUpperCase());
                } catch (IllegalArgumentException iae) {
                    continue;
                }
    
                String description = parts[3].trim();
                LOCATION location  = EnumsHandler.getLocation(parts[4].trim());
                boolean available  = Boolean.parseBoolean(parts[5].trim());
    

    
                String scheduleString = (parts.length >= 7) ? parts[6].trim() : "";
                Map<Integer, Map<Integer, Set<TimeSlot>>> schedule = ScheduleHelper.expandSchedule(scheduleString);
                
    
                // Find the owner by id and create the object
                Users owner = findUserById(ownerId);
                if (owner == null) {
                    continue;
                }
                Vehicles v = new Vehicles(owner, vehicleID, vehicleType, description, schedule, location, available);
                vehicles.add(v);
            }
    
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return vehicles;
    }

    private static final List<Users> users = loadUsers();
    private static final Set<Vehicles> vehicles = new HashSet<>(loadVehicles());
    private static final List<Reservations> reservations = loadReservations();


    public static List<Reservations> loadReservations() {
        List<Reservations> reservations = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(CSVLoader.class.getResourceAsStream("/reservations.csv")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    int studentId = Integer.parseInt(parts[0]);
                    int vehicleId = Integer.parseInt(parts[1]);
                    LOCATION station = EnumsHandler.getLocation(parts[2]);
                    int month = Integer.parseInt(parts[3]);
                    int date  = Integer.parseInt(parts[4]);
                    int start = Integer.parseInt(parts[5]);
                    int end   = Integer.parseInt(parts[6]);
                    int cost  = Integer.parseInt(parts[7]);

                    Users student = findUserById(studentId);
                    Vehicles vehicle = getVehicleById(vehicleId);
                    if (student != null && vehicle != null) {
                        reservations.add(new Reservations(student, vehicle, station, month, date, start, end, cost));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public static List<Reservations> getReservations() {
        return reservations;
    }

    private static Users findUserById(int id) {
        return users.stream()
        .filter(u -> u.getstudentid() == id)
        .findFirst()
        .orElse(null);
    }

    private static Vehicles getVehicleById(int id) {
        return vehicles.stream()
        .filter(v -> v.getID() == id)
        .findFirst()
        .orElse(null);
    }
}
