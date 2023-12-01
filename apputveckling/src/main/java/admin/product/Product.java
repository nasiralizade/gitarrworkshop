package admin.product;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Product {
    private int id;
    List<String> images = List.of("BobMarley.JPG", "IMG_1186.JPG", "IMG_1187.JPG", "IMG_1188.JPG", "IMG_1189.JPG");

    private String name;
    private String description;
    private String price;
    private String quantity; //the number of items in stock
    private String history;
    private String category;
    private String image;

    public Product(int id, String name, String description, String price, String quantity, String category, String image) {

        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
