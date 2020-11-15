package security;

import entity.Customer;
import entity.User;
import java.util.List;
import java.util.Scanner;
import JPTVR19Hodzajev_Upholstered_Furniture.App;
import tools.CustomerManager;
import tools.StorageManager;
import tools.UserManager;

public class SecManager {
    private Scanner scan = new Scanner(System.in);
    private UserManager userManager = new UserManager();
    private CustomerManager customerManager = new CustomerManager();
    private StorageManager storageManager = new StorageManager();
    
    public static enum role {CUSTOMER, MANAGER};
    
    public User checkInlogin(List<User> listUsers, List<Customer> listCustomers){
        do {            
           System.out.println("Ваш выбор:");
           System.out.println("0. Закрыть программу");
           System.out.println("1. Регистрация");
           System.out.println("2. Вход в систему");
           System.out.print("Введите номер задачи: ");
           String var = scan.nextLine(); 
            switch (var) {
                case "0":
                    System.out.println("Пока!");
                    System.exit(0);
                    break;
                case "1":
                    User user = userManager.createUser();
                    userManager.addUserToArray(user, listUsers);
                    storageManager.save(listUsers, App.storageFile.USERS.toString());
                    customerManager.addCustomerToArray(user.getCustomer(), listCustomers);
                    storageManager.save(listCustomers, App.storageFile.CUSTOMERS.toString());
                    break;
                case "2":
                    User checkInUser = userManager.getCheckInUser(listUsers);
                    if(checkInUser == null) break;
                    return checkInUser;
                default:
                    System.out.println("Нет такой задачи.");
            }
        } while (true);
 
    }
}
