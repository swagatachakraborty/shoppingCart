package javax.model;

import java.util.*;

public class OrderSummary {
    private final UUID id;
    private final List<ProductQuantityMap> products;
    private final PricingSummary pricingSummary;

    public OrderSummary(Set<ProductQuantityMap> products, PricingSummary pricingSummary) {
        this.id = UUID.randomUUID();
        this.products = new ArrayList<>(products);
        this.pricingSummary = pricingSummary;
    }

    public UUID getId() {
        return id;
    }

    public List<ProductQuantityMap> getProducts() {
        return products;
    }

    public PricingSummary getPricingSummary() {
        return pricingSummary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderSummary that = (OrderSummary) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
