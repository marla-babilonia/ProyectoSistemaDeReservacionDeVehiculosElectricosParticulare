import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        LocalTime hours = LocalTime.now();
        System.out.println("Welcome back OPERATOR. For seeing the STATUS of VEHICLES enter 1, For seeing the RESERVATIONS enter 2, To CLOSE THE PROGRAM enter 3.");
        //TODO add posible errors (what if its is not a number, or a diferent number aka not 1,2 or 3)
        
        int option = scan.nextInt(); 
        while(option != 3) {
            if(option != 1 && option != 2) {
                System.out.println("PLEASE enter only the NUMBERS 1, 2 and 3.");
            }

            if(option == 1) {

                //Enter into STATUS.

                if(hours.getHour() < 7 || hours.getHour() >= 16){
                System.out.println("Sorry, our rent system is only open from 7:00am-6:00pm.");
                System.out.println(hours.getHour());
                    //TODO add a way to close the program || inmediatly turn back to the option screen.

                }

                 
            }

            //Enters into RESERVATIONS.

            if(option == 2) {

                //does NOT check if services.


                
            }
            
            option = scan.nextInt();
        }
        scan.close();
    }
}
