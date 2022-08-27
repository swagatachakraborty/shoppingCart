package javax.model;

public class PricingSummary {
    private final Double totalSalesTax;
    private final Double totalProductPrice;
    private final Double cartPrice;

    public PricingSummary(Double totalProductPrice, Double totalSalesTax, Double cartPrice) {
        this.totalProductPrice = totalProductPrice;
        this.totalSalesTax = totalSalesTax;
        this.cartPrice = cartPrice;
    }

    public Double getCartPrice() {
        return cartPrice;
    }

    public Double getTotalSalesTax() {
        return totalSalesTax;
    }

    public Double getTotalProductPrice() {
        return totalProductPrice;
    }
}
