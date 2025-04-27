package information;

import CSVHandlers.CSVLoader;
import HelpfulClasses.EnumsHandler;
import information.AvailableStations.LOCATION;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.Scanner;

public class WaitlistHandler {
    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Users> users      = UsersHandler.getUsers();
    private static final Set<Vehicles> vehicles = VehiclesHandler.getVehicles();
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
            System.out.printf(
                "#%d  User:%s  Vehicle:%d  Station:%s  Start:%s  End:%s  Credits:%d%n",
                w.getWaitlistNum(),
                w.getUser().getstudentname(),
                w.getVehicle().getID(),
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
        Iterator<Waitlist> iterator = waitlists.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getWaitlistNum() == num) {
                iterator.remove();
                System.out.println("Removed waitlist entry #" + num);
                return;
            }
        }
        System.out.println("No waitlist entry with number " + num);
    }
}