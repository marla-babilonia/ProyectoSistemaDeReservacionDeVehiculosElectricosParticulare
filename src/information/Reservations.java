package information;

import HelpfulClasses.EnumsHandler;
import information.AvailableStations.LOCATION;

public class Reservations {
    private Users student;
    private Vehicles vehicle;
    private LOCATION station;
    private int month;    
    private int date;     
    private int startTime; // Start and end time are in military time, so its easier to calculate duration
    private int endTime;   
    private double creditCost;

    public Reservations(Users student, Vehicles vehicle, LOCATION station, int month, int date, int startTime, int endTime, double cost) {
        this.student = student;
        this.vehicle = vehicle;
        this.station = station;
        this.month = month;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creditCost = cost;
    }

    //Getters
    public Users getStudent() {
        return student;
    }

    public Vehicles getVehicle() {
        return vehicle;
    }

    public LOCATION getStation() {
        return station;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public double getCreditCost() {
        return creditCost;
    }

    //Setters
    public void setStudent(Users student) {
        this.student = student;
    }

    public void setVehicle(Vehicles vehicle) {
        this.vehicle = vehicle;
    }

    public void setStation(LOCATION station) {
        this.station = station;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setCreditCost(double creditCost) {
        this.creditCost = creditCost;
    }

    @Override
    public String toString() {

        String stationName = (station != null) ? EnumsHandler.formatStationName(station) : "No Station";
        return String.format(
            "Reservation[Student ID=%d, Vehicle ID=%d, Station=%s, Date=%02d/%02d, Start=%04d, End=%04d, Credits=%.1f]",
            student.getstudentid(),
            vehicle.getID(),
            stationName,
            month,
            date,
            startTime,
            endTime,
            creditCost
        );
    }


    
}