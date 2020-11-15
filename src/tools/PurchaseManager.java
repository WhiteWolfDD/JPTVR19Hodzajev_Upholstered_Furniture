package tools;

import entity.Customer;
import entity.Product;
import entity.Purchase;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import JPTVR19Hodzajev_Upholstered_Furniture.App;
import security.SecManager;

public class PurchaseManager {
    private Scanner scan = new Scanner(System.in);
    private CustomerManager customerManager = new CustomerManager();
    private ProductManager productManager = new ProductManager();
    private StorageManager storageManager = new StorageManager();
    
    public void makeDeal(List<Customer> listCustomers, List<Product> listProducts, List<Purchase> listPurchases){
        System.out.println(" ===== СПИСОК МЯГКОЙ МЕБЕЛИ ===== ");
        int productNum;
        do {            
            if(!productManager.printListProducts(listProducts)){
            return;
            }
            System.out.printf("Выберите номер мягкой мебели: ");
                String productNumStr = scan.nextLine();
            try {
                productNum = Integer.parseInt(productNumStr);
                if(productNum < 1 && productNum >= listProducts.size()){
                    throw new Exception("Выход за диапазон доступной мягкой мебели");
                }
                break;
            } catch (Exception e) {
                System.out.println("Выберите номер из указанного выше списка");
                productNumStr = scan.nextLine();
            }
        } while (true);
        Product product = listProducts.get(productNum-1);
        Customer customer = null;
        if(SecManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            customer = App.loggedInUser.getCustomer();
        }else if (SecManager.role.CUSTOMER.toString().equals(App.loggedInUser.getRole())){
            customer = App.loggedInUser.getCustomer();
        }
        Calendar calendar = new GregorianCalendar();

        double residual;
        residual = customer.getBalance() - product.getPrice();
        if (residual < 0){
            System.out.println("----------------------------------");
            System.out.println("Недостаточно средств для покупки");
            System.out.println("----------------------------------");
            System.out.println("Баланс: "+customer.getBalance()+"€");
            System.out.println("----------------------------------");
            System.out.println("Хотите пополнить свой баланс?");
            System.out.println("1. Пополнить баланс");
            System.out.println("2. Не пополнять баланс");
            String var = scan.nextLine();
            switch (var) {
                case "1":
                    System.out.println("--- ПОПОЛНИТЬ БАЛАНС ---");
                    double numBalance;
                    do {
                        System.out.printf("Введите сколько денег вы хотите добавить на счёт: ");
                        String strBalance = scan.nextLine();
                        try {
                            numBalance = Double.parseDouble(strBalance);
                            System.out.println("Вы успешно пополнили свой счёт на "+strBalance+"€");
                            break;
                        } catch (Exception e) {
                            System.out.println("Нужно использовать только цифры.");
                        }
                    } while (true);
                    customer.setBalance(customer.getBalance()+numBalance);
                    System.out.println("Теперь ваш баланс составляет "+customer.getBalance()+"€");
                    System.out.println("Попробуйте заново купить товар.");
                    break;
                case "2":
                    System.out.println(" --- НЕ ПОПОЛНЯТЬ БАЛАНС --- ");
                    break;
            }
                return;
        }else{
            customer.setBalance(residual);
            System.out.println("Теперь ваш баланс составляет "+ residual+"€");
            Purchase purchase =  new Purchase(customer, product, calendar.getTime());
            this.addPurchaseToArray(purchase, listPurchases);
        }
    }
    public void addPurchaseToArray(Purchase purchase, List<Purchase> listPurchases){
        listPurchases.add(purchase);
        storageManager.save(listPurchases, App.storageFile.PURCHASES.toString());
    }
    
    public boolean printListPurchases(List<Purchase> listPurchases){
        boolean notDeals = true;
        final SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        if(SecManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            for (int i = 0; i < listPurchases.size(); i++) {
                if (listPurchases.get(i) != null)
                    System.out.printf("%d. Клиент %s %s купил \"%s\" %s%n"
                            , i + 1
                            , listPurchases.get(i).getCustomer().getFirstName()
                            , listPurchases.get(i).getCustomer().getLastName()
                            , listPurchases.get(i).getProduct().getName()
                            , listPurchases.get(i).getPurchaseDate()
                    );
                notDeals = false;
            }
                System.out.printf("Сейчас у нас %s%n"
                        , f.format(new Date())
                );
            if(notDeals){
                System.out.println("Журнал покупок пуст");
                return false;
            }
        }else if (SecManager.role.CUSTOMER.toString().equals(App.loggedInUser.getRole())){
            for (int i = 0; i < listPurchases.size(); i++) {
                if (listPurchases.get(i) != null)
                    System.out.printf("%d. Клиент %s %s купил \"%s\" %s%n"
                            , i + 1
                            , listPurchases.get(i).getCustomer().getFirstName()
                            , listPurchases.get(i).getCustomer().getLastName()
                            , listPurchases.get(i).getProduct().getName()
                            , listPurchases.get(i).getPurchaseDate()
                    );
                notDeals = false;
            }
                System.out.printf("Сейчас у нас %s%n"
                        , f.format(new Date())
                );
            if(notDeals){
                System.out.println("Журнал покупок пуст");
                return false;
            }
        }
    return true;
    }
}
