package tools;

import entity.Product;
import java.util.List;
import java.util.Scanner;
import JPTVR19Hodzajev_Upholstered_Furniture.App;

public class ProductManager {
    public Product createProduct(){
        Product product = new Product();
        System.out.printf("Название мягкой мебели: ");
        Scanner scan = new Scanner(System.in);
        product.setName(scan.nextLine());
        double numPrice;
        do {            
            System.out.printf("Стоимость мягкой мебели: ");
            String strPrice = scan.nextLine();
            try {
                numPrice = Double.parseDouble(strPrice);
                break;
            } catch (Exception e) {
                System.out.println("Можно использовать только цифры.");
            }
        } while (true);
        product.setPrice(numPrice);
        return product;
    }
    
    public void addProductToArray(Product product, List<Product> listProducts){
        listProducts.add(product);
        StorageManager storageManager = new StorageManager();
        storageManager.save(listProducts, App.storageFile.PRODUCTS.toString());
    }
    public boolean printListProducts(List<Product> listProducts){
        if(listProducts == null || listProducts.size() < 1){
            System.out.println("В наличии нет мягкой мебели.");
            return false;
        }
        int n = 0;
        for (Product listProduct : listProducts) {
            if(listProduct != null){
                System.out.println(n+1+". "+listProduct.toString());
                n++;
            }
        }
        return true;
    }
}
