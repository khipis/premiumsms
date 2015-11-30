package pl.softcredit.premiumsms.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.softcredit.premiumsms.Mocks.VALID_CODE_1_PLN;
import static pl.softcredit.premiumsms.Mocks.VALID_CODE_3_PLN;
import static pl.softcredit.premiumsms.Mocks.VALID_SIGNATURE_1_PLN;
import static pl.softcredit.premiumsms.Mocks.VALID_SIGNATURE_3_PLN;
import static pl.softcredit.premiumsms.Mocks.get1PLNQueryCode;
import static pl.softcredit.premiumsms.Mocks.get3PLNQueryCode;
import static pl.softcredit.premiumsms.Mocks.getConfig;

@RunWith(JUnit4.class)
public class SignatureUtilTest {

    @Test
    public void shouldComputeProperSignature1PLN() throws NoSuchAlgorithmException {
        assertThat(SignatureUtil.compute(getConfig(), get1PLNQueryCode(VALID_CODE_1_PLN)))
                .isEqualTo(VALID_SIGNATURE_1_PLN);
    }

    @Test
    public void shouldComputeProperSignature3PLN() throws NoSuchAlgorithmException {
        assertThat(SignatureUtil.compute(getConfig(), get3PLNQueryCode(VALID_CODE_3_PLN)))
                .isEqualTo(VALID_SIGNATURE_3_PLN);
    }

}