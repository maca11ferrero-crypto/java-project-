package yourtable;

// Outside implements Services — satisfies the requirement of
// the interface being implemented by two unrelated classes (Outside & Partitions)
public class Outside extends Table implements Services {
    private boolean windowView;

    // Constructor updated to include capacity
    public Outside(int tableNo, boolean windowView, double price, int capacity) {
        super(tableNo, price, capacity);
        this.windowView = windowView;
    }

    public double calculatePrice() {
        double total = price;
        if (windowView) total += 20; // extra charge for window view
        return total;
    }

    // Implementation of Services interface
    // returns 1 if window view is available, 0 if not
    public int getServices() {
        return windowView ? 1 : 0;
    }

    // Getters and Setters
    public boolean isWindowView() { return windowView; }
    public void setWindowView(boolean windowView) { this.windowView = windowView; }

    public String toString() {
        return "Outside Table -> " + super.toString() +
               " WindowView= " + windowView;
    }
}