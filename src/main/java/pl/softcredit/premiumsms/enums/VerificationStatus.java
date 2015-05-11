package pl.softcredit.premiumsms.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Class VerificationStatus, status of transaction
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public enum VerificationStatus {
    FAILURE(0),
    SUCCESS(1),
    SUCCESS_BAD_SIGNATURE(2);

    private int statusCode;

    public static final Map<Integer, VerificationStatus> CACHE_BY_VALUE =
            new HashMap<Integer, VerificationStatus>();

    static {
        for (VerificationStatus verificationStatus : VerificationStatus.values()) {
            CACHE_BY_VALUE.put(verificationStatus.statusCode, verificationStatus);
        }
    }

    VerificationStatus(int statusCode) {
        this.statusCode = statusCode;
    }
}