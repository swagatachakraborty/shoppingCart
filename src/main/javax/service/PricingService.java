package javax.service;

import javax.model.PricingSummary;
import javax.model.ProductQuantityMap;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public class PricingService {
    private final Double salesTaxRate;

    public PricingService(Double salesTaxRate) {
        this.salesTaxRate = salesTaxRate;
    }

    public PricingSummary getPricingSummary(Set<ProductQuantityMap> products) {
        Double totalProductPrice = products.stream()
                .map(p -> p.getProduct().getPrice() * p.getQuantity())
                .reduce(0d, Double::sum);

        Double totalSalesTax = totalProductPrice * salesTaxRate / 100;

        return new PricingSummary(
                roundUp(totalProductPrice),
                roundUp(totalSalesTax),
                roundUp(totalProductPrice + totalSalesTax)
        );
    }

    private double roundUp(Double value) {
        return BigDecimal
                .valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
