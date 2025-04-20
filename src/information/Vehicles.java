package information;

import information.AvailableStations.LOCATION;

/* 
 * This class is made in a stadard way to do class,
 * we have the variables at the begining,
 * followed by the constroctor and the the setters and getters.
 */

public class Vehicles {
    int id;
    private VEHICLE_TYPE vehicleType;
    String description;
    int schedule;
    LOCATION location;
    Boolean available;

    public static enum VEHICLE_TYPE {
        SKOOTER,
        BICYCLE,
        SKATEBOARD,
    }

    public Vehicles(int id, VEHICLE_TYPE vehicleType, String description, int schedule, LOCATION location, Boolean available) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.description = description;
        this.schedule = schedule;
        this.location = location;
        this.available = available;
    }

    // Setters and Getters.

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

    public int getschedule() {
        return schedule;
    }

    public void setschedule(int schedule) {
        this.schedule = schedule;
    }

    public LOCATION getLugar() {
        return location;
    }

    public void setLugar(String location) {
        this.location = EnumsHandler.getLocation(location);
    }

    public Boolean getavailable() {
        return available;
    }

    public void setavailable(Boolean available) {
        this.available = available;
    }
}

//metodo para crear set de available vehicles
// editar el set available method para que si es falso quitarlo de la lista y si es cierto anadirlo a la lista

//para marla - buscar manera de editar, remover, y almacenar los vehiculos respondientes