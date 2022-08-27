package javax;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCart {
    private final Set<ProductQuantityMap> products = new HashSet<>();

    public void add(Product product) {
        add(product, 1);
    }

    public double getPrice() {
        Double cartPrice = products.stream()
                .map(p -> p.getProduct().getPrice() * p.getQuantity())
                .reduce(0d, Double::sum);
        return roundUp(cartPrice);
    }

    private double roundUp(Double cartPrice) {
        return Math.round(cartPrice * 100) / 100d;
    }

    public void add(Product product, Integer quantity) {
        Optional<ProductQuantityMap> item = products.stream()
                .filter(p -> p.getProduct().equals(product))
                .findFirst();

        if (item.isPresent()) {
            item.get().addQuantity(quantity);
            return;
        }

        products.add(new ProductQuantityMap(product, quantity));
    }

    public Set<ProductQuantityMap> getProducts() {
        return products;
    }
}
