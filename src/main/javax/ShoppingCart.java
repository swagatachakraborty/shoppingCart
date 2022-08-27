package javax;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ShoppingCart {
    private final Set<ProductQuantityMap> products = new HashSet<>();

    public void add(Product product) {
        add(product, 1);
    }

    public double getPrice() {
        return products.stream()
                .map(p -> p.getProduct().getPrice() * p.getQuantity())
                .reduce(0d, Double::sum);
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

    private static class ProductQuantityMap {
        private final Product product;
        private Integer quantity;

        ProductQuantityMap(Product product, Integer quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        Product getProduct() {
            return product;
        }

        Integer getQuantity() {
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
}
