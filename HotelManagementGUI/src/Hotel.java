import java.util.ArrayList;
import java.util.Date;
public class Hotel {
    private String name;
    private String address;
    private String contactDetails;
    private ArrayList<Room> rooms;

    public Hotel(String name, String address, String contactDetails) {
        this.name = name;
        this.address = address;
        this.contactDetails = contactDetails;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    // Checks the availability of a room for a given date range
    public boolean checkAvailability(int roomNumber, Date startDate, Date endDate) {
        // Implementation to check room availability for the given date range
        Room room = findRoomByNumber(roomNumber);
        if (room != null) {
            return room.isAvailable(startDate, endDate);
        }
        return false;
    }

    // Books a room for a customer for a specified date range
    public void bookRoom(int roomNumber, Customer customer, Date startDate, Date endDate) {
        // Implementation to book a room for a customer and update its availability status
        Room room = findRoomByNumber(roomNumber);
        if (room != null && room.isAvailable(startDate, endDate)) {
            room.bookRoom(customer, startDate, endDate);
        } else {
            System.out.println("Room not available for the specified date range.");
        }
    }

    // Checks out a customer from a room
    public void checkOut(int roomNumber) {
        Room room = findRoomByNumber(roomNumber);
        if (room != null && room.isOccupied()) {
            room.checkOut();
        } else {
            System.out.println("Room is not occupied or does not exist.");
        }
    }

    // Finds a room by its room number
    public Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    // Gets a list of all rooms
    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
