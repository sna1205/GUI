import java.util.Date;
public class Room {
    private int roomNumber;
    private String type;
    private double price;
    private boolean availability;
    private Customer customer;

    // Constructor initializes a room with a room number, type, and price
    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.availability = true;
        this.customer = null;
    }

    // Gets the room number
    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable(Date startDate, Date endDate) {
        if (availability) {
            // Check if the room is available for the given date range
            if (customer == null) {
                // If there is no customer, the room is available
                return true;
            } else {
                // If there is a customer, check if the date range overlaps with the existing booking
                boolean isBeforeExistingBooking = endDate.before(customer.getCheckInDate()) || endDate.equals(customer.getCheckInDate());
                boolean isAfterExistingBooking = startDate.after(customer.getCheckOutDate()) || startDate.equals(customer.getCheckOutDate());

                return isBeforeExistingBooking || isAfterExistingBooking;
            }
        }
        return false;
    }

    public void bookRoom(Customer customer, Date startDate, Date endDate) {
        if (isAvailable(startDate, endDate)) {
            // Set the customer and update availability
            this.customer = customer;
            this.availability = false;

            // Update Customer check-in and check-out dates
            customer.checkIn(this.roomNumber, startDate);
            customer.checkOut(endDate);


            System.out.println("Room booked successfully!");
        } else {
            System.out.println("Room not available for the specified date range.");
        }
    }

    public void checkOut() {
        if (isOccupied()) {
            // Perform any necessary actions related to checking out a customer
            this.customer = null;
            this.availability = true;

            System.out.println("Room checked out successfully!");
        } else {
            System.out.println("Room is not occupied.");
        }
    }

    public boolean isOccupied() {

        return customer != null;
    }
    public Customer getCustomer() {

        return customer;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

}
