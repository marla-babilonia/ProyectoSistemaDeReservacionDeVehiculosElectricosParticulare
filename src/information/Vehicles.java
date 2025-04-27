package information;

import java.util.Map;
import java.util.Set;

import HelpfulClasses.EnumsHandler;
import information.AvailableStations.LOCATION;

/* 
 * This class is made in a stadard way to do class,
 * we have the variables at the begining,
 * followed by the constroctor and the the setters and getters.
 */

public class Vehicles {
    Users owner; //owner is an object (has all the owner information)
    int id;
    VEHICLE_TYPE vehicleType;
    String description;
    Map<Integer, Map<Integer, Set<Integer>>> schedule; //Schedule is stored in a map by month (as key)
                                            // and a value of (key date : value set of times)
    LOCATION location;
    Boolean available;

    public static enum VEHICLE_TYPE {
        SKOOTER,
        BICYCLE,
        SKATEBOARD,
    }

    public Vehicles(Users owner, int id, VEHICLE_TYPE vehicleType, String description, Map<Integer, Map<Integer, Set<Integer>>> schedule, LOCATION location, Boolean available) {
        this.owner = owner;
        this.id = id;
        this.vehicleType = vehicleType;
        this.description = description;
        this.schedule = schedule;
        this.location = location;
        this.available = available;
    }

    // Setters and Getters

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public VEHICLE_TYPE getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(VEHICLE_TYPE type) {
        this.vehicleType = type;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public Map<Integer, Map<Integer, Set<Integer>>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<Integer, Map<Integer, Set<Integer>>> schedule) {
        this.schedule = schedule;
    }

    public LOCATION getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = EnumsHandler.getLocation(location);
    }

    public Boolean getavailable() {
        return available;
    }

    public void setavailable(Boolean available) {
        this.available = available;
    }

    //change the to string method to make it easier to print
    
    @Override
    public String toString() {
        return String.format(
            "Vehicle[ID = %d, Owner ID = %d, It's a: %s, It's in: %s, Available? %b, Description = %s]",
            id,
            owner.getstudentid(),
            vehicleType.name(),
            EnumsHandler.formatStationName(location),
            available,
            description
        );
    }
}

