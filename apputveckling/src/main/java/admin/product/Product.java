package admin.product;

import java.util.concurrent.ThreadLocalRandom;
public class Product {
    private Long id;


    private String name;
    private String description;
    private String price;
    private String quantity; //the number of items in stock
    private String history;
    private String category;
    private String image;

    public Product(String name, String description, String price, String quantity, String category, String image) {

        this.id= (long) ThreadLocalRandom.current().nextInt(1, 1000 + 1);
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.image = image;
    }


    public Product() {
        this.name = "";
        this.description = "";
        this.price = "";
        this.quantity = "";
        this.category = "";
        this.image = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
