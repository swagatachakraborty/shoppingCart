package javax;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {
    Double doveSoapPrice = 39.99;
    Double axeDeoPrice = 99.99;
    Product doveSoap = new Product("Dove Soap", doveSoapPrice);
    Product axeDeo = new Product("Axe Deo", axeDeoPrice);
    ShoppingCart cart = new ShoppingCart();


    @Test
    public void shouldAdd1DoveSoapToTheCart() {
        cart.add(doveSoap);

        assertEquals(doveSoapPrice, cart.getPrice());
    }

    @Test
    public void shouldAddMultipleDoveSoapToTheCart() {
        cart.add(doveSoap, 5);
        ArrayList<ProductQuantityMap> productsInCart = new ArrayList<>(cart.getProducts());

        assertEquals(199.95, cart.getPrice());

        assertEquals(1, productsInCart.size());
        assertEquals(doveSoap, productsInCart.get(0).getProduct());
        assertEquals(5, productsInCart.get(0).getQuantity());
    }

    @Test
    public void shouldAddSameProductMultipleTimesToTheCart() {
        cart.add(doveSoap, 5);
        cart.add(doveSoap, 3);
        ArrayList<ProductQuantityMap> productsInCart = new ArrayList<>(cart.getProducts());

        assertEquals(319.92, cart.getPrice());

        assertEquals(1, productsInCart.size());
        assertEquals(doveSoap, productsInCart.get(0).getProduct());
        assertEquals(8, productsInCart.get(0).getQuantity());
    }

    @Test
    public void shouldGetRoundedUpPriceUpTo2DecimalPoints() {
        Product pencil = new Product("Pencil", 5.564);
        cart.add(pencil);

        assertEquals(5.56, cart.getPrice());
    }

    @Test
    public void shouldGetRoundedUpPriceUpTo2DecimalPoints_RoundUpToNextHighestNumber() {
        Product pencil = new Product("Pencil", 5.565);
        cart.add(pencil);

        assertEquals(5.57, cart.getPrice());
    }

    @Test
    public void shouldAddMultipleProductsToTheCart() {
        cart.add(doveSoap, 2);
        cart.add(axeDeo, 2);
        ArrayList<ProductQuantityMap> productsInCart = new ArrayList<>(cart.getProducts());

        assertEquals(279.96, cart.getPrice());

        assertEquals(2, productsInCart.size());
        assertEquals(axeDeo, productsInCart.get(0).getProduct());
        assertEquals(axeDeoPrice, productsInCart.get(0).getProduct().getPrice());
        assertEquals(2, productsInCart.get(0).getQuantity());
        assertEquals(doveSoap, productsInCart.get(1).getProduct());
        assertEquals(doveSoapPrice, productsInCart.get(1).getProduct().getPrice());
        assertEquals(2, productsInCart.get(1).getQuantity());
    }
}