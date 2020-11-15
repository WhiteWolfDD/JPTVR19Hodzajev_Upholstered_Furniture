package JPTVR19Hodzajev_Upholstered_Furniture;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import entity.User;
import java.util.ArrayList;
import java.util.List;

import security.SecManager;
import tools.StorageManager;
import ui.UserInterface;

public class App {
    
    public static enum storageFile{PRODUCTS,CUSTOMERS,PURCHASES,USERS};
    
    private List<Product> listProducts = new ArrayList<>();
    private List<Customer> listCustomers = new ArrayList<>();
    private List<Purchase> listPurchases = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();
    
    private StorageManager storageManager = new StorageManager();
    
    public static User loggedInUser;
    
    public App() {
        List<Product> loadedProducts = storageManager.load(App.storageFile.PRODUCTS.toString());
        if(loadedProducts != null){
            listProducts = loadedProducts;
        }
        List<Customer> loadedCustomers = storageManager.load(App.storageFile.CUSTOMERS.toString());
        if(loadedCustomers != null){
            listCustomers = loadedCustomers;
        }
        List<Purchase> loadedPurchases = storageManager.load(App.storageFile.PURCHASES.toString());
        if(loadedPurchases != null){
            listPurchases = loadedPurchases;
        }
        List<User> loadedUser = storageManager.load(App.storageFile.USERS.toString());
        if(loadedUser != null){
            listUsers = loadedUser;
        }
    }
    
    public void run() {
        System.out.println(" === Добро пожаловать в Up.Fur. Shop! === ");
        SecManager secManager = new SecManager();
        App.loggedInUser = secManager.checkInlogin(listUsers, listCustomers);
        UserInterface userInterface = new UserInterface();
        if(SecManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            userInterface.printManagerUI(listUsers, listCustomers, listProducts, listPurchases);
        }else if(SecManager.role.CUSTOMER.toString().equals(App.loggedInUser.getRole())){
            userInterface.printCustomerUI(listUsers, listCustomers, listProducts, listPurchases);
        }
    }
    
}
