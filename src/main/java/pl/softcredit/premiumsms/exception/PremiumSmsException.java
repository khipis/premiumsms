package pl.softcredit.premiumsms.exception;


/**
 * Exception for premium sms integration
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class PremiumSmsException extends Exception {

    private String message = null;

    public PremiumSmsException() {
        super();
    }

    public PremiumSmsException(String message) {
        super(message);
        this.message = message;
    }

    public PremiumSmsException(Throwable cause) {
        super(cause);
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
