package pl.softcredit.premiumsms.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Class FailureReason
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public enum FailureReason {
    PREVIOUSLY_USED_CODE(1),
    INVALID_CODE(2),
    SUFFIX_OF_OTHER_PARTNER(3),
    INCORRECT_MODE(4),
    INVALID_PARTNER_ID(5);

    private int code;

    public static final Map<Integer, FailureReason> CACHE_BY_CODE;

    static {
        Map<Integer, FailureReason> valuesMap = new HashMap<>();
        for (FailureReason status : FailureReason.values()) {
            valuesMap.put(status.code, status);
        }
        CACHE_BY_CODE = Collections.unmodifiableMap(valuesMap);
    }

    FailureReason(int code) {
        this.code = code;
    }
}