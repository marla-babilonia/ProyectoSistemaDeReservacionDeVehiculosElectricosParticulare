package information;
import CSVHandlers.CSVLoader;
import java.util.Stack;

public class UndoStack {

    private static final Stack<UndoAction> undoStack = new Stack<>();
    private static final Stack<UndoAction> redoStack = new Stack<>();

    private static class UndoAction {
        enum ActionType { ADD, DELETE }
    
        private Reservations reservation;
        private Transaction transaction;
        private Users user;
        private Vehicles vehicle;
        private ActionType actionType;
    
        // Constructor for reservation & transaction
        public UndoAction(Reservations reservation, Transaction transaction, ActionType actionType) {
            this.reservation = reservation;
            this.transaction = transaction;
            this.actionType = actionType;
        }
    
        // Constructor for user
        public UndoAction(Users user, ActionType actionType) {
            this.user = user;
            this.actionType = actionType;
        }
    
        // Constructor for vehicle
        public UndoAction(Vehicles vehicle, ActionType actionType) {
            this.vehicle = vehicle;
            this.actionType = actionType;
        }
    
        // Getters
        public Reservations getReservation() { return reservation; }
        public Transaction getTransaction() { return transaction; }
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
    
        if (lastAction.getReservation() != null && lastAction.getTransaction() != null) {
            if (lastAction.getActionType() == UndoAction.ActionType.ADD) {
                // Undo reservation addition -> REMOVE them
                if (CSVLoader.getReservations().remove(lastAction.getReservation())) {
                    System.out.println("Reservation undone (removed).");
                }
                if (TransactionsHandler.getAllTransactions().remove(lastAction.getTransaction())) {
                    System.out.println("Transaction undone (removed).");
                }
                Users client = lastAction.getTransaction().getClient();
                Users owner = lastAction.getTransaction().getOwner();
                double credits = lastAction.getTransaction().getCreditAmount();
    
                client.setCredits(client.getCredits() + credits);
                owner.setCredits(owner.getCredits() - credits);
                System.out.println("Credits refunded.");
            } else if (lastAction.getActionType() == UndoAction.ActionType.DELETE) {
                // Undo reservation deletion -> ADD them back
                CSVLoader.getReservations().add(lastAction.getReservation());
                TransactionsHandler.getAllTransactions().add(lastAction.getTransaction());
    
                Users client = lastAction.getTransaction().getClient();
                Users owner = lastAction.getTransaction().getOwner();
                double credits = lastAction.getTransaction().getCreditAmount();
    
                client.setCredits(client.getCredits() - credits);
                owner.setCredits(owner.getCredits() + credits);
                System.out.println("Reservation and transaction restored.");
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
    
        if (lastAction.getReservation() != null && lastAction.getTransaction() != null) {
            if (lastAction.getActionType() == UndoAction.ActionType.ADD) {
                // Redo reservation addition
                CSVLoader.getReservations().add(lastAction.getReservation());
                TransactionsHandler.getAllTransactions().add(lastAction.getTransaction());
    
                Users client = lastAction.getTransaction().getClient();
                Users owner = lastAction.getTransaction().getOwner();
                double credits = lastAction.getTransaction().getCreditAmount();
    
                client.setCredits(client.getCredits() - credits);
                owner.setCredits(owner.getCredits() + credits);
    
                System.out.println("Reservation and transaction redone.");
            } else if (lastAction.getActionType() == UndoAction.ActionType.DELETE) {
                // Redo reservation deletion
                CSVLoader.getReservations().remove(lastAction.getReservation());
                TransactionsHandler.getAllTransactions().remove(lastAction.getTransaction());
    
                Users client = lastAction.getTransaction().getClient();
                Users owner = lastAction.getTransaction().getOwner();
                double credits = lastAction.getTransaction().getCreditAmount();
    
                client.setCredits(client.getCredits() + credits);
                owner.setCredits(owner.getCredits() - credits);
    
                System.out.println("Reservation and transaction removal redone.");
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
    

public static void recordReservationAddition(Reservations reservation, Transaction transaction) {
    undoStack.push(new UndoAction(reservation, transaction, UndoAction.ActionType.ADD));
    redoStack.clear();
}

public static void recordReservationDeletion(Reservations reservation, Transaction transaction) {
    undoStack.push(new UndoAction(reservation, transaction, UndoAction.ActionType.DELETE));
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