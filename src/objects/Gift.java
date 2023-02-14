package objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.Category;

public class Gift implements Comparable<Gift> {

    private String productName;
    private Double price;
    private Category category;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int quantity;

    public Gift() { }

    public Gift(final Gift gift) {
        this.productName = gift.getProductName();
        this.price = gift.getPrice();
        this.category = gift.getCategory();
        this.quantity = gift.getQuantity();
    }

    public final String getProductName() {
        return productName;
    }

    public final void setProductName(final String productName) {
        this.productName = productName;
    }

    public final Double getPrice() {
        return price;
    }

    public final void setPrice(final Double price) {
        this.price = price;
    }

    public final Category getCategory() {
        return category;
    }

    public final void setCategory(final Category category) {
        this.category = category;
    }

    public final int getQuantity() {
        return quantity;
    }

    public final void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    @Override
    public final int compareTo(final Gift gift) {
        if (this.getCategory().equals(gift.getCategory())) {
            return this.getPrice().compareTo(gift.getPrice());
        } else {
            return this.getCategory().compareTo(gift.getCategory());
        }
    }
}
