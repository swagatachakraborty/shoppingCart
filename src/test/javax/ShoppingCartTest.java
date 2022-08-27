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

        assertEquals(199.95, cart.getPrice());
    }

    @Test
    public void shouldAddSameProductMultipleTimesToTheCart() {
        cart.add(doveSoap, 5);
        cart.add(doveSoap, 3);

        assertEquals(319.92, cart.getPrice());
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
}