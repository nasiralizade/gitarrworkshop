package admin.product;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Product images bean
 * This class is used to get product images from the database
 * This class is also used to add product images to the database
 * This class is also used to get one product image from the database
 */
@Named
@SessionScoped
public class ProductImagesBean implements Serializable {

    @Produces
    @PersistenceContext(unitName = "ProductImages")
    private EntityManager entityManager;
    List<ProductImages> productImages;
    List<ProductImages> oneImage; // for the product page
    public ProductImagesBean() {
    }
    public void setProductImages(List<ProductImages> productImages) {
        this.productImages = productImages;
    }

   
}
