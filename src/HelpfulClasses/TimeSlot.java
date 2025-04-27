package HelpfulClasses;

//This class is just to help manage and maintain the timeslots correctly
//Everything is in military time because it's easier 

public class TimeSlot {
    private int startTime;
    private int endTime;

    public TimeSlot(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    /**
     * Converts military time (e.g., 1300) into standard AM/PM time.
     */
    private String formatTime(int time) {
        // First, separa hours and time from the start time and the end time
        //Como el tiempo es dado en militar se divide entre 100 para coger los primeros dos digitos
        //And then the modulus by 100 gives the las two digits 
        int hour = time / 100;
        int minute = time % 100;
        //divides by PM and AM
        String period = (hour >= 12) ? "PM" : "AM";
        //if its the first hour then its 12 am
        if (hour == 0) hour = 12;
        //if the hour is more than 12, subtract 12
        else if (hour > 12) hour -= 12;
        //Return the time in normal format hour:minutes
        return String.format("%d:%02d %s", hour, minute, period);
    }

    //Override toString to write the time slots easily readable
    @Override
    public String toString() {
        return formatTime(startTime) + " - " + formatTime(endTime);
    }


    @Override
    //be able to compare that two time slots are equals
    public boolean equals(Object obj) {
        //if the object is the same as the og object then its true
        if (this == obj){
            return true;
        }
        //if the object is from another class or null then is false
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        //cast object to timeslot to be able to compare
        TimeSlot other = (TimeSlot) obj;
        //They are equal if the start time and end times of eachother are the same
        return startTime == other.startTime && endTime == other.endTime;
    }
 
}
