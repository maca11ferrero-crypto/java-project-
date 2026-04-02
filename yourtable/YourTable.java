package yourtable;
import java.util.Scanner;

public class YourTable {
    static Scanner input = new Scanner(System.in);
    static Restaurant restaurant = new Restaurant("YourTable Restaurant", 50);

    public static void main(String[] args) {
        // Two Outside tables with different window view and capacity
        restaurant.addTable(new Outside(1, true, 50, 4));
        restaurant.addTable(new Outside(2, false, 50, 6));

        // Two Inside tables on different floors with different capacity
        restaurant.addTable(new Inside(3, 1, 1, 40, 3));
        restaurant.addTable(new Inside(4, 0, 2, 40, 5));

        // Two Partition tables on different floors with different partitions and capacity
        restaurant.addTable(new Partitions(5, 1, 1, 2, 60, 8));
        restaurant.addTable(new Partitions(6, 0, 2, 3, 60, 10));

        int choice;
        do {
            System.out.println("\n=== YourTable Menu ===");
            System.out.println("1 Add Reservation");
            System.out.println("2 Search Reservation");
            System.out.println("3 Cancel Reservation");
            System.out.println("4 Display Tables");
            System.out.println("5 Exit");
            choice = input.nextInt();
            switch (choice) {
                case 1: addNewReservation(); break;
                case 2: searchReservation(); break;
                case 3: cancelReservation(); break;
                case 4: viewTables(); break;
            }
        } while (choice != 5);
    }

    static void addNewReservation() {
        System.out.println("Enter Name:");
        String name = input.next();
        System.out.println("Enter Mobile:");
        String mobile = input.next();
        System.out.println("Enter ID:");
        String id = input.next();

        // ask user for number of guests to filter tables by capacity
        System.out.println("Enter number of guests:");
        int guests = input.nextInt();

        System.out.println("\nWhere do you prefer to sit?");
        System.out.println("1 - Outside (Window View)");
        System.out.println("2 - Inside");
        System.out.println("3 - Inside with Partitions");
        int preference = input.nextInt();

        // show available Outside tables with capacity check
        if (preference == 1) {
            System.out.println("\nAvailable Outside Tables:");
            boolean found = false;
            for (Table t : restaurant.getAvailableByType(1)) {
                if (t.getCapacity() < guests) continue; // skip if capacity not enough
                Outside o = (Outside) t;
                System.out.println("  - Table No: " + t.getTableNo() +
                                   " | Window View: " + o.isWindowView() +
                                   " | Capacity: " + t.getCapacity() +
                                   " | Price/hr: " + t.calculatePrice());
                found = true;
            }
            if (!found) {
                System.out.println("Sorry, no outside tables available for " + guests + " guests.");
                return;
            }
            System.out.println("Enter Table No:");
            int tableNo = input.nextInt();
            System.out.println("Enter Hours:");
            int hours = input.nextInt();
            Table t = restaurant.searchTable(tableNo);
            if (t != null && t.isAvailable() && t.getCapacity() >= guests) {
                System.out.println("Price per hour: " + t.calculatePrice());
                System.out.println("Total: " + (t.calculatePrice() * hours));
                Reservation r = new Reservation(name, mobile, id);
                r.checkIn(t, hours);
                restaurant.addReservation(r);
                System.out.println("Reservation Added Successfully!");
            } else {
                System.out.println("Table not available or not enough capacity.");
            }
            return;
        }

        // show available Inside or Partition tables with capacity and floor info
        int floorPref = -1;
        if (preference == 2 || preference == 3) {
            System.out.println("\nAvailable Tables:");
            boolean found = false;
            for (Table t : restaurant.getAvailableByType(preference)) {
                if (t.getCapacity() < guests) continue; // skip if capacity not enough
                System.out.println("  - Floor: " + ((Inside) t).getFloorNumber() +
                                   " | Table No: " + t.getTableNo() +
                                   " | Capacity: " + t.getCapacity() +
                                   " | Price/hr: " + t.calculatePrice());
                found = true;
            }
            if (!found) {
                System.out.println("Sorry, no tables available for " + guests + " guests.");
                return;
            }
            System.out.println("Enter preferred floor number:");
            floorPref = input.nextInt();
        }

        System.out.println("Enter Hours:");
        int hours = input.nextInt();

        // find suitable table based on preference, floor, and guests
        Table t = restaurant.findSuitableTable(preference, floorPref, guests);

        if (t != null) {
            System.out.println("\nTable found: " + t);
            System.out.println("Price per hour: " + t.calculatePrice());
            System.out.println("Total: " + (t.calculatePrice() * hours));
            Reservation r = new Reservation(name, mobile, id);
            r.checkIn(t, hours);
            restaurant.addReservation(r);
            System.out.println("Reservation Added Successfully!");
        } else {
            System.out.println("Sorry, no suitable table available for " + guests + " guests.");
        }
    }

    // displays all tables
    static void viewTables() {
        restaurant.displayTables();
    }

    // searches for a reservation by ID and table number
    static void searchReservation() {
        System.out.println("Enter ID:");
        String id = input.next();
        System.out.println("Enter Table No:");
        int tableNo = input.nextInt();
        Reservation r = restaurant.searchReservation(id, tableNo);
        if (r != null)
            System.out.println(r);
        else
            System.out.println("Not Found");
    }

    // cancels a reservation by ID and table number
    static void cancelReservation() {
        System.out.println("Enter ID:");
        String id = input.next();
        System.out.println("Enter Table No:");
        int tableNo = input.nextInt();
        if (restaurant.cancelReservation(id, tableNo))
            System.out.println("Cancelled Successfully");
        else
            System.out.println("Not Found");
    }

    // recursive method to print all Inside tables — as per UML
    static void printAllInsideRecursion(Inside[] list, int index) {
        if (index >= list.length) return; // base case
        System.out.println(list[index]);
        printAllInsideRecursion(list, index + 1); // recursive call
    }
}