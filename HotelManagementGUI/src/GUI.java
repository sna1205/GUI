import javax.swing.*;
public class GUI {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("My Hotel", "123 Main St", "123-456-7890");
        IDandPasswords idandPasswords = new IDandPasswords();

        SwingUtilities.invokeLater(() -> {
            LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
        });
    }
}
