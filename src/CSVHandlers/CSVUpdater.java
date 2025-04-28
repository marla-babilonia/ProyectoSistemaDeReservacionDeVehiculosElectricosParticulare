package CSVHandlers;

import information.*;
import HelpfulClasses.ScheduleHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
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
            for (Reservations reservation : reservations) {
                w.write(
                    reservation.getStudent().getstudentid() + "," +
                    reservation.getVehicle().getID()       + "," +
                    reservation.getStation().name()        + "," +
                    reservation.getMonth()                 + "," +
                    reservation.getDate()                  + "," +
                    reservation.getStartTime()             + "," +
                    reservation.getEndTime()               + "," +
                    reservation.getCreditCost()
                );
                w.newLine();
            }
        }
        System.out.println("Reservations saved");
    }

    public static void saveTransactions(LinkedList<Transaction> transactions) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("resources/transactions.csv"))) {
            for (Transaction transaction : transactions) {
                // mirror your loader format:
                String timeSlot = String.format("%04d-%04d", transaction.getStartTime(), transaction.getEndTime());
                w.write(String.join(",",
                    Integer.toString(transaction.getTransactionId()),
                    Integer.toString(transaction.getClient().getstudentid()),
                    Integer.toString(transaction.getOwner().getstudentid()),
                    Integer.toString(transaction.getVehicle().getID()),
                    Double.toString(transaction.getCreditAmount()),
                    Integer.toString(transaction.getMonth()),
                    Integer.toString(transaction.getDay()),
                    timeSlot
                ));
                w.newLine();
            }
        }
        System.out.println("Transactions saved");
    }

    public static void saveWaitlist(List<Waitlist> waitlist) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("resources/waitlist.csv"))) {
            for (Waitlist waitlistEntry : waitlist) {
                String locationName = (waitlistEntry.getLocation() != null) ? waitlistEntry.getLocation().name() : "UNKNOWN";
                w.write(String.join(",",
                    String.valueOf(waitlistEntry.getWaitlistNum()),
                    String.valueOf(waitlistEntry.getUser().getstudentid()),
                    String.valueOf(waitlistEntry.getVehicle().getID()),
                    locationName,
                    String.valueOf(waitlistEntry.getMonth()),
                    String.valueOf(waitlistEntry.getDate()),
                    String.valueOf(waitlistEntry.getStart()),
                    String.valueOf(waitlistEntry.getEnd()),
                    String.valueOf(waitlistEntry.getTotalCredits())
                ));
                w.newLine();
            }
        }
        System.out.println("Waitlist saved");
    }

    /** Optional helper to save all at once: */
    public static void saveAllCSVs(
            List<Users> users,
            Set<Vehicles> vehicles,
            List<Reservations> reservations,
            LinkedList<Transaction> transactions,
            List<Waitlist> waitlist
    ) {
        try {
            saveUsers(users);
            saveVehicles(vehicles);
            saveReservations(reservations);
            saveTransactions(transactions);
            saveWaitlist(waitlist);
            System.out.println("All CSVs saved successfully!");
        } catch (IOException e) {
            System.err.println("Failed to save CSVs: " + e.getMessage());
        }
    }
}
