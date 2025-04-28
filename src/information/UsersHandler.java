package information;

import CSVHandlers.CSVLoader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UsersHandler {
    
    private static List<Users> users = CSVLoader.loadUsers();

    public static List<Users> getUsers() {
        return users;
    }

    public static void addUser() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");

        String name = scanner.nextLine().trim();

        while (!name.matches("^[A-Za-z ]+$")) {
            System.out.print("Invalid name. Only letters and spaces. Try again: ");
            name = scanner.nextLine().trim();
        }
        
        System.out.print("Enter student number: ");
        String idStr = scanner.nextLine().trim();

        while (!idStr.matches("^(80[12][0-4]|840|84[2-6])\\d{5}$")) {
            System.out.print("Invalid ID. Must start with 801,802,803,804 (next two digits <25) or 840-846, and 9 digits total. Try again: ");
            idStr = scanner.nextLine().trim();
        }
        int studentId = Integer.parseInt(idStr);
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        while (!email.matches("^[A-Za-z0-9._%+-]+@upr\\.edu$")) {
            System.out.print("Invalid email. Must end with @upr.edu and valid characters. Try again: ");
            email = scanner.nextLine().trim();
        }
       
        System.out.print("Enter phone number: ");
        String phoneStr = scanner.nextLine().trim();

        while (!phoneStr.matches("^\\d{10}$")) {
            System.out.print("Invalid phone. Must be 10 digits. Try again: ");
            phoneStr = scanner.nextLine().trim();
        }

        String phone = phoneStr;
        System.out.print("Select user type (1=OWNER, 2=CLIENT, 3=BOTH): ");
        int typeOpt;
        try {
            typeOpt = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            typeOpt = 2;
        }

        Users.OWNER_OR_CLIENT type;
        switch (typeOpt) {
            case 1: type = Users.OWNER_OR_CLIENT.OWNER; break;
            case 3: type = Users.OWNER_OR_CLIENT.OWNERANDCLIENT; break;
            default: type = Users.OWNER_OR_CLIENT.CLIENT;
        }
        
        System.out.print("Enter starting credits (or press Enter for 0): ");
        String creditsStr = scanner.nextLine().trim();
        int credits = creditsStr.isEmpty() ? 0 : Integer.parseInt(creditsStr);
        int vehiclesOwned = 0;
        

        Users newUser = new Users(name, studentId, email, phone, type, credits, vehiclesOwned);
        UndoStack.recordUserAddition(newUser);
        users.add(newUser);
        System.out.println("User added successfully.");
    }

    
    public static void removeUser(int id) {
        Users userToRemove = getUserById(id);
        boolean removed = users.removeIf(u -> u.getstudentid() == id);
        if (removed) {
            UndoStack.recordUserDeletion(userToRemove);
            System.out.println("User removed.");
        } else {
            System.out.println("No user found with ID " + id);
        }
    }




    public static void modifyUser(int id) {
        Users u = users.stream()
                       .filter(x -> x.getstudentid() == id)
                       .findFirst().orElse(null);
        if (u == null) {
            System.out.println("No user found with ID " + id);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new email (blank to skip): ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty() && email.matches("^[A-Za-z0-9._%+-]+@upr\\.edu$")) {
            u.setstudentemail(email);
        }
        System.out.print("Enter new phone (10 digits or blank): ");
        String phone = scanner.nextLine().trim();
        if (!phone.isEmpty() && phone.matches("^\\d{10}$")) {
            u.setstudentphone(phone);
        }
        System.out.print("Enter new credits (blank to skip): ");
        String cred = scanner.nextLine().trim();
        if (!cred.isEmpty()) {
            u.setCredits(Integer.parseInt(cred));
        }
        System.out.println("User updated.");
    }

    public static Users getUserById(int id) {
        return users.stream()
            .filter(u -> u.getstudentid() == id)
            .findFirst()
            .orElse(null);
    }


    public static void findById(int id) {
        List<Users> found = users.stream()
            .filter(u -> u.getstudentid() == id)
            .collect(Collectors.toList());
        printFound(found);
    }

    public static void findByPhone(String phone) {
        List<Users> found = users.stream()
            .filter(u -> String.format("%010d", u.getstudentphone()).equals(phone))
            .collect(Collectors.toList());
        printFound(found);
    }

    public static void findByName(String name) {
        List<Users> found = users.stream()
            .filter(u -> u.getstudentname().equalsIgnoreCase(name))
            .collect(Collectors.toList());
        printFound(found);
    }

    public static void findByType(Users.OWNER_OR_CLIENT type) {
        List<Users> found = users.stream()
            .filter(u -> u.getTipo() == type)
            .collect(Collectors.toList());
        printFound(found);
    }

    private static void printFound(List<Users> list) {
        if (list.isEmpty()) System.out.println("No users found.");
        else list.forEach(System.out::println);
    }
}