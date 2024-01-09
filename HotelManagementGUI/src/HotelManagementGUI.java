import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class HotelManagementGUI {
    private Hotel hotel;
    private JPanel inputPanel;
    private String userID;

    public HotelManagementGUI(String userID) {
        this.hotel = hotel;
        this.userID = userID;
        this.hotel = new Hotel("My Hotel", "123 Main St", "123-456-7890");
        createAndShowGUI();
    }

    private void createAndShowGUI() {

        JFrame frame = new JFrame("Hotel Management System");

        JLabel text = new JLabel("Welcome To Booking System");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1000, 10));

        JButton addRoomButton = new JButton("Add Room");
        JButton removeRoomButton = new JButton("Remove Room");
        JButton checkAvailabilityButton = new JButton("Check Availability");
        JButton bookRoomButton = new JButton("Book Room");
        JButton checkOutButton = new JButton("Check Out");
        JButton viewAllRoomsButton = new JButton("View All Rooms");
        JButton viewBookingButton = new JButton("View Booking Room");

        Dimension buttonSize = new Dimension(150, 30);
        addRoomButton.setPreferredSize(buttonSize);
        removeRoomButton.setPreferredSize(buttonSize);
        checkAvailabilityButton.setPreferredSize(buttonSize);
        bookRoomButton.setPreferredSize(buttonSize);
        checkOutButton.setPreferredSize(buttonSize);
        viewAllRoomsButton.setPreferredSize(buttonSize);

        inputPanel = new JPanel(new GridLayout(0, 2));

        // View All Rooms Button
        viewAllRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve all rooms from the hotel
                ArrayList<Room> allRooms = hotel.getRooms();

                if (!allRooms.isEmpty()) {
                    // Prepare data for displaying in a table
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String[][] data = new String[allRooms.size()][4];
                    int i = 0;

                    // Populate the data array with room details
                    for (Room room : allRooms) {
                        data[i][0] = String.valueOf(room.getRoomNumber());
                        data[i][1] = room.getType();
                        data[i][2] = String.valueOf(room.getPrice());
                        data[i][3] = room.isAvailable(new Date(), new Date()) ? "Available" : "Booked";

                        i++;
                    }

                    String[] columnNames = {"Room Number", "Type", "Price", "Availability"};

                    // Create a table and display it in a JOptionPane
                    JTable table = new JTable(data, columnNames);
                    JScrollPane scrollPane = new JScrollPane(table);

                    JOptionPane.showMessageDialog(null, scrollPane, "All Rooms Information", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No rooms available.");
                }
            }
        });

        // Add Room Button
        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display input fields for adding a room
                JTextField roomNumberField = new JTextField();
                JTextField roomTypeField = new JTextField();
                JTextField roomPriceField = new JTextField();

                JPanel inputPanel = new JPanel(new GridLayout(0, 2));
                inputPanel.add(new JLabel("Room Number:"));
                inputPanel.add(roomNumberField);
                inputPanel.add(new JLabel("Room Type:"));
                inputPanel.add(roomTypeField);
                inputPanel.add(new JLabel("Room Price:"));
                inputPanel.add(roomPriceField);

                // Show input dialog and handle user input
                int result = JOptionPane.showConfirmDialog(null, inputPanel,
                        "Enter Room Information", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        // Parse input and create a new room
                        int roomNumber = Integer.parseInt(roomNumberField.getText());
                        String type = roomTypeField.getText();
                        double price = Double.parseDouble(roomPriceField.getText());

                        Room newRoom = new Room(roomNumber, type, price);
                        hotel.addRoom(newRoom);
                        JOptionPane.showMessageDialog(null, "Room added successfully!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
                    }
                }
            }
        });

        // Remove Room Button
        removeRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display input fields for removing a room
                inputPanel.removeAll();
                inputPanel.add(new JLabel("Room Number to remove:"));
                JTextField roomNumberField = new JTextField();
                inputPanel.add(roomNumberField);

                // Show input dialog and handle user input
                int result = JOptionPane.showConfirmDialog(null, inputPanel,
                        "Enter Room Information", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        // Parse input, find and remove the specified room
                        int roomNumber = Integer.parseInt(roomNumberField.getText());
                        Room roomToRemove = hotel.findRoomByNumber(roomNumber);

                        if (roomToRemove != null) {
                            hotel.removeRoom(roomToRemove);
                            JOptionPane.showMessageDialog(null, "Room removed successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Room not found!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
                    }
                }
            }
        });

        // Check Availability Button
        checkAvailabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display input fields for checking room availability
                inputPanel.removeAll();
                inputPanel.add(new JLabel("Enter Room Number:"));
                JTextField roomNumberField = new JTextField();
                inputPanel.add(roomNumberField);
                inputPanel.add(new JLabel("Enter Start Date (yyyy-MM-dd):"));
                JTextField startDateField = new JTextField();
                inputPanel.add(startDateField);
                inputPanel.add(new JLabel("Enter End Date (yyyy-MM-dd):"));
                JTextField endDateField = new JTextField();
                inputPanel.add(endDateField);

                // Show input dialog and handle user input
                int result = JOptionPane.showConfirmDialog(null, inputPanel,
                        "Enter Room Information", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        // Parse input, check room availability, and show the result
                        int roomNumber = Integer.parseInt(roomNumberField.getText());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date startDate = dateFormat.parse(startDateField.getText());
                        Date endDate = dateFormat.parse(endDateField.getText());

                        boolean isAvailable = hotel.checkAvailability(roomNumber, startDate, endDate);

                        if (isAvailable) {
                            JOptionPane.showMessageDialog(null, "Room is available for the given date range.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Room is not available for the given date range.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input or date format!");
                    }
                }
            }
        });

        // Book Room Button
        bookRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display input fields for booking a room with date options
                inputPanel.removeAll();
                inputPanel.add(new JLabel("Enter Room Number:"));
                JTextField roomNumberField = new JTextField();
                inputPanel.add(roomNumberField);
                inputPanel.add(new JLabel("Enter Customer Name:"));
                JTextField customerNameField = new JTextField();
                inputPanel.add(customerNameField);
                inputPanel.add(new JLabel("Enter Customer Contact Details:"));
                JTextField contactDetailsField = new JTextField();
                inputPanel.add(contactDetailsField);
                inputPanel.add(new JLabel("Enter Check-in Date (yyyy-MM-dd):"));
                JTextField checkInDateField = new JTextField();
                inputPanel.add(checkInDateField);
                inputPanel.add(new JLabel("Enter Check-out Date (yyyy-MM-dd):"));
                JTextField checkOutDateField = new JTextField();
                inputPanel.add(checkOutDateField);

                // Show input dialog and handle user input
                int result = JOptionPane.showConfirmDialog(null, inputPanel,
                        "Enter Room Information", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        // Parse input and book the room for the specified date range
                        int roomNumber = Integer.parseInt(roomNumberField.getText());
                        String customerName = customerNameField.getText();
                        String contactDetails = contactDetailsField.getText();
                        Customer customer = new Customer(customerName, contactDetails);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date startDate = dateFormat.parse(checkInDateField.getText());
                        Date endDate = dateFormat.parse(checkOutDateField.getText());

                        hotel.bookRoom(roomNumber, customer, startDate, endDate);
                        JOptionPane.showMessageDialog(null, "Room booked successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input or date format!");
                    }
                }
            }
        });


        // Check Out Button
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display input fields for checking out
                inputPanel.removeAll();
                inputPanel.add(new JLabel("Enter Room Number to check out:"));
                JTextField roomNumberField = new JTextField();
                inputPanel.add(roomNumberField);

                // Show input dialog and handle user input
                int result = JOptionPane.showConfirmDialog(null, inputPanel,
                        "Enter Room Information", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        // Parse input and check out the specified room
                        int roomNumber = Integer.parseInt(roomNumberField.getText());
                        hotel.checkOut(roomNumber);
                        JOptionPane.showMessageDialog(null, "Room checked out successfully!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
                    }
                }
            }
        });

        // View Booking Button
        viewBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Improved logic for viewing all booked rooms information
                ArrayList<Room> bookedRooms = new ArrayList<>();

                for (Room room : hotel.getRooms()) {
                    if (room.isOccupied() && room.getCustomer() != null) {
                        bookedRooms.add(room);
                    }
                }

                if (!bookedRooms.isEmpty()) {
                    // Prepare data for displaying booked room information
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String[][] data = new String[bookedRooms.size()][6];
                    int i = 0;

                    // Populate the data array with booked room details
                    for (Room room : bookedRooms) {
                        Customer customer = room.getCustomer();

                        data[i][0] = String.valueOf(room.getRoomNumber());
                        data[i][1] = String.valueOf(!room.isOccupied());
                        data[i][2] = customer.getCheckInDate() != null ? dateFormat.format(customer.getCheckInDate()) : "Not available";
                        data[i][3] = customer.getCheckOutDate() != null ? dateFormat.format(customer.getCheckOutDate()) : "Not available";
                        data[i][4] = customer.getName();
                        data[i][5] = customer.getContactDetails();

                        i++;
                    }

                    String[] columnNames = {"Room Number", "Availability", "Check-in Date", "Check-out Date", "Customer Name", "Contact Details"};

                    // Create a table and display it in a JOptionPane
                    JTable table = new JTable(data, columnNames);
                    JScrollPane scrollPane = new JScrollPane(table);

                    JOptionPane.showMessageDialog(null, scrollPane, "Booked Rooms Information", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No rooms are currently booked.");
                }
            }
        });


        panel.add(text);
        panel.add(viewAllRoomsButton);
        panel.add(viewBookingButton);
        panel.add(addRoomButton);
        panel.add(removeRoomButton);
        panel.add(checkAvailabilityButton);
        panel.add(bookRoomButton);
        panel.add(checkOutButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.add(panel);
        frame.setVisible(true);
    }

}
