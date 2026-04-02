package yourtable;

public class Restaurant {
    private String restaurantName;
    private int numTables;
    private int numReservations;
    // Aggregation relationn
    private Table[] tableList = new Table[50];
    // Composition relation
    private Reservation[] reservations = new Reservation[50];

    public Restaurant(String restaurantName, int numTables) {
        this.restaurantName = restaurantName;
        this.numTables = 0;
        this.numReservations = 0;
    }

    // adds a table to the restaurant
    public boolean addTable(Table t) {
        if (numTables < tableList.length) {
            tableList[numTables++] = t;
            return true;
        }
        return false;
    }

    // adds a reservation to the restaurant
    public boolean addReservation(Reservation r) {
        if (numReservations < reservations.length) {
            reservations[numReservations++] = r;
            return true;
        }
        return false;
    }

    // cancels a reservation by ID and table number
    public boolean cancelReservation(String id, int tableNo) {
        Reservation r = searchReservation(id, tableNo);
        if (r != null) {
            r.checkOut();
            return true;
        }
        return false;
    }

    // searches for a reservation by ID and table number
    public Reservation searchReservation(String id, int tableNo) {
        for (int i = 0; i < numReservations; i++) {
            if (reservations[i].getId().equals(id)
                    && reservations[i].getTableNo() == tableNo)
                return reservations[i];
        }
        return null;
    }

    // searches for a table by table number
    public Table searchTable(int tableNo) {
        for (int i = 0; i < numTables; i++) {
            if (tableList[i].getTableNo() == tableNo)
                return tableList[i];
        }
        return null;
    }

    // returns all Inside tables — as per UML
    public Inside[] getInsideTables() {
        int count = 0;
        for (int i = 0; i < numTables; i++)
            if (tableList[i] instanceof Inside) count++;
        Inside[] result = new Inside[count];
        int j = 0;
        for (int i = 0; i < numTables; i++)
            if (tableList[i] instanceof Inside)
                result[j++] = (Inside) tableList[i];
        return result;
    }

    // returns all Outside tables — as per UML
    public Outside[] getOutsideTables() {
        int count = 0;
        for (int i = 0; i < numTables; i++)
            if (tableList[i] instanceof Outside) count++;
        Outside[] result = new Outside[count];
        int j = 0;
        for (int i = 0; i < numTables; i++)
            if (tableList[i] instanceof Outside)
                result[j++] = (Outside) tableList[i];
        return result;
    }

    // returns all available tables — as per UML
    public Table[] getAllAvailable() {
        int count = 0;
        for (int i = 0; i < numTables; i++)
            if (tableList[i].isAvailable()) count++;
        Table[] result = new Table[count];
        int j = 0;
        for (int i = 0; i < numTables; i++)
            if (tableList[i].isAvailable())
                result[j++] = tableList[i];
        return result;
    }

    // deletes a table by table number — as per UML
    public boolean deleteTable(int tableNo) {
        for (int i = 0; i < numTables; i++) {
            if (tableList[i].getTableNo() == tableNo) {
                for (int j = i; j < numTables - 1; j++)
                    tableList[j] = tableList[j + 1];
                tableList[--numTables] = null;
                return true;
            }
        }
        return false;
    }

    // returns available tables filtered by type (1=Outside, 2=Inside, 3=Partitions)
    public Table[] getAvailableByType(int preference) {
        int count = 0;
        for (int i = 0; i < numTables; i++) {
            Table t = tableList[i];
            if (!t.isAvailable()) continue;
            if (preference == 3 && t instanceof Partitions) count++;
            if (preference == 2 && t instanceof Inside && !(t instanceof Partitions)) count++;
            if (preference == 1 && t instanceof Outside) count++;
        }
        Table[] result = new Table[count];
        int j = 0;
        for (int i = 0; i < numTables; i++) {
            Table t = tableList[i];
            if (!t.isAvailable()) continue;
            if (preference == 3 && t instanceof Partitions) result[j++] = t;
            if (preference == 2 && t instanceof Inside && !(t instanceof Partitions)) result[j++] = t;
            if (preference == 1 && t instanceof Outside) result[j++] = t;
        }
        return result;
    }

    // finds a suitable table based on preference, floor, and number of guests
    public Table findSuitableTable(int preference, int floorPref, int guests) {
        for (int i = 0; i < numTables; i++) {
            Table t = tableList[i];
            if (!t.isAvailable()) continue;
            if (t.getCapacity() < guests) continue; // skip if table can't fit guests

            if (preference == 1 && t instanceof Outside)
                return t;
            if (preference == 3 && t instanceof Partitions)
                if (floorPref == -1 || ((Partitions) t).getFloorNumber() == floorPref)
                    return t;
            if (preference == 2 && t instanceof Inside && !(t instanceof Partitions))
                if (floorPref == -1 || ((Inside) t).getFloorNumber() == floorPref)
                    return t;
        }
        return null;
    }

    // displays all tables in the restaurant
    public void displayTables() {
        System.out.println("\n=== Tables in " + restaurantName + " ===");
        for (int i = 0; i < numTables; i++)
            System.out.println(tableList[i]);
    }

    // Getters
    public String getRestaurantName() { return restaurantName; }
    public int getNumTables() { return numTables; }
    public int getNumReservations() { return numReservations; }
}
