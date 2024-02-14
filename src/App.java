import business.UserManager;
import entity.User;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;
import view.ReservationView;

public class App {
    public static void main(String[] args) {
        //LoginView loginView = new LoginView();
        UserManager userManager = new UserManager();
        EmployeeView employeeView = new EmployeeView(userManager.findByLogin("Fatih Fedai","aa"));
    }
}