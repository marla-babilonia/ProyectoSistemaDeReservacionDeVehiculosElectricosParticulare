import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        LocalTime hours = LocalTime.now();
        System.out.println("Please insert a number from 1 to 3. USER 1, OPERATOR 2, CLOSE THE PROGRAM 3.");
        //TODO add posible errors (what if its is not a number, or a diferent number aka not 1,2 or 3)
        
        int option = scan.nextInt(); 
        while(option != 3) {

            //enters in USER.

            if(option == 1) {

                //checks if services are open (AKA: is between 7am and 6pm).

                if(hours.getHour() < 7 || hours.getHour() >= 16){
                System.out.println("Sorry, our rent system is only open from 7:00am-6:00pm.");
                System.out.println(hours.getHour());
                    //TODO add a way to close the program || inmediatly turn back to the option screen.

                }

                 
            }

            //enters in OPERATOR.

            if(option == 2) {

                //does NOT check if services.


                
            }
            
            option = scan.nextInt();
        }
        scan.close();
    }
}
