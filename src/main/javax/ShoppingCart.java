package javax;

import javax.model.PricingSummary;
import javax.model.Product;
import javax.model.ProductQuantityMap;
import javax.service.PricingService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ShoppingCart {
    private final Set<ProductQuantityMap> products = new HashSet<>();
    private final PricingService pricingService;

    public ShoppingCart(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public void add(Product product) {
        add(product, 1);
    }

    public double getCartPrice() {
        return pricingService.getPricingSummary(products).getCartPrice();
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

    public PricingSummary getPricingSummary() {
        return pricingService.getPricingSummary(products);
    }
}
