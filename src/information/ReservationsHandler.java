package information;

import information.AvailableStations.LOCATION;
import HelpfulClasses.EnumsHandler;
import HelpfulClasses.ScheduleHelper;
import HelpfulClasses.TimeSlot;
import information.WaitlistHandler;

import CSVHandlers.CSVLoader;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class ReservationsHandler {

    private static final Scanner scanner = new Scanner(System.in);
    
    private static final List<Users> users = UsersHandler.getUsers();
    private static final Set<Vehicles> vehicles = VehiclesHandler.getVehicles();
    private static final List<Reservations> reservations = CSVLoader.getReservations();

    public static void addReservation() {

        //Start by getting the users ID
        System.out.print("To add a reservation please enter the user's ID:");
        int userIDString = Integer.parseInt(scanner.nextLine());
        Users  user = UsersHandler.getUserById(userIDString);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        boolean flag = false;
        int month = -1;
        int day = -1;
        int start = -1;
        int end = -1;
        int duration = 0;

        //Get the reservation and keep asking to input details until they are correct
        while (!flag){
            //Get the month, day, start time and end time of the reservation
            month = ScheduleHelper.askMonthAndValidate(scanner);
            day = ScheduleHelper.askDateAndValidate(scanner);
            start = ScheduleHelper.askStartTimeAndValidate(scanner);
            end = ScheduleHelper.askEndTimeAndValidate(scanner, start);

            //Caculate the duration of the reservation to make sure it's less than 6 hours
            duration = ScheduleHelper.calculateDurationInHours(start, end);
            if (duration <= 0 || duration > 6) {
                System.out.println("Duration must be between 1 and 6 hours");
            } else {
                flag = true;
            }         
        }

        List<Vehicles> available = checkAvailability(month, day, start, end);
        if (available.isEmpty()) {
            System.out.println("No vehicles available for that time slot.");
            System.out.print("Join the waitlist? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            List<AvailableStations> stations = CSVLoader.loadStations();
            System.out.println("Which station do you want to wait for?");
            for (int i = 0; i < stations.size(); i++) {
                System.out.printf("%d) %s%n",
                    i + 1,
                    EnumsHandler.formatStationName(stations.get(i).getName())
                );
            }
            int choice = Integer.parseInt(scanner.nextLine().trim());
            AvailableStations.LOCATION station = stations.get(choice - 1).getName();
            int entryNum = WaitlistHandler.nextNum();
            Waitlist entry = new Waitlist(entryNum, user,null, station, month, day, start, end, 0);
            WaitlistHandler.addWaitlistEntry(entry);
            System.out.println("Added to waitlist as entry #" + entryNum);
        }
    return;
}

        Set<Vehicles.VEHICLE_TYPE> types = new HashSet<>();
        for (Vehicles v : available) types.add(v.getVehicleType());

        System.out.println("Available vehicle types:");
        int idx = 1;
        List<Vehicles.VEHICLE_TYPE> typeList = new ArrayList<>(types);
        for (Vehicles.VEHICLE_TYPE t : typeList) {
            System.out.printf("%d - %s%n", idx++, t.name());
        }
        
        System.out.print("Choose a type option: ");
        int typeOpt = Integer.parseInt(scanner.nextLine());
        if (typeOpt < 1 || typeOpt > typeList.size()) {
            System.out.println("Invalid type selection.");
            return;
        }
        Vehicles.VEHICLE_TYPE chosenType = typeList.get(typeOpt - 1);

        List<Vehicles> filtered = new ArrayList<>();
        for (Vehicles v : available) {
            if (v.getVehicleType() == chosenType) filtered.add(v);
        }

        System.out.println("Available vehicles of type " + chosenType + ":");
        for (Vehicles v : filtered) System.out.println(v);

        System.out.print("Enter desired Vehicle ID: ");
        int vehicleId = Integer.parseInt(scanner.nextLine());
        Vehicles vehicle = null;
        for (Vehicles v : filtered) if (v.getID() == vehicleId) vehicle = v;
        if (vehicle == null) {
            System.out.println("Invalid vehicle ID.");
            return;
        }

        double cost = calculateCost(duration, vehicle.getVehicleType());
        System.out.printf("Total credit cost: %d%n", cost);

        Users owner = vehicle.getOwner();
        owner.setCredits(owner.getCredits() + cost);
        user.setCredits(user.getCredits() - cost);

        Reservations reservation = new Reservations(user, vehicle, vehicle.getLocation(), month, day, start, end, cost);
        reservations.add(reservation);
        System.out.println("Reservation confirmed:\n" + reservation);

    }

    public static void removeReservation() {
        System.out.print("Enter client (student) ID to remove reservation: ");
        int clientId = Integer.parseInt(scanner.nextLine());
        Users client = UsersHandler.getUserById(clientId);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        List<Reservations> clientReservations = getClientReservations(clientId);
        if (clientReservations.isEmpty()) {
            System.out.println("No reservations found for this client.");
            return;
        }

        displayReservations(clientReservations);

        System.out.print("Select reservation number to cancel: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > clientReservations.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Reservations selected = clientReservations.get(choice - 1);
        Users owner = selected.getVehicle().getOwner();

        owner.setCredits(owner.getCredits() - selected.getCreditCost());
        client.setCredits(client.getCredits() + selected.getCreditCost());

        reservations.remove(selected);
        System.out.println("Reservation cancelled successfully.");
    }


    public static void modifyReservation() {
        System.out.print("Enter client (student) ID to modify reservation: ");
        int clientId = Integer.parseInt(scanner.nextLine());
        Users client = UsersHandler.getUserById(clientId);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        List<Reservations> clientReservations = getClientReservations(clientId);
        if (clientReservations.isEmpty()) {
            System.out.println("No reservations found for this client.");
            return;
        }

        displayReservations(clientReservations);

        System.out.print("Select reservation number to modify: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > clientReservations.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Reservations selected = clientReservations.get(choice - 1);

        boolean modifying = true;
        while (modifying) {
            System.out.println("\nWhat do you want to modify?");
            System.out.println("1. Date");
            System.out.println("2. Time Slot");
            System.out.println("3. Both Date and Time Slot");
            System.out.println("4. Vehicle");
            System.out.println("5. Client (User)");
            System.out.println("6. Finish Modifications");
            System.out.print("Enter option (1-6): ");
            int modifyOption = Integer.parseInt(scanner.nextLine());

            int newMonth = selected.getMonth();
            int newDay = selected.getDate();
            int newStart = selected.getStartTime();
            int newEnd = selected.getEndTime();
            Vehicles newVehicle = selected.getVehicle();
            Users newClient = selected.getStudent();

            if (modifyOption == 1 || modifyOption == 3) {
                newMonth = ScheduleHelper.askMonthAndValidate(scanner);
                newDay = ScheduleHelper.askDateAndValidate(scanner);
            }
            if (modifyOption == 2 || modifyOption == 3) {
                newStart = ScheduleHelper.askStartTimeAndValidate(scanner);
                newEnd = ScheduleHelper.askEndTimeAndValidate(scanner, newStart);
            }
            if (modifyOption == 4) {
                List<Vehicles> availableVehicles = checkAvailability(newMonth, newDay, newStart, newEnd);
                if (availableVehicles.isEmpty()) {
                    System.out.println("No vehicles available for the selected time slot.");
                    return;
                }
                System.out.println("Available Vehicles:");
                for (Vehicles v : availableVehicles) System.out.println(v);
                System.out.print("Enter new Vehicle ID: ");
                int newVehicleId = Integer.parseInt(scanner.nextLine());
                Vehicles found = null;
                for (Vehicles v : availableVehicles) if (v.getID() == newVehicleId) found = v;
                if (found == null) {
                    System.out.println("Invalid Vehicle ID.");
                    return;
                }
                newVehicle = found;
            }
            if (modifyOption == 5) {
                System.out.print("Enter new client (student) ID: ");
                int newClientId = Integer.parseInt(scanner.nextLine());
                Users foundUser = UsersHandler.getUserById(newClientId);
                if (foundUser == null) {
                    System.out.println("Client not found.");
                    return;
                }
                newClient = foundUser;
            }
            if (modifyOption == 6) {
                modifying = false;
                continue;
            }

            int duration = ScheduleHelper.calculateDurationInHours(newStart, newEnd);
            if (duration <= 0 || duration > 6) {
                System.out.println("Invalid duration: must be between 1 and 6 hours.");
                return;
            }

            List<Vehicles> available = checkAvailability(newMonth, newDay, newStart, newEnd);
            if (!available.contains(newVehicle)) {
                System.out.println("Selected vehicle is not available for the new time slot.");
                return;
            }
            Users oldOwner = selected.getVehicle().getOwner();
            Users newOwner = newVehicle.getOwner();

            double oldCost = selected.getCreditCost();
            double newCost = calculateCost(duration, newVehicle.getVehicleType());

            oldOwner.setCredits(oldOwner.getCredits() - oldCost);
            newClient.setCredits(newClient.getCredits() + oldCost);

            Users owner = newVehicle.getOwner();
            newOwner.setCredits(newOwner.getCredits() + newCost);
            newClient.setCredits(newClient.getCredits() - newCost);

            selected.setMonth(newMonth);
            selected.setDate(newDay);
            selected.setStartTime(newStart);
            selected.setEndTime(newEnd);
            selected.setVehicle(newVehicle);
            selected.setStudent(newClient);
            selected.setCreditCost(newCost);

            System.out.println("Modification applied successfully.\n");
        }
        
    }

    public static void showAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.println("All Reservations:");
        for (Reservations res : reservations) {
            System.out.println(res);
        }
    }

    private static List<Vehicles> checkAvailability(int month, int day, int start, int end) {
        List<Vehicles> available = new ArrayList<>();
        for (Vehicles v : vehicles) {
            Map<Integer, Map<Integer, Set<TimeSlot>>> sched = v.getSchedule();
            if (!sched.containsKey(month) || !sched.get(month).containsKey(day)) continue;

            boolean covers = false;
            for (TimeSlot slot : sched.get(month).get(day)) {
                if (slot.getStartTime() <= start && slot.getEndTime() >= end) {
                    covers = true;
                    break;
                }
            }
            if (!covers) continue;

            boolean conflict = false;
            for (Reservations r : reservations) {
                if (r.getVehicle().getID() != v.getID()) continue;
                if (r.getMonth() != month || r.getDate() != day) continue;
                if (ScheduleHelper.timesOverlap(start, end, r.getStartTime(), r.getEndTime())) {
                    conflict = true;
                    break;
                }
            }
            if (!conflict) available.add(v);
        }
        return available;
    }

    private static double calculateCost(int durationHours, Vehicles.VEHICLE_TYPE type) {
        double cost = 0;
    
        switch (type) {
            case BICYCLE:
                cost = 3 + (durationHours - 1) * 2;
                break;
            case SKOOTER:
                cost = 2 + (durationHours - 1) * 1;
                break;
            case SKATEBOARD:
                cost = 1 + (durationHours - 1) * 0.5;
                break;
            default:
                cost = durationHours;
        }
    
        return cost;
    }
    

    private static List<Reservations> getClientReservations(int clientId) {
        List<Reservations> result = new ArrayList<>();
        for (Reservations r : reservations) {
            if (r.getStudent().getstudentid() == clientId) {
                result.add(r);
            }
        }
        return result;
    }

    private static void displayReservations(List<Reservations> reservationList) {
        for (int i = 0; i < reservationList.size(); i++) {
            System.out.println((i + 1) + ". " + reservationList.get(i));
        }
    }

    public static List<Reservations> getReservations() {
        return reservations;
    }

}

