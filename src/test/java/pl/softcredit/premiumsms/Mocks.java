package pl.softcredit.premiumsms;

import pl.softcredit.premiumsms.builder.CodeQueryBuilder;
import pl.softcredit.premiumsms.builder.ConfigBuilder;
import pl.softcredit.premiumsms.dto.CodeQuery;
import pl.softcredit.premiumsms.dto.Config;


public class Mocks {

    public static final String SUFFIX_1_PLN = "SOFTCRE";
    public static final String SUFFIX_3_PLN = "SOFT3";

    public static final String PARTNER_ID = "7827";
    public static final String KEY = "e93089b60bf84a464df30c8e9d138b26";

    public static final String VALID_CODE_1_PLN = "gxbfioit";
    public static final String VALID_SIGNATURE_1_PLN = "b75baaafe5f01501ca9b172eab8b76b9";

    public static final String VALID_CODE_3_PLN = "xxccphoq";
    public static final String VALID_SIGNATURE_3_PLN = "b656cfbb28e4460d0b2449c47c0a1f31";

    public static final String INVALID_CODE = "";
    public static final String INVALID_PARTNER_ID = "9999";
    public static final String INVALID_SUFFIX = "INVALID";

    public static final int ONE_PLN_GROSS_VALUE = 100;
    public static final int THREE_PLN_GROSS_VALUE = 300;

    private Mocks() {

    }

    public static CodeQuery get1PLNQueryCode(String code) {
        return CodeQueryBuilder.instance().suffix(SUFFIX_1_PLN).code(code).cost(ONE_PLN_GROSS_VALUE).build();
    }

    public static CodeQuery get3PLNQueryCode(String code) {
        return CodeQueryBuilder.instance().suffix(SUFFIX_3_PLN).code(code).cost(THREE_PLN_GROSS_VALUE).build();
    }

    public static CodeQuery getInvalidSuffixQueryCode(String code) {
        return CodeQueryBuilder.instance().suffix(INVALID_SUFFIX).code(code).cost(ONE_PLN_GROSS_VALUE).build();
    }

    public static Config getConfig() {
        return ConfigBuilder.instance().key(KEY).partnerId(PARTNER_ID)
                .url(Constants.HTTPS_PLATNOSCI_URL).build();
    }


    public static Config getInvalidPartnerConfig() {
        return ConfigBuilder.instance().key(KEY).partnerId(INVALID_PARTNER_ID)
                .url(Constants.HTTPS_PLATNOSCI_URL).build();
    }

}
