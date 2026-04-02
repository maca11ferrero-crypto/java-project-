package yourtable;

// Partitions extends Inside (two-level inheritance: Table -> Inside -> Partitions)
// Partitions implements Services — second unrelated class to implement the interface
public class Partitions extends Inside implements Services {
    private int numberOfPartitions;

    // Constructor updated to include capacity
    public Partitions(int tableNo, int extraChair, int floorNumber,
                      int numberOfPartitions, double price, int capacity) {
        super(tableNo, extraChair, floorNumber, price, capacity);
        this.numberOfPartitions = numberOfPartitions;
    }

    public double calculatePrice() {
        return super.calculatePrice() + (numberOfPartitions * 5); // each partition costs 5
    }

    // Implementation of Services interface
    // returns the number of partitions as the service count
    public int getServices() {
        return numberOfPartitions;
    }

    // Getters and Setters
    public int getNumberOfPartitions() { return numberOfPartitions; }
    public void setNumberOfPartitions(int n) { this.numberOfPartitions = n; }

    public String toString() {
        return "Partition Table -> " + super.toString() +
               " Partitions= " + numberOfPartitions;
    }
}