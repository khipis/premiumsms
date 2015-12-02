package pl.softcredit.premiumsms;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.softcredit.premiumsms.dto.Response;
import pl.softcredit.premiumsms.enums.FailureReason;
import pl.softcredit.premiumsms.exception.PremiumSmsException;
import pl.softcredit.premiumsms.provider.ServerResponseProvider;
import pl.softcredit.premiumsms.provider.strategy.PlatnosciOnlineResponseProvider;
import pl.softcredit.premiumsms.provider.strategy.SuccessResponseProvider;

import static pl.softcredit.premiumsms.Mocks.INVALID_CODE;
import static pl.softcredit.premiumsms.Mocks.ONE_PLN_GROSS_VALUE;
import static pl.softcredit.premiumsms.Mocks.VALID_CODE_1_PLN;
import static pl.softcredit.premiumsms.Mocks.VALID_SIGNATURE_1_PLN;
import static pl.softcredit.premiumsms.Mocks.get1PLNQueryCode;
import static pl.softcredit.premiumsms.Mocks.getConfig;
import static pl.softcredit.premiumsms.Mocks.getInvalidPartnerConfig;
import static pl.softcredit.premiumsms.Mocks.getInvalidSuffixQueryCode;
import static pl.softcredit.premiumsms.enums.FailureReason.INVALID_PARTNER_ID;
import static pl.softcredit.premiumsms.enums.FailureReason.PREVIOUSLY_USED_CODE;
import static pl.softcredit.premiumsms.enums.FailureReason.SUFFIX_OF_OTHER_PARTNER;
import static pl.softcredit.premiumsms.enums.VerificationStatus.FAILURE;
import static pl.softcredit.premiumsms.enums.VerificationStatus.SUCCESS;

@RunWith(JUnit4.class)
public class CodeVerifier1PLNTest {

    private SoftAssertions softAssertion = new SoftAssertions();

    //@Test
    public void shouldRecognizeValidCodeLive() throws PremiumSmsException {
        Response response = getResponseFromVerifier(
                new PlatnosciOnlineResponseProvider(), VALID_CODE_1_PLN);

        softAssertion = new SoftAssertions();
        softAssertion.assertThat(response.getCost()).isEqualTo(ONE_PLN_GROSS_VALUE);
        softAssertion.assertThat(response.getVerificationStatus()).isEqualTo(SUCCESS);
        softAssertion.assertAll();

    }

    @Test
    public void shouldRecognizeValidCode() throws PremiumSmsException {
        Response response = getResponseFromVerifier(
                new SuccessResponseProvider("" + ONE_PLN_GROSS_VALUE,
                                            VALID_SIGNATURE_1_PLN), VALID_CODE_1_PLN);

        softAssertion = new SoftAssertions();
        softAssertion.assertThat(response.getCost()).isEqualTo(ONE_PLN_GROSS_VALUE);
        softAssertion.assertThat(response.getVerificationStatus()).isEqualTo(SUCCESS);
        softAssertion.assertAll();
    }

    @Test
    public void shouldRecognizeFailurePreviouslyUsedCode() throws PremiumSmsException {
        Response response = getResponseFromVerifier(new PlatnosciOnlineResponseProvider(), VALID_CODE_1_PLN);
        invalidResponseAssert(response, PREVIOUSLY_USED_CODE);
    }


    @Test
    public void shouldRecognizeFailureInvalidCode() throws PremiumSmsException {
        Response response = getResponseFromVerifier(new PlatnosciOnlineResponseProvider(), INVALID_CODE);
        invalidResponseAssert(response, FailureReason.INVALID_CODE);
    }

    @Test
    public void shouldRecognizeFailureSuffixFromOtherPartner() throws PremiumSmsException {
        CodeVerifier verifier = new CodeVerifier(new PlatnosciOnlineResponseProvider(), getConfig());
        Response response = verifier.verify(getInvalidSuffixQueryCode(VALID_CODE_1_PLN));

        invalidResponseAssert(response, SUFFIX_OF_OTHER_PARTNER);
    }

    @Test
    public void shouldRecognizeFailureInvalidPartnerId() throws PremiumSmsException {
        CodeVerifier verifier = new CodeVerifier(new PlatnosciOnlineResponseProvider(), getInvalidPartnerConfig());
        Response response = verifier.verify(get1PLNQueryCode(VALID_CODE_1_PLN));

        invalidResponseAssert(response, INVALID_PARTNER_ID);
    }

    private void invalidResponseAssert(Response response, FailureReason invalidPartnerId) {
        softAssertion = new SoftAssertions();
        softAssertion.assertThat(response.getCost()).isEqualTo(0);
        softAssertion.assertThat(response.getVerificationStatus()).isEqualTo(FAILURE);
        softAssertion.assertThat(response.getFailureReason()).isEqualTo(invalidPartnerId);
        softAssertion.assertAll();

    }

    private Response getResponseFromVerifier(ServerResponseProvider provider, String code) throws PremiumSmsException {
        CodeVerifier
                verifier = new CodeVerifier(provider, getConfig());
        return verifier.verify(get1PLNQueryCode(code));
    }

}
