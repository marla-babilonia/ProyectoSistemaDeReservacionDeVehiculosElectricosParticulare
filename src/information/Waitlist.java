package information;

import java.time.LocalTime;
import information.AvailableStations.LOCATION;

public class Waitlist {
    private int waitlistNum;
    private Users user;
    private Vehicles vehicle;
    private LOCATION location;
    private LocalTime startTime;
    private LocalTime endTime;
    private double totalCredits;
    private int month, date, start, end;

    public Waitlist(int waitlistNum, Users user, Vehicles vehicle, LOCATION location, int month, int date, int start, int end, double totalCredits) {
    this.waitlistNum = waitlistNum;
    this.user = user;
    this.vehicle = vehicle;
    this.location = location;
    this.month = month;
    this.date = date;
    this.start = start;
    this.end = end;
    this.startTime = LocalTime.of(start/100, start%100);
    this.endTime = LocalTime.of(end/100, end%100);
    this.totalCredits = totalCredits;
  }

    // Setters and Getters.

    public int getWaitlistNum(){
        return waitlistNum;
    }
    public void setWaitlistNum(int WaitlistNum){
        this.waitlistNum = WaitlistNum;
    }

    public Users getUser(){
        return user;
    }
    public void setUser(Users User){
        this.user = User;
    }

    public Vehicles getVehicle(){
        return vehicle;
    }
    public void setVehicle(Vehicles Vehicle){
        this.vehicle = Vehicle;
    }

    public LOCATION getLocation(){
        return location;
    }
    public void setLocation(LOCATION Location){
        this.location = Location;
    }

    public int getMonth(){ 
        return month; 
    }

    public int getDate(){ 
        return date;  
    }

    public int getStart(){ 
        return start; 
    }
    
    public int getEnd(){ 
        return end;   
    }

    public LocalTime getStartTime(){
        return startTime;
    }
    public void setStartTime(LocalTime StartTime){
        this.startTime = StartTime;
    }

    public LocalTime getEndTime(){
        return endTime;
    }
    public void setEndTime(LocalTime EndTime){
        this.endTime = EndTime;
    }

    public double getTotalCredits(){
        return totalCredits;
    }
    public void setTotaCredits(double TotalCredits){
        this.totalCredits = TotalCredits;
    }

}
