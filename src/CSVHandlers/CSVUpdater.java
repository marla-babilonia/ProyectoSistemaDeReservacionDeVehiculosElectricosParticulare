package CSVHandlers;

import information.Users;
import information.Vehicles;
import information.Reservations;
import information.AvailableStations.LOCATION;
import HelpfulClasses.ScheduleHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class CSVUpdater {

    private static final String USERS_CSV        = "src/users.csv";
    private static final String VEHICLES_CSV     = "src/vehicles.csv";
    private static final String RESERVATIONS_CSV = "src/reservations.csv";

    /*updates user file :)
     */
    public static void saveUsers(List<Users> users) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(USERS_CSV))) {
            for (Users u : users) {
                String type = u.getTipo().name()
                             .toLowerCase()
                             .replace("ownerandclient", "owner and client");
                w.write(String.join(",",
                    u.getstudentname(),
                    Integer.toString(u.getstudentid()),
                    u.getstudentemail(),
                    u.getstudentphone(),
                    type,
                    String.format("%.1f", u.getCredits()),
                    Integer.toString(u.getVehiclesOwned())
                ));
                w.newLine();
            }
        }
    }

    /* Updates vehicles csv
     */
    public static void saveVehicles(Set<Vehicles> vehicles) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(VEHICLES_CSV))) {
            for (Vehicles v : vehicles) {
                String sched = ScheduleHelper.compressSchedule(v.getSchedule());
                w.write(String.join(",",
                    Integer.toString(v.getOwner().getstudentid()),
                    Integer.toString(v.getID()),
                    v.getVehicleType().name(),
                    v.getdescription().replace(",", " "),    // avoid commas inside descriptions
                    v.getLocation().name(),
                    Boolean.toString(v.getavailable()),
                    sched
                ));
                w.newLine();
            }
        }
    }

    /** updates reservations */
    public static void saveReservations(List<Reservations> reservations) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(RESERVATIONS_CSV))) {
            for (Reservations r : reservations) {
                LOCATION station = r.getStation();
                w.write(String.join(",",
                    Integer.toString(r.getStudent().getstudentid()),
                    Integer.toString(r.getVehicle().getID()),
                    station.name(),
                    Integer.toString(r.getMonth()),
                    Integer.toString(r.getDate()),
                    Integer.toString(r.getStartTime()),
                    Integer.toString(r.getEndTime()),
                    String.format("%.1f", r.getCreditCost())
                ));
                w.newLine();
            }
        }
    }
}
