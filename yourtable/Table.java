package yourtable;

public abstract class Table {
    private int tableNo;
    private boolean available;
    protected double price;
    private int capacity; // max number of guests the table can hold

    // Constructor updated to include capacity
    public Table(int tableNo, double price, int capacity) {
        this.tableNo = tableNo;
        this.price = price;
        this.capacity = capacity;
        this.available = true;
    }

    public abstract double calculatePrice();

    // Getters and Setters
    public int getTableNo() { return tableNo; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getCapacity() { return capacity; } // getter for capacity
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String toString() {
        return "TableNo= " + tableNo +
               " Available= " + available +
               " BasePrice= " + price +
               " Capacity= " + capacity;
    }
}