public class IdGenerator {
    private static int idNum;

    // Method to generate and return a new idNum
    public static int generateId() {
        idNum = (int) (Math.random() * 10000);
        return idNum;
    }

    // Method to retrieve the current idNum
    public static int getId() {
        return idNum;
    }
}
