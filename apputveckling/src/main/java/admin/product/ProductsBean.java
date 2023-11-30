package admin.product;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Named
@RequestScoped
public class ProductsBean implements Serializable {
    List<Product> products;

    public ProductsBean() {
        // kommer erstatta med att hämta från databasen
        products = new ArrayList<>();
        Product product1 = new Product("Product 1", "Description 1", "1.00", "1", "Category 1", "BobMarley.JPG");
        Product product2 = new Product("Product 2", "Description 2", "2.00", "2", "Category 2", "IMG_1186.JPG");
        Product product3 = new Product("Product 3", "Description 3", "3.00", "3", "Category 3", "IMG_1187.JPG");
        Product product4 = new Product("Product 4", "Description 4", "4.00", "4", "Category 4", "IMG_1188.JPG");
        Product product5 = new Product("Product 5", "Description 5", "5.00", "5", "Category 5", "IMG_1189.JPG");
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
