package admin.product;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.Part;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 * this class is used to get the list of products from the database
 * and to show the details of a specific product
 *
 * @see Product for the product entity
 */
@Named
@RequestScoped
public class ProductBean implements Serializable {
    @Produces
    @PersistenceContext(unitName = "PRODUCT")
    private EntityManager entityManager;
    Product newProduct = new Product();
    private List<Part>imageFile;
    List<Product> products; // used to get the list of products from the database
    List<Product> productsDetails; // used to show the details of a specific product
    private String isShowProductDetails = "false"; // used to show the details of a specific product
    List<ProductImages> productImagesList; // used to get the list of product images from the database
    private Part saveProductImages; // used to save the product images to the database

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
    }

    /**
     * this method is used to get the value of isShowProductDetails
     *
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

    public List<Part> getImageFile() {
        return imageFile;
    }

    public void setImageFile(List<Part> imageFile) {
        this.imageFile = imageFile;
    }

    /**
     * this method is used to show the details of a specific product
     *
     * @param productId the id of the product
     *                  the id is used to get the product from the database
     *                  and to show the details of the product
     * @see Product for the product entity
     */
    public void showProductDetails(int productId) {
        productsDetails = entityManager.createQuery("select p from Product p where p.PRODUCT_ID = :productId", Product.class)
                .setParameter("productId", productId)
                .getResultList();
        isShowProductDetails = "true";

    }

    public List<ProductImages> getProductImagesList() {
        productImagesList = entityManager.createQuery("select p from ProductImages p", ProductImages.class).getResultList();
        return productImagesList;
    }

    public void setProductImagesList(List<ProductImages> productImagesList) {
        this.productImagesList = productImagesList;
    }

    /**
     * this method is used to get the list of products from the database
     *
     * @return the list of products
     */
    public List<Product> getProducts() {
        products = entityManager.createQuery("select p from Product p", Product.class).getResultList();
        return products;
    }

    /**
     * this method is used to set the list of products
     *
     * @param products the list of products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * this method is used to get the list of products from the database
     *
     * @return the list of products
     */
    public List<Product> getProductsDetails() {
        return productsDetails;
    }



    /**
     * this method is used to update a product in the database
     *
     * @param productId the id of the product
     */
    @Transactional
    public void deleteProduct(int productId) {
        entityManager.createQuery("delete from Product p where p.PRODUCT_ID = :productId")
                .setParameter("productId", productId)
                .executeUpdate();
    }

    /**
     *
     * @throws IOException
     */
    @Transactional
    public void addProduct() throws IOException {
        try {

            entityManager.persist(newProduct);
            //ProductImagesBean imagesBean =new ProductImagesBean();
           // imagesBean.addProductImages(newProduct.getPRODUCT_ID(), imageFile);
        }catch (Exception e){
            throw e;
        }

    }

}
