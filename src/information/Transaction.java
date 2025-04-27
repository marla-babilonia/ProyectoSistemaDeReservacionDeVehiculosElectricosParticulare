package information;

public class Transaction {

    private static int nextTransactionId = 1; // Auto-incrementing ID

    private int transactionId;
    private Users client;
    private Users owner;
    private Vehicles vehicle;
    private int creditAmount;
    private int month;
    private int day;
    private int startTime;
    private int endTime;

    public Transaction(Users client, Users owner, Vehicles vehicle, int creditAmount, int month, int day, int startTime, int endTime) {
        this.transactionId = nextTransactionId++;
        this.client = client;
        this.owner = owner;
        this.vehicle = vehicle;
        this.creditAmount = creditAmount;
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

    public int getCreditAmount() {
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
        return String.format("Transaction ID: %d | Client: %s | Owner: %s | Vehicle: %s | Credits: %d | Date: %02d/%02d | Time: %02d:00-%02d:00",
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
}
