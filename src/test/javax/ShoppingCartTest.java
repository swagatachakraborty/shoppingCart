package javax;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {
    Double doveSoapPrice = 39.99;
    Product doveSoap = new Product("Dove Soap", doveSoapPrice);
    ShoppingCart cart = new ShoppingCart();


    @Test
    public void shouldAdd1DoveSoapToTheCart() {
        cart.add(doveSoap);

        assertEquals(doveSoapPrice, cart.getPrice());
    }

    @Test
    public void shouldAddMultipleDoveSoapToTheCart() {
        cart.add(doveSoap, 5);

        assertEquals(199.95, cart.getPrice(), .001);
    }

    @Test
    public void shouldAddSameProductMultipleTimesToTheCart() {
        cart.add(doveSoap, 5);
        cart.add(doveSoap, 3);

        assertEquals(319.92, cart.getPrice(), .001);
    }
}