package information;
import CSVHandlers.CSVLoader;
import java.util.Stack;

public class UndoStack {

    private static final Stack<UndoAction> undoStack = new Stack<>();
    private static final Stack<UndoAction> redoStack = new Stack<>();

    private static class UndoAction {
        enum ActionType { ADD, DELETE }
    
        private Reservations reservation;
        private double transactionAmount;
        private Users client;
        private Users owner;
        private Users user;
        private Vehicles vehicle;
        private ActionType actionType;
    

        public UndoAction(Reservations reservation, Users client, Users owner, double transactionAmount, ActionType actionType) {
            this.reservation = reservation;
            this.client = client;
            this.owner = owner;
            this.transactionAmount = transactionAmount;
            this.actionType = actionType;
        }
    

        public UndoAction(Users user, ActionType actionType) {
            this.user = user;
            this.actionType = actionType;
        }
    

        public UndoAction(Vehicles vehicle, ActionType actionType) {
            this.vehicle = vehicle;
            this.actionType = actionType;
        }
    

        public Reservations getReservation() { return reservation; }
        public double getTransactionAmount() { return transactionAmount; }
        public Users getClient() { return client; }
        public Users getOwner() { return owner; }
        public Users getUser() { return user; }
        public Vehicles getVehicle() { return vehicle; }
        public ActionType getActionType() { return actionType; }
    }

    public static void undoLastAction() {
        if (undoStack.isEmpty()) {
            System.out.println("No actions to undo.");
            return;
        }
    
        UndoAction lastAction = undoStack.pop();
        redoStack.push(lastAction);
    
        if (lastAction.getReservation() != null && lastAction.getClient() != null && lastAction.getOwner() != null) {
            if (lastAction.getActionType() == UndoAction.ActionType.ADD) {
                
                if (CSVLoader.getReservations().remove(lastAction.getReservation())) {
                    System.out.println("Reservation undone (removed).");
                }
                lastAction.getClient().setCredits(lastAction.getClient().getCredits() + lastAction.getTransactionAmount());
                lastAction.getOwner().setCredits(lastAction.getOwner().getCredits() - lastAction.getTransactionAmount());
                System.out.println("Credits refunded.");
            } else if (lastAction.getActionType() == UndoAction.ActionType.DELETE) {
                
                CSVLoader.getReservations().add(lastAction.getReservation());
                lastAction.getClient().setCredits(lastAction.getClient().getCredits() - lastAction.getTransactionAmount());
                lastAction.getOwner().setCredits(lastAction.getOwner().getCredits() + lastAction.getTransactionAmount());
                System.out.println("Reservation restored and credits adjusted.");
            }
        } else if (lastAction.getUser() != null) {
            if (lastAction.getActionType() == UndoAction.ActionType.ADD) {
                if (UsersHandler.getUsers().remove(lastAction.getUser())) {
                    System.out.println("User addition undone (removed).");
                }
            } else if (lastAction.getActionType() == UndoAction.ActionType.DELETE) {
                if (UsersHandler.getUsers().add(lastAction.getUser())) {
                    System.out.println("User deletion undone (restored).");
                }
            }
        } else if (lastAction.getVehicle() != null) {
            if (lastAction.getActionType() == UndoAction.ActionType.ADD) {
                if (VehiclesHandler.getVehicles().remove(lastAction.getVehicle())) {
                    System.out.println("Vehicle addition undone (removed).");
                }
            } else if (lastAction.getActionType() == UndoAction.ActionType.DELETE) {
                if (VehiclesHandler.getVehicles().add(lastAction.getVehicle())) {
                    System.out.println("Vehicle deletion undone (restored).");
                }
            }
        }
    }
    

    public static void redoLastAction() {
        if (redoStack.isEmpty()) {
            System.out.println("No actions to redo.");
            return;
        }
    
        UndoAction lastAction = redoStack.pop();
        undoStack.push(lastAction);
    
        if (lastAction.getReservation() != null && lastAction.getClient() != null && lastAction.getOwner() != null) {
            if (lastAction.getActionType() == UndoAction.ActionType.ADD) {
                CSVLoader.getReservations().add(lastAction.getReservation());
                lastAction.getClient().setCredits(lastAction.getClient().getCredits() - lastAction.getTransactionAmount());
                lastAction.getOwner().setCredits(lastAction.getOwner().getCredits() + lastAction.getTransactionAmount());
                System.out.println("Reservation re-added and credits deducted/added.");
            } else if (lastAction.getActionType() == UndoAction.ActionType.DELETE) {
                CSVLoader.getReservations().remove(lastAction.getReservation());
                lastAction.getClient().setCredits(lastAction.getClient().getCredits() + lastAction.getTransactionAmount());
                lastAction.getOwner().setCredits(lastAction.getOwner().getCredits() - lastAction.getTransactionAmount());
                System.out.println("Reservation re-deleted and credits adjusted.");
            }
        } else if (lastAction.getUser() != null) {
            if (lastAction.getActionType() == UndoAction.ActionType.ADD) {
                UsersHandler.getUsers().add(lastAction.getUser());
                System.out.println("User addition redone.");
            } else if (lastAction.getActionType() == UndoAction.ActionType.DELETE) {
                UsersHandler.getUsers().remove(lastAction.getUser());
                System.out.println("User deletion redone.");
            }
        } else if (lastAction.getVehicle() != null) {
            if (lastAction.getActionType() == UndoAction.ActionType.ADD) {
                VehiclesHandler.getVehicles().add(lastAction.getVehicle());
                System.out.println("Vehicle addition redone.");
            } else if (lastAction.getActionType() == UndoAction.ActionType.DELETE) {
                VehiclesHandler.getVehicles().remove(lastAction.getVehicle());
                System.out.println("Vehicle deletion redone.");
            }
        }
    }
    

    public static void recordReservationAddition(Reservations reservation, Users client, Users owner, double transactionAmount) {
        undoStack.push(new UndoAction(reservation, client, owner, transactionAmount, UndoAction.ActionType.ADD));
        redoStack.clear();
    }

    public static void recordReservationDeletion(Reservations reservation, Users client, Users owner, double transactionAmount) {
        undoStack.push(new UndoAction(reservation, client, owner, transactionAmount, UndoAction.ActionType.DELETE));
        redoStack.clear();
    }

    public static void recordUserAddition(Users user) {
        undoStack.push(new UndoAction(user, UndoAction.ActionType.ADD));
        redoStack.clear();
    }

    public static void recordUserDeletion(Users user) {
        undoStack.push(new UndoAction(user, UndoAction.ActionType.DELETE));
        redoStack.clear();
    }

    public static void recordVehicleAddition(Vehicles vehicle) {
        undoStack.push(new UndoAction(vehicle, UndoAction.ActionType.ADD));
        redoStack.clear();
    }

    public static void recordVehicleDeletion(Vehicles vehicle) {
        undoStack.push(new UndoAction(vehicle, UndoAction.ActionType.DELETE));
        redoStack.clear();
    }
}
