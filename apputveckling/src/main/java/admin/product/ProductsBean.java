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
    List<Product> productsDetails;
    private String isShowProductDetails = "false";

    public String getIsShowProductDetails() {
        return isShowProductDetails;
    }

    public void setIsShowProductDetails(String isShowProductDetails) {
        this.isShowProductDetails = isShowProductDetails;
    }

    public ProductsBean() {
        // kommer erstatta med att hämta från databasen
        products = new ArrayList<>();
        Product product1 = new Product(0,"Product 1", "Description 1", "1.00", "1", "Category 1", "BobMarley.JPG");
        Product product2 = new Product(1,"Product 2", "Description 2", "2.00", "2", "Category 2", "IMG_1186.JPG");
        Product product3 = new Product(2,"Product 3", "Description 3", "3.00", "3", "Category 3", "IMG_1187.JPG");
        Product product4 = new Product(3,"Product 4", "Description 4", "4.00", "4", "Category 4", "IMG_1188.JPG");
        Product product5 = new Product(4,"Product 5", "Description 5", "5.00", "5", "Category 5", "IMG_1189.JPG");
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

    public List<Product> getProductsDetails() {
        return productsDetails;
    }

    public void setProductsDetails(List<Product> productsDetails) {
        this.productsDetails = productsDetails;
    }

    public void showProductDetails(int productId){
        productsDetails = new ArrayList<>();
        int id = products.get(productId).getId();
        productsDetails.add(products.get(id));
        isShowProductDetails = "true";

    }
}
