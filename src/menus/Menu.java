package menus;

import information.ReservationsHandler;
import information.UsersHandler;
import information.VehiclesHandler;
// import station.StationHandler;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void displayMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Manage Reservations");
            System.out.println("2. Manage Users");
            System.out.println("3. Manage Vehicles");
            System.out.println("4. Manage Waitlist");
            System.out.println("5. Quit");

            System.out.print("Enter option (1-5): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    displayReservationMenu();
                    break;
                case "2":
                    displayUserMenu();
                    break;
                case "3":
                    displayVehicleMenu();
                    break;
                case "4":
                    displayWaitlistMenu();
                    break;
                case "5":
                    quitProgram();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void displayReservationMenu() {
        boolean managingReservations = true;
        while (managingReservations) {
            System.out.println("\nMANAGE RESERVATIONS");
            System.out.println("1. View Reservations");
            System.out.println("2. Add Reservation");
            System.out.println("3. Remove Reservation");
            System.out.println("4. Modify Reservation");
            System.out.println("5. Go Back");

            System.out.print("Enter option (1-5): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    ReservationsHandler.showAllReservations();
                    break;
                case "2":
                    ReservationsHandler.addReservation();
                    break;
                case "3":
                    ReservationsHandler.removeReservation();
                    break;
                case "4":
                    ReservationsHandler.modifyReservation();
                    break;
                case "5":
                    managingReservations = false;
                    break;
                default:
                    System.out.println("Invalid option, try again");
            }
        }
    }

    private static void displayUserMenu() {
        boolean managingUsers = true;
        while (managingUsers) {
            System.out.println("\nMANAGE USERS");
            System.out.println("1. View Users");
            System.out.println("2. Add User");
            System.out.println("3. Remove User");
            System.out.println("4. Modify User");
            System.out.println("5. Go Back");

            System.out.print("Enter option (1-5): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    UsersHandler.getUsers().forEach(System.out::println);
                    break;
                case "2":
                    UsersHandler.addUser();
                    break;
                case "3":
                    System.out.print("Enter User ID to remove: ");
                    int removeId = Integer.parseInt(scanner.nextLine());
                    UsersHandler.removeUser(removeId);
                    break;
                case "4":
                    System.out.print("Enter User ID to modify: ");
                    int modifyId = Integer.parseInt(scanner.nextLine());
                    UsersHandler.modifyUser(modifyId);
                    break;
                case "5":
                    managingUsers = false;
                    break;
                default:
                    System.out.println("Invalid option, try again");
            }
        }
    }

    private static void displayVehicleMenu() {
        boolean managingVehicles = true;
        while (managingVehicles) {
            System.out.println("\nMANAGE VEHICLES");
            System.out.println("1. View Vehicles");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Remove Vehicle");
            System.out.println("4. Modify Vehicle");
            System.out.println("5. Go Back");

            System.out.print("Enter option (1-5): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                VehiclesHandler.getVehicles().forEach(System.out::println);
                    break;
                case "2":
                    VehiclesHandler.addVehicle();
                    break;
                case "3":
                    VehiclesHandler.removeVehicle();
                    break;
                case "4":
                    System.out.println("Modify vehicle no se ha hecho");
                    break;
                case "5":
                    managingVehicles = false;
                    break;
                default:
                    System.out.println("Invalid option, try again");
            }
        }
    }

    private static void displayWaitlistMenu() {
        boolean managingWaitlist = true;
        while (managingWaitlist) {
            System.out.println("\nMANAGE WAITLIST");
            System.out.println("1. View Waitlist");
            System.out.println("2. Remove from Waitlist");
            System.out.println("3. Go Back");

            System.out.print("Enter option (1-3): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    // StationHandler.showWaitlist();
                    break;
                case "2":
                    // StationHandler.removeFromWaitlist();
                    //hay que crear lo de waitlist
                    break;
                case "3":
                    managingWaitlist = false;
                    break;
                default:
                    System.out.println("Invalid option, try again");
            }
        }
    }

    private static void quitProgram() {
        System.out.println("Saving changes...");
        
        //falta salvar a los csv

        System.out.println("You're Welcome :)");
        System.exit(0);
    }
}
