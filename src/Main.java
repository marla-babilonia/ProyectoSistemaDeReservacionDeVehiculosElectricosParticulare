import information.AvailableStations;
import information.Vehicles;
import information.Vehicles.VEHICLE_TYPE;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Stations stations = new Stations();
        Scanner scan = new Scanner(System.in);
        LocalTime hours = LocalTime.now();
        System.out.println("Welcome back OPERATOR.");
        //TODO add posible errors (what if its is not a number, or a diferent number aka not 1,2 or 3)
        
        int option = 0; 
        
        while(option != 3) {
            System.out.println("For seeing the STATUS of VEHICLES enter 1, For seeing the USERS enter 2, To CLOSE THE PROGRAM enter 3.");
            try{ // use a try to catch the error if operator inputs something that is not a number.
            option = scan.nextInt();
            switch (option) {
                case 1:
                if(hours.getHour() < 7 || hours.getHour() >= 16){
                    System.out.println("Sorry, our rent system is only open from 7:00am-6:00pm.");
                    System.out.println(hours.getHour());
                        //TODO add a way to close the program || inmediatly turn back to the option screen.
                    }
                    int Option_Station = 0;
                    System.out.println("1 for CENTRO, 2 for STEFANI, 3 for BIOLOGIA, 4 for QUIMICA, 5 for ADEM ");
                    Option_Station = scan.nextInt();
                    switch (Option_Station) {
                        case 1://lists vehicles segun su edificio
                        System.out.println("List of VEHICLES for CENTRO");
                        for (Vehicles v : stations.centroEstudiantesVehicles) {
                            System.out.println("ID: " + v.getID() + ", Type: " + v.getVehicleType() + ", Available: " + v.getavailable() + v.getdescription());
                        }
                            System.out.println("Would you like to edit a vehicle? (yes=1 / no=2)");//checks if operator wants to edit
                            int editOption = scan.nextInt();
                            if (editOption == 1) {
                                
                                int vehicleId = 0;
                                Vehicles vehicleToEdit = null;
                                boolean exists = false;
                                
                                
                                System.out.println("What do you wish t edit; 1 for Availability, 2 for Description, 3 for Removal, 4 for addition.");
                                editOption = 0;
                                editOption = scan.nextInt();
                                switch (editOption) {
                                    case 1:
                                        System.out.println("Enter the ID of the vechicle to edit: ");//checks for id 
                                        vehicleId = scan.nextInt();
                                        for (Vehicles v : stations.centroEstudiantesVehicles) {
                                            if (v.getID() == vehicleId) {
                                                exists = true;
                                                vehicleToEdit = v;
                                                break;
                                            }
                                        }
        
                                        if (!exists) {
                                            System.out.println("That ID does not exist in this location.");//if id does not exist you cant edit the vehicle
                                            break;
                                        }
                                        System.out.println("Set Availability;");
                                        boolean aval = scan.nextBoolean();
                                        vehicleToEdit.setavailable(aval);
                                        break;
                                    case 2: 
                                    System.out.println("Enter the ID of the vechicle to edit: ");
                                        vehicleId = scan.nextInt();
                                        for (Vehicles v : stations.centroEstudiantesVehicles) {
                                            if (v.getID() == vehicleId) {
                                                exists = true;
                                                vehicleToEdit = v;
                                                break;
                                            }
                                        }
        
                                        if (!exists) {
                                            System.out.println("That ID does not exist in this location.");
                                            break;
                                        }
                                    System.out.println("Set Description;");
                                        String newdes = scan.next();
                                        vehicleToEdit.setdescription(newdes);
                                        break;
                                    case 3:
                                    System.out.println("Enter the ID of the vechicle to edit: ");
                                        vehicleId = scan.nextInt();
                                        for (Vehicles v : stations.centroEstudiantesVehicles) {
                                            if (v.getID() == vehicleId) {
                                                exists = true;
                                                vehicleToEdit = v;
                                                break;
                                            }
                                        }
        
                                        if (!exists) {
                                            System.out.println("That ID does not exist in this location.");
                                            break;
                                        }
                                    System.out.println("Enter again the ID of the vehicle to confirm;");//makes the operator confirm that he does wahnt to remove the correct id.
                                        vehicleId = scan.nextInt();
                                        for (Vehicles v : stations.centroEstudiantesVehicles) {
                                            if (v.getID() == vehicleId) {
                                                stations.centroEstudiantesVehicles.remove(v);
                                                break;
                                            }
                                        }
                                        break;
                                    case 4:
                                                                        
                                        System.out.println("Enter the vehicle TYPE (1=BICYCLE, 2=SKOOTER, 3=SKATEBOARD):");//creates new vehicle to add
                                        int typeInput = scan.nextInt();
                                        VEHICLE_TYPE type = null;
                                        switch (typeInput) {
                                            case 1: type = VEHICLE_TYPE.BICYCLE; break;
                                            case 2: type = VEHICLE_TYPE.SKOOTER; break;
                                            case 3: type = VEHICLE_TYPE.SKATEBOARD; break;
                                            default:
                                                System.out.println("Invalid vehicle type.");
                                                break;
                                        }

                                        System.out.println("Enter a description for the vehicle:");
                                        scan.nextLine(); // clear scanner buffer
                                        String description = scan.nextLine();

                                        System.out.println("Is the vehicle available? (true/false):");
                                        boolean availability = scan.nextBoolean();

                                        System.out.println("Enter the new ID:");
                                        int newVehicleId = scan.nextInt();
                                        while (stations.isIdUsed(newVehicleId)){//makes sure that the id does not alredt exists
                                            System.out.println("You cant use the same ID that is alredy in used.");
                                            newVehicleId = scan.nextInt();
                                        }
                                        Vehicles newVehicle = new Vehicles(newVehicleId, type, description, 0, AvailableStations.LOCATION.CENTRO_DE_ESTUDIANTES, availability);

                                        stations.centroEstudiantesVehicles.add(newVehicle);
                                        stations.allVehicles.add(newVehicle.getID());

                                        System.out.println("Vehicle added successfully! ID: " + newVehicleId);
                                        break;
                                        
                                    default:
                                        System.out.println("ERROR: Please enter a VALID number (1, 2, 3, 4 or 5).");
                                }
                            }
                            break;
                        case 2:
                        System.out.println("List of VEHICLES for STEFANI");//repeat the case 1 but for stefani this time
                        for (Vehicles v : stations.stefaniVehicles) {
                            System.out.println("ID: " + v.getID() + ", Type: " + v.getVehicleType() + ", Available: " + v.getavailable() + v.getdescription());
                        }
                        System.out.println("Would you like to edit a vehicle? (yes=1 / no=2)");
                        editOption = scan.nextInt();
                        if (editOption == 1) {
                                
                            int vehicleId = 0;
                            Vehicles vehicleToEdit = null;
                            boolean exists = false;
                            
                            
                            System.out.println("What do you wish t edit; 1 for Availability, 2 for Description, 3 for Removal, 4 for addition.");
                            editOption = 0;
                            editOption = scan.nextInt();
                            switch (editOption) {
                                case 1:
                                    System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.stefaniVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                    System.out.println("Set Availability;");
                                    boolean aval = scan.nextBoolean();
                                    vehicleToEdit.setavailable(aval);
                                    break;
                                case 2: 
                                System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.stefaniVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                System.out.println("Set Description;");
                                    String newdes = scan.next();
                                    vehicleToEdit.setdescription(newdes);
                                    break;
                                case 3:
                                System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.stefaniVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                System.out.println("Enter again the ID of the vehicle to confirm;");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.stefaniVehicles) {
                                        if (v.getID() == vehicleId) {
                                            stations.stefaniVehicles.remove(v);
                                            break;
                                        }
                                    }
                                    break;
                                case 4:
                                                                    
                                    System.out.println("Enter the vehicle TYPE (1=BICYCLE, 2=SKOOTER, 3=SKATEBOARD):");
                                    int typeInput = scan.nextInt();
                                    VEHICLE_TYPE type = null;
                                    switch (typeInput) {
                                        case 1: type = VEHICLE_TYPE.BICYCLE; break;
                                        case 2: type = VEHICLE_TYPE.SKOOTER; break;
                                        case 3: type = VEHICLE_TYPE.SKATEBOARD; break;
                                        default:
                                            System.out.println("Invalid vehicle type.");
                                            break;
                                    }

                                    System.out.println("Enter a description for the vehicle:");
                                    scan.nextLine(); // clear scanner buffer
                                    String description = scan.nextLine();

                                    System.out.println("Is the vehicle available? (true/false):");
                                    boolean availability = scan.nextBoolean();

                                    System.out.println("Enter the new ID:");
                                    int newVehicleId = scan.nextInt();
                                    while (stations.isIdUsed(newVehicleId)){
                                        System.out.println("You cant use the same ID that is alredy in used.");
                                        newVehicleId = scan.nextInt();
                                    }
                                    Vehicles newVehicle = new Vehicles(newVehicleId, type, description, 0, AvailableStations.LOCATION.EDIFICIO_STEFANI, availability);

                                    stations.stefaniVehicles.add(newVehicle);
                                    stations.allVehicles.add(newVehicle.getID());

                                    System.out.println("Vehicle added successfully! ID: " + newVehicleId);
                                    break;
                                    
                                default:
                                    System.out.println("ERROR: Please enter a VALID number (1, 2, 3, 4 or 5).");
                            }
                        }
                            break;
                        case 3:
                        System.out.println("List of VEHICLES for BIOLOGIA");
                        for (Vehicles v : stations.biologiaVehicles) {
                            System.out.println("ID: " + v.getID() + ", Type: " + v.getVehicleType() + ", Available: " + v.getavailable() + v.getdescription());
                        }
                        System.out.println("Would you like to edit a vehicle? (yes=1 / no=2)");
                        editOption = scan.nextInt();
                        if (editOption == 1) {
                                
                            int vehicleId = 0;
                            Vehicles vehicleToEdit = null;
                            boolean exists = false;
                            
                            
                            System.out.println("What do you wish t edit; 1 for Availability, 2 for Description, 3 for Removal, 4 for addition.");
                            editOption = 0;
                            editOption = scan.nextInt();
                            switch (editOption) {
                                case 1:
                                    System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.biologiaVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                    System.out.println("Set Availability;");
                                    boolean aval = scan.nextBoolean();
                                    vehicleToEdit.setavailable(aval);
                                    break;
                                case 2: 
                                System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.biologiaVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                System.out.println("Set Description;");
                                    String newdes = scan.next();
                                    vehicleToEdit.setdescription(newdes);
                                    break;
                                case 3:
                                System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.biologiaVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                System.out.println("Enter again the ID of the vehicle to confirm;");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.biologiaVehicles) {
                                        if (v.getID() == vehicleId) {
                                            stations.biologiaVehicles.remove(v);
                                            break;
                                        }
                                    }
                                    break;
                                case 4:
                                                                    
                                    System.out.println("Enter the vehicle TYPE (1=BICYCLE, 2=SKOOTER, 3=SKATEBOARD):");
                                    int typeInput = scan.nextInt();
                                    VEHICLE_TYPE type = null;
                                    switch (typeInput) {
                                        case 1: type = VEHICLE_TYPE.BICYCLE; break;
                                        case 2: type = VEHICLE_TYPE.SKOOTER; break;
                                        case 3: type = VEHICLE_TYPE.SKATEBOARD; break;
                                        default:
                                            System.out.println("Invalid vehicle type.");
                                            break;
                                    }

                                    System.out.println("Enter a description for the vehicle:");
                                    scan.nextLine(); // clear scanner buffer
                                    String description = scan.nextLine();

                                    System.out.println("Is the vehicle available? (true/false):");
                                    boolean availability = scan.nextBoolean();

                                    System.out.println("Enter the new ID:");
                                    int newVehicleId = scan.nextInt();
                                    while (stations.isIdUsed(newVehicleId)){
                                        System.out.println("You cant use the same ID that is alredy in used.");
                                        newVehicleId = scan.nextInt();
                                    }
                                    Vehicles newVehicle = new Vehicles(newVehicleId, type, description, 0, AvailableStations.LOCATION.EDIFICIO_DE_BIOLOGIA, availability);

                                    stations.biologiaVehicles.add(newVehicle);
                                    stations.allVehicles.add(newVehicle.getID());

                                    System.out.println("Vehicle added successfully! ID: " + newVehicleId);
                                    break;
                                    
                                default:
                                    System.out.println("ERROR: Please enter a VALID number (1, 2, 3, 4 or 5).");
                            }
                        }
                            break;
                        case 4:System.out.println("List of VEHICLES for QUIMICA");
                        for (Vehicles v : stations.quimicaVehicles) {
                            System.out.println("ID: " + v.getID() + ", Type: " + v.getVehicleType() + ", Available: " + v.getavailable() + v.getdescription());
                        }
                            
                        System.out.println("Would you like to edit a vehicle? (yes=1 / no=2)");
                        editOption = scan.nextInt();
                        if (editOption == 1) {
                                
                            int vehicleId = 0;
                            Vehicles vehicleToEdit = null;
                            boolean exists = false;
                            
                            
                            System.out.println("What do you wish t edit; 1 for Availability, 2 for Description, 3 for Removal, 4 for addition.");
                            editOption = 0;
                            editOption = scan.nextInt();
                            switch (editOption) {
                                case 1:
                                    System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.quimicaVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                    System.out.println("Set Availability;");
                                    boolean aval = scan.nextBoolean();
                                    vehicleToEdit.setavailable(aval);
                                    break;
                                case 2: 
                                System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.quimicaVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                System.out.println("Set Description;");
                                    String newdes = scan.next();
                                    vehicleToEdit.setdescription(newdes);
                                    break;
                                case 3:
                                System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.quimicaVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                System.out.println("Enter again the ID of the vehicle to confirm;");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.quimicaVehicles) {
                                        if (v.getID() == vehicleId) {
                                            stations.quimicaVehicles.remove(v);
                                            break;
                                        }
                                    }
                                    break;
                                case 4:
                                                                    
                                    System.out.println("Enter the vehicle TYPE (1=BICYCLE, 2=SKOOTER, 3=SKATEBOARD):");
                                    int typeInput = scan.nextInt();
                                    VEHICLE_TYPE type = null;
                                    switch (typeInput) {
                                        case 1: type = VEHICLE_TYPE.BICYCLE; break;
                                        case 2: type = VEHICLE_TYPE.SKOOTER; break;
                                        case 3: type = VEHICLE_TYPE.SKATEBOARD; break;
                                        default:
                                            System.out.println("Invalid vehicle type.");
                                            break;
                                    }

                                    System.out.println("Enter a description for the vehicle:");
                                    scan.nextLine(); // clear scanner buffer
                                    String description = scan.nextLine();

                                    System.out.println("Is the vehicle available? (true/false):");
                                    boolean availability = scan.nextBoolean();

                                    System.out.println("Enter the new ID:");
                                    int newVehicleId = scan.nextInt();
                                    while (stations.isIdUsed(newVehicleId)){
                                        System.out.println("You cant use the same ID that is alredy in used.");
                                        newVehicleId = scan.nextInt();
                                    }
                                    Vehicles newVehicle = new Vehicles(newVehicleId, type, description, 0, AvailableStations.LOCATION.EDIFICIO_INGENIERIA_QUIMICA, availability);

                                    stations.quimicaVehicles.add(newVehicle);
                                    stations.allVehicles.add(newVehicle.getID());

                                    System.out.println("Vehicle added successfully! ID: " + newVehicleId);
                                    break;
                                    
                                default:
                                    System.out.println("ERROR: Please enter a VALID number (1, 2, 3, 4 or 5).");
                            }
                        }
                            break;
                        case 5:
                        System.out.println("List of VEHICLES for ADEM");
                        for (Vehicles v : stations.adminEmpresasVehicles) {
                            System.out.println("ID: " + v.getID() + ", Type: " + v.getVehicleType() + ", Available: " + v.getavailable() + v.getdescription());
                        }
                        System.out.println("Would you like to edit a vehicle? (yes=1 / no=2)");
                        editOption = scan.nextInt();
                        if (editOption == 1) {
                                
                            int vehicleId = 0;
                            Vehicles vehicleToEdit = null;
                            boolean exists = false;
                            
                            
                            System.out.println("What do you wish t edit; 1 for Availability, 2 for Description, 3 for Removal, 4 for addition.");
                            editOption = 0;
                            editOption = scan.nextInt();
                            switch (editOption) {
                                case 1:
                                    System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.adminEmpresasVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                    System.out.println("Set Availability;");
                                    boolean aval = scan.nextBoolean();
                                    vehicleToEdit.setavailable(aval);
                                    break;
                                case 2: 
                                System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.adminEmpresasVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                System.out.println("Set Description;");
                                    String newdes = scan.next();
                                    vehicleToEdit.setdescription(newdes);
                                    break;
                                case 3:
                                System.out.println("Enter the ID of the vechicle to edit: ");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.adminEmpresasVehicles) {
                                        if (v.getID() == vehicleId) {
                                            exists = true;
                                            vehicleToEdit = v;
                                            break;
                                        }
                                    }
    
                                    if (!exists) {
                                        System.out.println("That ID does not exist in this location.");
                                        break;
                                    }
                                System.out.println("Enter again the ID of the vehicle to confirm;");
                                    vehicleId = scan.nextInt();
                                    for (Vehicles v : stations.adminEmpresasVehicles) {
                                        if (v.getID() == vehicleId) {
                                            stations.adminEmpresasVehicles.remove(v);
                                            break;
                                        }
                                    }
                                    break;
                                case 4:
                                                                    
                                    System.out.println("Enter the vehicle TYPE (1=BICYCLE, 2=SKOOTER, 3=SKATEBOARD):");
                                    int typeInput = scan.nextInt();
                                    VEHICLE_TYPE type = null;
                                    switch (typeInput) {
                                        case 1: type = VEHICLE_TYPE.BICYCLE; break;
                                        case 2: type = VEHICLE_TYPE.SKOOTER; break;
                                        case 3: type = VEHICLE_TYPE.SKATEBOARD; break;
                                        default:
                                            System.out.println("Invalid vehicle type.");
                                            break;
                                    }

                                    System.out.println("Enter a description for the vehicle:");
                                    scan.nextLine(); // clear scanner buffer
                                    String description = scan.nextLine();

                                    System.out.println("Is the vehicle available? (true/false):");
                                    boolean availability = scan.nextBoolean();

                                    System.out.println("Enter the new ID:");
                                    int newVehicleId = scan.nextInt();
                                    while (stations.isIdUsed(newVehicleId)){
                                        System.out.println("You cant use the same ID that is alredy in used.");
                                        newVehicleId = scan.nextInt();
                                    }
                                    Vehicles newVehicle = new Vehicles(newVehicleId, type, description, 0, AvailableStations.LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS, availability);

                                    stations.adminEmpresasVehicles.add(newVehicle);
                                    stations.allVehicles.add(newVehicle.getID());

                                    System.out.println("Vehicle added successfully! ID: " + newVehicleId);
                                    break;
                                    
                                default:
                                    System.out.println("ERROR: Please enter a VALID number (1, 2, 3, 4 or 5).");
                            }
                        }
                            break;
                        default:
                        System.out.println("ERROR: Please enter a VALID number (1, 2, 3, 4 or 5).");
                    }

                    break;
                case 2: 

                System.out.println("RESERVATIONS");
                break;
                case 3: break; //closer program/while loop.
                default:
                    System.out.println("ERROR: Please enter a VALID number (1, 2, or 3)."); //when error is wrong number input.
                    break;
            }
        } catch (InputMismatchException e) {
                System.out.println("ERROR: Please enter a valid NUMBER (1, 2, or 3)."); //when error is wrong input type.
                scan.next(); // Clear the invalid input from the scanner.
        }
        
            
        }
        scan.close();
    }
}

// anadir que llame a todos los csvs una vez se abre el programa