package HelpfulClasses;
import java.util.Scanner;

import information.AvailableStations;
import information.AvailableStations.LOCATION;
import information.Vehicles.VEHICLE_TYPE;

public class EnumsHandler {

    //helper methods for the available stations enums :))
    
    // This method receives a string of the different stations and returns the enum
    public static LOCATION getLocation(String string) {

        LOCATION location = null;

        if (string.equalsIgnoreCase("edificio stefani")){
            location = LOCATION.EDIFICIO_STEFANI;
        } else if (string.equalsIgnoreCase("centro de estudiantes")){
            location = LOCATION.CENTRO_DE_ESTUDIANTES;
        } else if (string.equalsIgnoreCase("edificio de biologia")){
            location = LOCATION.EDIFICIO_DE_BIOLOGIA;
        } else if (string.equalsIgnoreCase("edificio ingenieria quimica")){
            location = LOCATION.EDIFICIO_INGENIERIA_QUIMICA;
        } else if (string.equalsIgnoreCase("edificio administracion de empresas")){
            location = LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS;
        }

        return location;
    }

    //This method receives the enum of different stations and returns them as a string
    public static String formatStationName(LOCATION station) {
        switch (station) {
            case EDIFICIO_STEFANI:
                return "Edificio Stefani";
            case CENTRO_DE_ESTUDIANTES:
                return "Centro de Estudiantes";
            case EDIFICIO_DE_BIOLOGIA:
                return "Edificio de Biología";
            case EDIFICIO_INGENIERIA_QUIMICA:
                return "Edificio de Ingeniería Química";
            case EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS:
                return "Edificio de Administración de Empresas";
            default:
                return station.name();
        }
    }

    public static LOCATION askLocationOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a station:");
        System.out.println("1 - Edificio Stefani");
        System.out.println("2 - Centro de Estudiantes");
        System.out.println("3 - Edificio de Biología");
        System.out.println("4 - Edificio de Ingeniería Química");
        System.out.println("5 - Edificio de Administración de Empresas");
        System.out.print("Press Number: ");

        int option = -1;
        try {
            option = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }

        LOCATION location = getLocationFromOption(option);
        if (location == null) {
            System.out.println("Invalid selection. No station selected.");
        }

        return location;
    }

    //get the location from the options given
    private static LOCATION getLocationFromOption(int option) {
        switch (option) {
            case 1:
                return LOCATION.EDIFICIO_STEFANI;
            case 2:
                return LOCATION.CENTRO_DE_ESTUDIANTES;
            case 3:
                return LOCATION.EDIFICIO_DE_BIOLOGIA;
            case 4:
                return LOCATION.EDIFICIO_INGENIERIA_QUIMICA;
            case 5:
                return LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS;
            default:
                return null;
        }
    }

    


    //Helper methods for the vehicle types enums :))

    public static VEHICLE_TYPE askVehicleTypeOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a vehicle type:");
        System.out.println("1 - Skooter");
        System.out.println("2 - Bicycle");
        System.out.println("3 - Skateboard");
        System.out.print("Enter option (1-3): ");

        int option = -1;
        try {
            option = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Enter a number");
        }

        VEHICLE_TYPE type = getVehicleTypeFromOption(option);
        if (type == null) {
            System.out.println("None selected");
        }

        return type;
    }

    private static VEHICLE_TYPE getVehicleTypeFromOption(int option) {
        switch (option) {
            case 1:
                return VEHICLE_TYPE.SKOOTER;
            case 2:
                return VEHICLE_TYPE.BICYCLE;
            case 3:
                return VEHICLE_TYPE.SKATEBOARD;
            default:
                return null;
        }
    }
}
