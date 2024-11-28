// Your1PropertyConnect\src\main\java\your1propertyconnect\Model\SubscriptionTier.java

package your1propertyconnect.Model;

public enum SubscriptionTier {
    NOTIER(0.00),
    TIER1(75.00),
    TIER2(150.00),
    TIER3(300.00);

    private final double price;

    SubscriptionTier(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
