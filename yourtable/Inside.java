package yourtable;

public class Inside extends Table {
    protected int extraChair; // number of extra chairs (int not boolean as per UML)
    protected int floorNumber;

    // Constructor updated to include capacity
    public Inside(int tableNo, int extraChair, int floorNumber, double price, int capacity) {
        super(tableNo, price, capacity);
        this.extraChair = extraChair;
        this.floorNumber = floorNumber;
    }

    public double calculatePrice() {
        return price + (extraChair * 10); // each extra chair costs 10
    }

    // Getters and Setters
    public int getExtraChair() { return extraChair; }
    public void setExtraChair(int extraChair) { this.extraChair = extraChair; }
    public int getFloorNumber() { return floorNumber; }
    public void setFloorNumber(int floorNumber) { this.floorNumber = floorNumber; }

    public String toString() {
        return "Inside Table -> " + super.toString() +
               " ExtraChair= " + extraChair +
               " Floor= " + floorNumber;
    }
}