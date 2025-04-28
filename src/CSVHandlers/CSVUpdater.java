package CSVHandlers;

import information.*;
import HelpfulClasses.ScheduleHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class CSVUpdater {

    public static void saveUsers(List<Users> users) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("resources/users.csv"))) {
            for (Users u : users) {
                w.write(
                    u.getstudentname() + "," +
                    u.getstudentid()   + "," +
                    u.getstudentemail()+ "," +
                    u.getstudentphone()+ "," +
                    u.getTipo()        + "," +
                    u.getCredits()     + "," +
                    u.getVehiclesOwned()
                );
                w.newLine();
            }
        }
        System.out.println("Users saved");
    }

    public static void saveVehicles(Set<Vehicles> vehicles) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("resources/vehicles.csv"))) {
            for (Vehicles v : vehicles) {
                String locationName = (v.getLocation() != null) ? v.getLocation().name() : "UNKNOWN";

                w.write(
                    v.getOwner().getstudentid()   + "," +
                    v.getID()     + "," +
                    v.getVehicleType().name() + "," +
                    v.getdescription()    + "," +
                    locationName   + "," +
                    v.getavailable()    + "," +
                    ScheduleHelper.compressSchedule(v.getSchedule())
                );
                w.newLine();
            }
        }
        System.out.println("Vehicles saved");
    }

    public static void saveReservations(List<Reservations> reservations) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("resources/reservations.csv"))) {
            for (Reservations r : reservations) {
                w.write(
                    r.getStudent().getstudentid() + "," +
                    r.getVehicle().getID()       + "," +
                    r.getStation().name()        + "," +
                    r.getMonth()                 + "," +
                    r.getDate()                  + "," +
                    r.getStartTime()             + "," +
                    r.getEndTime()               + "," +
                    r.getCreditCost()
                );
                w.newLine();
            }
        }
        System.out.println("Reservations saved");
    }

    /** Optional helper to save all at once: */
    public static void saveAllCSVs(
            List<Users> users,
            Set<Vehicles> vehicles,
            List<Reservations> reservations
    ) {
        try {
            saveUsers(users);
            saveVehicles(vehicles);
            saveReservations(reservations);
            System.out.println("All CSVs saved successfully!");
        } catch (IOException e) {
            System.err.println("Failed to save CSVs: " + e.getMessage());
        }
    }
}
