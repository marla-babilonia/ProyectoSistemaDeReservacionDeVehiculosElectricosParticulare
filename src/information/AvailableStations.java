package information;

public class AvailableStations {

    private String name;
    private int maxCapacity;
    private int currentCapacity;

    public AvailableStations(String name, int maxCapacity, int currentCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public String getName() {
        return name;
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

}
