package information;

public class AvailableStations {

    private LOCATION locationName;
    private int maxCapacity;
    private int currentCapacity;

    public static enum LOCATION {
        EDIFICIO_STEFANI,
        CENTRO_DE_ESTUDIANTES,
        EDIFICIO_DE_BIOLOGIA,
        EDIFICIO_INGENIERIA_QUIMICA,
        EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS,
    }

    public AvailableStations(LOCATION locationName, int maxCapacity, int currentCapacity) {
        this.locationName = locationName;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public LOCATION getName() {
        return this.locationName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public void increaseCurrentCapacity() {
        this.currentCapacity++;
    }

    public void decreaseCurrentCapacity() {
        if (this.currentCapacity > 0) {
            this.currentCapacity--;
        }
    }

    
}
