package information;

import java.time.LocalTime;
import information.AvailableStations.LOCATION;

public class Reservations {
    private int reservationNum;
    private Users user;
    private Vehicles vehicle;
    private LOCATION pickupLocation;
    private LOCATION dropoffLocation;
    private LocalTime startTime;
    private LocalTime endTime;
    private int totalCredits;

    public Reservations(int reservationNum, Users user, Vehicles vehicle, LOCATION pickupLocation, LOCATION dropoffLocation, LocalTime startTime, LocalTime endTime, int totalCredits){
        this.reservationNum = reservationNum;
        this.user = user;
        this.vehicle = vehicle;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCredits = totalCredits;
    }

    // Setters and Getters.

    public int getReservationNum(){
        return reservationNum;
    }
    public void setReservationNum(int ReservationNum){
        this.reservationNum = ReservationNum;
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

    public LOCATION getPickupLocation(){
        return pickupLocation;
    }
    public void setPickupLocation(LOCATION PickupLocation){
        this.pickupLocation = PickupLocation;
    }

    public LOCATION getDropoffLocation(){
        return dropoffLocation;
    }
    public void setDropoffLocation(LOCATION DropoffLocation){
        this.dropoffLocation = DropoffLocation;
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
