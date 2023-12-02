package admin.product;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductImagesBean implements Serializable {

    @Produces
    @PersistenceContext(unitName = "ProductImages")
    private EntityManager entityManager;
    List<ProductImages> productImages;
    List<ProductImages> oneImage;

    public ProductImagesBean() {
    }

    public List<ProductImages> getProductImages(int productId) {
        productImages = entityManager.createQuery("select p from ProductImages p where p.PRODUCT_ID = :productId", ProductImages.class)
                .setParameter("productId", productId)
                .getResultList();
        return productImages;
    }

    public void setProductImages(List<ProductImages> productImages) {
        this.productImages = productImages;
    }

    public String getOneImage(int productId) {
       oneImage = entityManager.createQuery("select p from ProductImages p where p.PRODUCT_ID = :productId", ProductImages.class)
                .setParameter("productId", productId)
                .getResultList();
        return oneImage.get(0).getImgPathString();
    }


    public void setOneImage(List<ProductImages> oneImage) {
        this.oneImage = oneImage;
    }
}
