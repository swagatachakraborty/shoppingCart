package javax;

import org.junit.Test;

import javax.model.OrderSummary;
import javax.model.Product;
import javax.model.ProductQuantityMap;
import javax.service.PricingService;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {
    private final Double doveSoapPrice = 39.99;
    private final Double axeDeoPrice = 99.99;
    private final Product doveSoap = new Product("Dove Soap", doveSoapPrice);
    private final Product axeDeo = new Product("Axe Deo", axeDeoPrice);
    private final PricingService pricingService = new PricingService(0d);
    private final ShoppingCart cart = new ShoppingCart(pricingService);


    @Test
    public void shouldAdd1DoveSoapToTheCart() {
        cart.add(doveSoap);

        assertEquals(doveSoapPrice, cart.getCartPrice());
    }

    @Test
    public void shouldAddMultipleDoveSoapToTheCart() {
        cart.add(doveSoap, 5);
        ArrayList<ProductQuantityMap> productsInCart = new ArrayList<>(cart.getProducts());

        assertEquals(199.95, cart.getCartPrice());

        assertEquals(1, productsInCart.size());
        assertEquals(doveSoap, productsInCart.get(0).getProduct());
        assertEquals(5, productsInCart.get(0).getQuantity());
    }

    @Test
    public void shouldAddSameProductMultipleTimesToTheCart() {
        cart.add(doveSoap, 5);
        cart.add(doveSoap, 3);
        ArrayList<ProductQuantityMap> productsInCart = new ArrayList<>(cart.getProducts());

        assertEquals(319.92, cart.getCartPrice());

        assertEquals(1, productsInCart.size());
        assertEquals(doveSoap, productsInCart.get(0).getProduct());
        assertEquals(8, productsInCart.get(0).getQuantity());
    }

    @Test
    public void shouldGetRoundedUpPriceUpTo2DecimalPoints() {
        Product pencil = new Product("Pencil", 5.5649);
        cart.add(pencil);

        assertEquals(5.56, cart.getCartPrice());
    }

    @Test
    public void shouldGetRoundedUpPriceUpTo2DecimalPoints_RoundUpToNextHighestNumber_whenThe3rdDecimalIsGreaterThan5() {
        Product pencil = new Product("Pencil", 5.566);
        cart.add(pencil);

        assertEquals(5.57, cart.getCartPrice());
    }

    @Test
    public void shouldGetRoundedUpPriceUpTo2DecimalPoints_RoundUpToNextHighestNumber_whenThe3rdDecimalIs5() {
        Product pencil = new Product("Pencil", 5.995);
        cart.add(pencil);

        assertEquals(6, cart.getCartPrice());
    }

    @Test
    public void shouldAddMultipleProductsToTheCart() {
        cart.add(doveSoap, 2);
        cart.add(axeDeo, 2);
        ArrayList<ProductQuantityMap> productsInCart = new ArrayList<>(cart.getProducts());

        assertEquals(279.96, cart.getCartPrice());

        assertEquals(2, productsInCart.size());
        assertEquals(axeDeo, productsInCart.get(0).getProduct());
        assertEquals(axeDeoPrice, productsInCart.get(0).getProduct().getPrice());
        assertEquals(2, productsInCart.get(0).getQuantity());
        assertEquals(doveSoap, productsInCart.get(1).getProduct());
        assertEquals(doveSoapPrice, productsInCart.get(1).getProduct().getPrice());
        assertEquals(2, productsInCart.get(1).getQuantity());
    }

    @Test
    public void shouldAddSalesTaxWhileCalculatingCartPrice() {
        ShoppingCart newCart = new ShoppingCart(new PricingService(12.5));
        newCart.add(doveSoap, 2);
        newCart.add(axeDeo, 2);
        ArrayList<ProductQuantityMap> productsInCart = new ArrayList<>(newCart.getProducts());

        assertEquals(314.96, newCart.getCartPrice());
        assertEquals(279.96, newCart.getPricingSummary().getTotalProductPrice());
        assertEquals(35.00, newCart.getPricingSummary().getTotalSalesTax());
        assertEquals(2, productsInCart.size());

        assertEquals(axeDeo, productsInCart.get(0).getProduct());
        assertEquals(axeDeoPrice, productsInCart.get(0).getProduct().getPrice());
        assertEquals(2, productsInCart.get(0).getQuantity());

        assertEquals(doveSoap, productsInCart.get(1).getProduct());
        assertEquals(doveSoapPrice, productsInCart.get(1).getProduct().getPrice());
        assertEquals(2, productsInCart.get(1).getQuantity());
    }

    @Test
    public void shouldCompleteOrderSummary() {
        ShoppingCart newCart = new ShoppingCart(new PricingService(12.5));
        newCart.add(doveSoap, 2);
        newCart.add(axeDeo, 2);
        OrderSummary orderSummary = newCart.getOrderSummary();

        assertEquals(314.96, orderSummary.getPricingSummary().getCartPrice());
        assertEquals(279.96, orderSummary.getPricingSummary().getTotalProductPrice());
        assertEquals(35.00, orderSummary.getPricingSummary().getTotalSalesTax());

        assertEquals(2, orderSummary.getProducts().size());

        assertEquals(axeDeo, orderSummary.getProducts().get(0).getProduct());
        assertEquals(axeDeoPrice, orderSummary.getProducts().get(0).getProduct().getPrice());
        assertEquals(2, orderSummary.getProducts().get(0).getQuantity());

        assertEquals(doveSoap, orderSummary.getProducts().get(1).getProduct());
        assertEquals(doveSoapPrice, orderSummary.getProducts().get(1).getProduct().getPrice());
        assertEquals(2, orderSummary.getProducts().get(1).getQuantity());
    }
}