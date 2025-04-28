package information;

import CSVHandlers.CSVLoader;
import HelpfulClasses.EnumsHandler;
import information.AvailableStations.LOCATION;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class WaitlistHandler {
    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Waitlist> waitlists = CSVLoader.loadWaitlists();

    public static void addWaitlistEntry(Waitlist entry) {
        waitlists.add(entry);
    }

    public static int nextNum() {
    int max = 0;
    for (Waitlist w : waitlists) {
        if (w.getWaitlistNum() > max) {
            max = w.getWaitlistNum();
        }
    }
    return max + 1;
}

    /* Prints all waitlist entries. */
    public static void showWaitlist() {
        if (waitlists.isEmpty()) {
            System.out.println("No waitlist entries found.");
            return;
        }
        System.out.println("\n=== WAITLIST ENTRIES ===");
        for (Waitlist w : waitlists) {
            String vehicleId;
            if (w.getVehicle() != null) {
                vehicleId = Integer.toString(w.getVehicle().getID());
            } else {
                vehicleId = "ANY";
            }
            System.out.printf(
                "#%d  User:%s  Vehicle:%s  Station:%s  Start:%s  End:%s  Credits:%.1f%n",
                w.getWaitlistNum(),
                w.getUser().getstudentname(),
                vehicleId,
                EnumsHandler.formatStationName(w.getLocation()),
                w.getStartTime(),
                w.getEndTime(),
                w.getTotalCredits()
            );
        }
    }

    /* Removes a waitlist entry by its number. */
    public static void removeWaitlistEntry() {
        System.out.print("Enter waitlist entry number to remove: ");
        int num = Integer.parseInt(scanner.nextLine().trim());

        boolean removed = false;
        Iterator<Waitlist> it = waitlists.iterator();
        while (it.hasNext()) {
            if (it.next().getWaitlistNum() == num) {
                it.remove();
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Removed waitlist entry number " + num);
        } else {
            System.out.println("No waitlist entry with number " + num);
        }
    }

    public static List<Waitlist> getWaitlists() {
        return new ArrayList<>(waitlists);
    }
}