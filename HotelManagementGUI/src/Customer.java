import java.util.Date;
public class Customer {
    private String name;
    private String contactDetails;
    private Date checkInDate;
    private Date checkOutDate;
    private int roomNumber;

    public Customer(String name, String contactDetails) {
        this.name = name;
        this.contactDetails = contactDetails;
    }

    public void checkIn(int roomNumber, Date checkInDate) {
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
    }

    public void checkOut(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public String getName() {
        return name;
    }

    public String getContactDetails() {
        return contactDetails;
    }
}
