package insurance.premium;


public class NotSupportedCurrencyException extends Exception {
    public NotSupportedCurrencyException(String currencyCode) {
        super(String.format("The currency %s is not supported.", currencyCode));
    }
}
