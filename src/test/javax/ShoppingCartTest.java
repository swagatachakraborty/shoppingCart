package javax;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    @Test
    public void shouldAdd1DoveSoapToTheCart() {
        double doveSoapPrice = 39.99d;
        Product doveSoap = new Product("Dove Soap", doveSoapPrice);
        ShoppingCart cart = new ShoppingCart();
        cart.add(doveSoap);

        assertEquals(doveSoapPrice, cart.getPrice());
    }
}