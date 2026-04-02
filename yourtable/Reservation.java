package yourtable;

public class Reservation {
    private String fullName;
    private String mobile;
    private String id;
    private Table table; // Aggregation: Table exists independently from Reservation
    private int numOfHours;
    private double totalPrice;

    // Main constructor
    public Reservation(String fullName, String mobile, String id) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.id = id;
    }

    // Copy constructor — as per UML
    public Reservation(Reservation obj) {
        this.fullName = obj.fullName;
        this.mobile = obj.mobile;
        this.id = obj.id;
        this.table = obj.table;
        this.numOfHours = obj.numOfHours;
        this.totalPrice = obj.totalPrice;
    }

    // checkIn replaces reserve — as per UML
    // sets the table, calculates total price, marks table as unavailable
    public void checkIn(Table table, int hours) {
        this.table = table;
        this.numOfHours = hours;
        this.totalPrice = table.calculatePrice() * hours;
        table.setAvailable(false);
    }

    // checkOut replaces cancel — as per UML
    // marks the table as available again
    public void checkOut() {
        if (table != null)
            table.setAvailable(true);
    }

    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Table getTable() { return table; }
    public int getNumOfHours() { return numOfHours; }
    public double getTotalPrice() { return totalPrice; }
    public int getTableNo() { return table.getTableNo(); }

    public String toString() {
        return "Reservation -> Name= " + fullName +
               " | ID= " + id +
               " | Mobile= " + mobile +
               " | TableNo= " + table.getTableNo() +
               " | Hours= " + numOfHours +
               " | TotalPrice= " + totalPrice;
    }
}