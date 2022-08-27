package javax;

import java.util.Objects;

public class ProductQuantityMap {
    private final Product product;
    private Integer quantity;

    ProductQuantityMap(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    void addQuantity(Integer quantity) {
        this.quantity += quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuantityMap that = (ProductQuantityMap) o;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
