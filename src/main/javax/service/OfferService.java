package javax.service;

import javax.model.ProductQuantityMap;
import java.util.List;

public class OfferService {

    public static Double GetDiscountedPrice(ProductQuantityMap productQuantityMap, List<String> productWithOffer) {
        boolean isApplicable = productWithOffer.stream().anyMatch(ProductName -> productQuantityMap.getProduct().getName().equals(ProductName));
        if (isApplicable) {
            return productQuantityMap.getProduct().getPrice() * (Integer)(productQuantityMap.getQuantity() / 3);
        }
        return 0d;
    }
}
