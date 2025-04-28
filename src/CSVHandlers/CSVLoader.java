package CSVHandlers;

import information.*;
import information.AvailableStations.LOCATION;
import information.Users.OWNER_OR_CLIENT;
import information.Vehicles.VEHICLE_TYPE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map;

import HelpfulClasses.EnumsHandler;
import HelpfulClasses.ScheduleHelper;
import HelpfulClasses.TimeSlot;

public class CSVLoader {

    public static List<Users> loadUsers() {
        List<Users> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("\uFEFF")) line = line.substring(1);
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String name    = parts[0];
                    int    id      = Integer.parseInt(parts[1]);
                    String email   = parts[2];
                    String phone   = parts[3];
                    String typeStr = parts[4];
                    OWNER_OR_CLIENT type = OWNER_OR_CLIENT.valueOf(typeStr.replace(" ", "").toUpperCase());
                    double credits = Double.parseDouble(parts[5]);
                    int vehiclesOwned = Integer.parseInt(parts[6]);

                    users.add(new Users(name, id, email, phone, type, credits, vehiclesOwned));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<AvailableStations> loadStations() {
        List<AvailableStations> stations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/stations.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    LOCATION loc = EnumsHandler.getLocation(parts[0]);
                    int max  = Integer.parseInt(parts[1]);
                    int curr = Integer.parseInt(parts[2]);
                    stations.add(new AvailableStations(loc, max, curr));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stations;
    }

    public static Set<Vehicles> loadVehicles() {
        Set<Vehicles> vehicles = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/vehicles.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 6) continue;

                int ownerId  = Integer.parseInt(parts[0].trim());
                int vid      = Integer.parseInt(parts[1].trim());
                VEHICLE_TYPE type = VEHICLE_TYPE.valueOf(parts[2].trim().toUpperCase());
                String desc  = parts[3].trim();
                LOCATION loc = EnumsHandler.getLocation(parts[4].trim());
                boolean avail= Boolean.parseBoolean(parts[5].trim());
                String sched = parts.length >= 7 ? parts[6].trim() : "";
                Map<Integer, Map<Integer, Set<TimeSlot>>> schedule = 
                    sched.isEmpty()
                      ? ScheduleHelper.createEmptySchedule()
                      : ScheduleHelper.expandSchedule(sched);

                Users owner = findUserById(ownerId);
                if (owner != null) {
                    vehicles.add(new Vehicles(owner, vid, type, desc, schedule, loc, avail));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public static List<Reservations> loadReservations() {
        List<Reservations> reservations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/reservations.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 8) {
                    int studentId = Integer.parseInt(p[0]);
                    int vehicleId = Integer.parseInt(p[1]);
                    LOCATION station = EnumsHandler.getLocation(p[2]);
                    int mon   = Integer.parseInt(p[3]);
                    int day   = Integer.parseInt(p[4]);
                    int start = Integer.parseInt(p[5]);
                    int end   = Integer.parseInt(p[6]);
                    double cost = Double.parseDouble(p[7]);

                    Users u = findUserById(studentId);
                    Vehicles v = getVehicleById(vehicleId);
                    if (u != null && v != null) {
                        reservations.add(new Reservations(u, v, station, mon, day, start, end, cost));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public static List<Waitlist> loadWaitlists() {
        List<Waitlist> waitlist = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/waitlist.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 9) {
                    int num = Integer.parseInt(p[0]);
                    int sid = Integer.parseInt(p[1]);
                    int vid = Integer.parseInt(p[2]);
                    LOCATION station = EnumsHandler.getLocation(p[3]);
                    int mon = Integer.parseInt(p[4]);
                    int day = Integer.parseInt(p[5]);
                    int st  = Integer.parseInt(p[6]);
                    int en  = Integer.parseInt(p[7]);
                    double cost = Double.parseDouble(p[8]);

                    Users u = findUserById(sid);
                    Vehicles v = getVehicleById(vid);
                    if (u != null && v != null) {
                        waitlist.add(new Waitlist(num, u, v, station, mon, day, st, en, cost));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return waitlist;
    }

    public static LinkedList<Transaction> loadTransactions() {

        LinkedList<Transaction> allTransactions = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",");
                
                if (p.length == 9) {
                    int transactionId   = Integer.parseInt(p[0]);
                    int clientId    = Integer.parseInt(p[1]);
                    int ownerId    = Integer.parseInt(p[2]);
                    int vehicleId    = Integer.parseInt(p[3]);
                    double credits = Double.parseDouble(p[4]);
                    int month    = Integer.parseInt(p[5]);
                    int day    = Integer.parseInt(p[6]);
                    int startTime     = Integer.parseInt(p[7]);
                    int endTime     = Integer.parseInt(p[8]);

                    Users client = findUserById(clientId);
                    Users owner  = findUserById(ownerId);
                    Vehicles vehicles   = getVehicleById(vehicleId);

                    if (client != null && owner != null && vehicles != null) {
                        Transaction transaction = new Transaction(client, owner, vehicles, credits, month, day, startTime, endTime);
                        
                        transaction.setTransactionId(transactionId);
                        allTransactions.add(transaction);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTransactions;
    }

    

    private static final List<Users> users = loadUsers();
    private static final Set<Vehicles> vehicles = loadVehicles();
    private static final List<Reservations> reservations = loadReservations();
    private static final LinkedList<Transaction> transactions = loadTransactions();

    private static Users findUserById(int id) {
        return users.stream()
                .filter(u -> u.getstudentid() == id)
                .findFirst().orElse(null);
    }

    private static Vehicles getVehicleById(int id) {
        return vehicles.stream()
                .filter(v -> v.getID() == id)
                .findFirst().orElse(null);
    }
    public static List<Reservations> getReservations() {
        return reservations;
    }

    public static LinkedList<Transaction> getTransactions() {
        return loadTransactions();
    }


}
