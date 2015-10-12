package pl.softcredit.premiumsms.enums;

import java.util.Collections;
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

    private int code;

    public static final Map<Integer, VerificationStatus> CACHE_BY_CODE;

    static {
        Map<Integer, VerificationStatus> valuesMap = new HashMap<>();
        for (VerificationStatus verificationStatus : VerificationStatus.values()) {
            valuesMap.put(verificationStatus.code, verificationStatus);
        }
        CACHE_BY_CODE = Collections.unmodifiableMap(valuesMap);
    }

    VerificationStatus(int statusCode) {
        this.code = statusCode;
    }
}