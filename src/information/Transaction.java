package information;

public class Transaction {

    private static int nextTransactionId = 1; // Auto-incrementing ID

    private int transactionId;
    private Users client;
    private Users owner;
    private Vehicles vehicle;
    private double creditAmount;
    private int month;
    private int day;
    private int startTime;
    private int endTime;

    public Transaction(Users client, Users owner, Vehicles vehicle, double cost, int month, int day, int startTime, int endTime) {
        this.transactionId = nextTransactionId++;
        this.client = client;
        this.owner = owner;
        this.vehicle = vehicle;
        this.creditAmount = cost;
        this.month = month;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters
    public int getTransactionId() {
        return transactionId;
    }

    public Users getClient() {
        return client;
    }

    public Users getOwner() {
        return owner;
    }

    public Vehicles getVehicle() {
        return vehicle;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
    public String toString() {
        return String.format(
           "Transaction ID: %d | Client: %d | Owner: %d | Vehicle: %d | Credits: %.1f | Date: %02d/%02d | Time: %04d-%04d",
            transactionId,
            client.getstudentid(),
            owner.getstudentid(),
            vehicle.getID(),
            creditAmount,
            month,
            day,
            startTime,
            endTime
        );
    }

    public void setTransactionId(int id) {
        this.transactionId = id;
        if (id >= nextTransactionId) {
            nextTransactionId = id + 1;
        }
    }
}
