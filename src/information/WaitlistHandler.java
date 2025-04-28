package information;

import CSVHandlers.CSVLoader;
import HelpfulClasses.EnumsHandler;
import java.util.Queue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class WaitlistHandler {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Queue<Waitlist> waitlist = new LinkedList<>(CSVLoader.loadWaitlists());

    public static void addWaitlistEntry(Waitlist entry) {
        waitlist.add(entry);
    }

    public static int nextNum() {
    int max = 0;
    for (Waitlist w : waitlist) {
        if (w.getWaitlistNum() > max) {
            max = w.getWaitlistNum();
        }
    }
    return max + 1;
}

    /* Prints all waitlist entries. */
    public static void showWaitlist() {
        if (waitlist.isEmpty()) {
            System.out.println("No waitlist entries found.");
            return;
        }
        System.out.println("\n=== WAITLIST ENTRIES ===");
        for (Waitlist w : waitlist) {
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
        Iterator<Waitlist> it = waitlist.iterator();
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

    public static Queue<Waitlist> getWaitlist() {
        return waitlist;
      }
}