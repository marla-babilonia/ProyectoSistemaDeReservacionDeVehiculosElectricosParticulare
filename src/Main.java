import information.VehiclesHandler;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        VehiclesHandler vh = new VehiclesHandler();
        Scanner scan = new Scanner(System.in);
        LocalTime hours = LocalTime.now();
        System.out.println("Welcome back OPERATOR. For seeing the STATUS of VEHICLES enter 1, For seeing the RESERVATIONS enter 2, To CLOSE THE PROGRAM enter 3.");
        //TODO add posible errors (what if its is not a number, or a diferent number aka not 1,2 or 3)
        
        int option = 0; 
        
        while(option != 3) {
            try{ // use a try to catch the error if operator inputs something that is not a number.
            option = scan.nextInt();
            switch (option) {
                case 1:
                if(hours.getHour() < 7 || hours.getHour() >= 16){
                    System.out.println("Sorry, our rent system is only open from 7:00am-6:00pm.");
                    System.out.println(hours.getHour());
                        //TODO add a way to close the program || inmediatly turn back to the option screen.
                    }
                    System.out.println("List of VEHICLES");
                    System.out.println(vh.getAvailableVehicles());

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