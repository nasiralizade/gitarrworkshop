package admin.product;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * this class is used to get the list of products from the database
 * and to show the details of a specific product
 * @see Product for the product entity
 */
@Named
@RequestScoped
public class ProductBean implements Serializable {
    @Produces
    @PersistenceContext(unitName = "PRODUCT")
    private EntityManager entityManager;
    Product newProduct= new Product();

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
    }

    List<Product> products; // used to get the list of products from the database
    List<Product> productsDetails; // used to show the details of a specific product
    private String isShowProductDetails = "false"; // used to show the details of a specific product

    /**
     * this method is used to get the value of isShowProductDetails
     * @return the value of isShowProductDetails
     */
    public String getIsShowProductDetails() {
        return isShowProductDetails;
    }

    public void setIsShowProductDetails(String isShowProductDetails) {
        this.isShowProductDetails = isShowProductDetails;
    }

    public ProductBean() {
    }



    public void setProductsDetails(List<Product> productsDetails) {
        this.productsDetails = productsDetails;
    }

    /**
     * this method is used to show the details of a specific product
     * @see Product for the product entity
     * @param productId the id of the product
     *                  the id is used to get the product from the database
     *                  and to show the details of the product
     */
    public void showProductDetails(int productId){
        productsDetails = entityManager.createQuery("select p from Product p where p.PRODUCT_ID = :productId", Product.class)
                .setParameter("productId", productId)
                .getResultList();
        isShowProductDetails = "true";

    }

    /**
     * this method is used to get the list of products from the database
     * @return the list of products
     */
    public List<Product> getProducts() {
        products = entityManager.createQuery("select p from Product p", Product.class).getResultList();
        return products;
    }

    /**
     * this method is used to set the list of products
     * @param products the list of products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * this method is used to get the list of products from the database
     * @return the list of products
     */
    public List<Product> getProductsDetails() {
        return productsDetails;
    }

    /**
     * this method is used to add a product to the database
     * @param productName the name of the product
     * @param productPrice the price of the product
     * @param productMainDesc the main description of the product
     * @param productHistoryDesc the history description of the product
     * @param productYear the year of the product
     */
    public void addProduct(){

    }

    /**
     * this method is used to update a product in the database
     * @param productId the id of the product
     */
@Transactional
    public void deleteProduct(int productId){
        entityManager.createQuery("delete from Product p where p.PRODUCT_ID = :productId")
                .setParameter("productId", productId)
                .executeUpdate();
       ProductImagesBean productImagesBean = new ProductImagesBean();
         productImagesBean.deleteProductImages(productId);
    }


}
