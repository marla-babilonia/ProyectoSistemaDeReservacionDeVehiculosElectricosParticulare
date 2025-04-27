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
    private int totalCredits;

    public Waitlist(int waitlistNum, Users user, Vehicles vehicle, LOCATION location, LocalTime startTime, LocalTime endTime, int totalCredits){
        this.waitlistNum = waitlistNum;
        this.user = user;
        this.vehicle = vehicle;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public int getTotalCredits(){
        return totalCredits;
    }
    public void setTotaCredits(int TotalCredits){
        this.totalCredits = TotalCredits;
    }

}
