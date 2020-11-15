package ui;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import entity.User;
import java.util.List;
import java.util.Scanner;
import tools.CustomerManager;
import tools.ProductManager;
import tools.PurchaseManager;
import tools.UserManager;

public class UserInterface {
    private Scanner scan = new Scanner(System.in);
    private ProductManager productManager = new ProductManager();
    private CustomerManager customerManager = new CustomerManager();
    private PurchaseManager purchaseManager = new PurchaseManager();
    private UserManager userManager = new UserManager();
    
    public void printManagerUI(List<User> listUsers, List<Customer> listCustomers, List<Product> listProducts, List<Purchase> listPurchases){
        boolean repeat = true;
        do {            
            System.out.println("=============================================");
            System.out.println("Список операций: ");
            System.out.println("0. Покинуть магазин");
            System.out.println("1. Добавить мягкую мебель");
            System.out.println("2. Просмотреть список мягкой мебели");
            System.out.println("3. Добавить покупателя");
            System.out.println("4. Просмотреть список покупателей");
            System.out.println("5. Купить мягкую мебель");
            System.out.println("6. Просмотреть список покупок");
            System.out.println("=============================================");
            System.out.printf("Выберите номер операции: ");
            String var = scan.nextLine();
            switch (var) {
                case "0":
                    System.out.println(" --- ВЫХОД ИЗ МАГАЗИНА --- ");
                    repeat = false;
                    break;
                case "1":
                    System.out.println(" --- ДОБАВЛЕНИЕ ТОВАРА --- ");
                    Product product = productManager.createProduct();
                    productManager.addProductToArray(product, listProducts);
                    break;
                case "2":
                    System.out.println(" --- СПИСОК ТОВАРОВ --- ");
                    productManager.printListProducts(listProducts);
                    break;
                case "3":
                    System.out.println(" --- ДОБАВЛЕНИЕ ПОКУПАТЕЛЯ --- ");
                    Customer customer = customerManager.createCustomer();
                    customerManager.addCustomerToArray(customer, listCustomers);
                    break;
                case "4":
                    System.out.println(" --- СПИСОК ПОКУПАТЕЛЕЙ --- ");
                    customerManager.printListCustomers(listCustomers);
                    break;
                case "5":
                    System.out.println(" --- СДЕЛАТЬ ПОКУПКУ --- ");
                    purchaseManager.makeDeal(listCustomers, listProducts, listPurchases);
                    break;
                case "6":
                    System.out.println(" --- СПИСОК ПОКУПОК --- ");
                    purchaseManager.printListPurchases(listPurchases);
                    break;
                default:
                    System.out.println("Нет такой задачи!");
            }
        } while (repeat);
    }
    public void printCustomerUI(List<User> listUsers, List<Customer> listCustomers, List<Product> listProducts, List<Purchase> listPurchases){
        boolean repeat = true;
        do {            
            System.out.println("=============================================");
            System.out.println("Список операций: ");
            System.out.println("0. Покинуть магазин");
            System.out.println("1. Просмотреть список мягкой мебели");
            System.out.println("2. Купить мягкую мебель");
            System.out.println("3. Просмотреть журнал покупок");
            System.out.println("4. Просмотреть список покупателей");
            System.out.println("=============================================");
            System.out.printf("Выберите номер операции: ");
            String var = scan.nextLine();
            switch (var) {
                case "0":
                    System.out.println(" ===== ВЫХОД ИЗ МАГАЗИНА ===== ");
                    repeat = false;
                    break;
                case "1":
                    System.out.println(" ===== СПИСОК МЯГКОЙ МЕБЕЛИ ===== ");
                    productManager.printListProducts(listProducts);
                    break;
                case "2":
                    System.out.println(" ==== СДЕЛАТЬ ПОКУПКУ ===== ");
                    purchaseManager.makeDeal(listCustomers, listProducts, listPurchases);
                    break;
                case "3":
                    System.out.println(" ===== ЖУРНАЛ ПОКУПОК ===== ");
                    purchaseManager.printListPurchases(listPurchases);
                    break;
                case "4":
                    System.out.println(" --- СПИСОК ПОКУПАТЕЛЕЙ (Проверить баланс) --- ");
                    customerManager.printListCustomers(listCustomers);
                    break;
                default:
                    System.out.println("Нет такой задачи!");
            }
        } while (repeat);
    }
}