package pl.softcredit.premiumsms.exception;


/**
 * Exception for premium sms integration
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public final class PremiumSmsException extends Exception {

    private String message = null;

    public PremiumSmsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
