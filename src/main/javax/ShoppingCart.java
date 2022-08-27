package javax;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Product> products = new ArrayList<>();

    public void add(Product product) {
        this.products.add(product);
    }

    public double getPrice() {
        return products.stream().map(Product::getPrice).reduce(0d, Double::sum);
    }
}
