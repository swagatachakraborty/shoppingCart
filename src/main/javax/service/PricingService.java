package javax.service;

import javax.model.PricingSummary;
import javax.model.ProductQuantityMap;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PricingService {
    private final Double salesTaxRate;
    private List<String> productsWithOffer = new ArrayList<>();

    public PricingService(Double salesTaxRate) {
        this.salesTaxRate = salesTaxRate;
    }

    public PricingService(Double salesTaxRate, List<String> productsWithOffer) {
        this.salesTaxRate = salesTaxRate;
        this.productsWithOffer = productsWithOffer;
    }

    public PricingSummary getPricingSummary(Set<ProductQuantityMap> products) {
        Double totalProductPrice = products.stream()
                .map(p -> p.getProduct().getPrice() * p.getQuantity())
                .reduce(0d, Double::sum);

        Double totalDiscountAmount = products.stream()
                .map(p -> OfferService.GetDiscountedPrice(p, productsWithOffer))
                .reduce(0d, Double::sum);

        Double cartPriceWithoutTax = totalProductPrice - totalDiscountAmount;

        Double totalSalesTax = cartPriceWithoutTax * salesTaxRate / 100;

        return new PricingSummary(
                roundUp(totalProductPrice),
                roundUp(totalSalesTax),
                roundUp(totalDiscountAmount),
                roundUp(totalProductPrice + totalSalesTax - totalDiscountAmount)
        );
    }

    private double roundUp(Double value) {
        return BigDecimal
                .valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
