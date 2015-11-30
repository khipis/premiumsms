package pl.softcredit.premiumsms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.softcredit.premiumsms.dto.Response;
import pl.softcredit.premiumsms.enums.FailureReason;
import pl.softcredit.premiumsms.enums.VerificationStatus;
import pl.softcredit.premiumsms.exception.PremiumSmsException;
import pl.softcredit.premiumsms.provider.ServerResponseProvider;
import pl.softcredit.premiumsms.provider.strategy.PlatnosciOnlineResponseProvider;
import pl.softcredit.premiumsms.provider.strategy.SuccessResponseProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.softcredit.premiumsms.Mocks.INVALID_CODE;
import static pl.softcredit.premiumsms.Mocks.THREE_PLN_GROSS_VALUE;
import static pl.softcredit.premiumsms.Mocks.VALID_CODE_3_PLN;
import static pl.softcredit.premiumsms.Mocks.VALID_SIGNATURE_3_PLN;
import static pl.softcredit.premiumsms.Mocks.get1PLNQueryCode;
import static pl.softcredit.premiumsms.Mocks.get3PLNQueryCode;
import static pl.softcredit.premiumsms.Mocks.getConfig;
import static pl.softcredit.premiumsms.Mocks.getInvalidPartnerConfig;
import static pl.softcredit.premiumsms.Mocks.getInvalidSuffixQueryCode;

@RunWith(JUnit4.class)
public class CodeVerifier3PLNTest {


    //@Test
    public void shouldRecognizeValidCodeLive() throws PremiumSmsException {
        Response response = getResponseFromVerifier(
                new PlatnosciOnlineResponseProvider(), VALID_CODE_3_PLN);

        assertThat(response.getCost()).isEqualTo(THREE_PLN_GROSS_VALUE);
        assertThat(response.getVerificationStatus()).isEqualTo(VerificationStatus.SUCCESS);
    }

    @Test
    public void shouldRecognizeValidCode() throws PremiumSmsException {
        Response response = getResponseFromVerifier(
                new SuccessResponseProvider("" + THREE_PLN_GROSS_VALUE,
                                            VALID_SIGNATURE_3_PLN), VALID_CODE_3_PLN);

        assertThat(response.getCost()).isEqualTo(THREE_PLN_GROSS_VALUE);
        assertThat(response.getVerificationStatus()).isEqualTo(VerificationStatus.SUCCESS);
    }

    @Test
    public void shouldRecognizeFailurePreviouslyUsedCode() throws PremiumSmsException {
        Response response = getResponseFromVerifier(new PlatnosciOnlineResponseProvider(),
                                                       VALID_CODE_3_PLN);
        invalidResponseAssert(response, FailureReason.PREVIOUSLY_USED_CODE);
    }


    @Test
    public void shouldRecognizeFailureInvalidCode() throws PremiumSmsException {
        Response response =
                getResponseFromVerifier(new PlatnosciOnlineResponseProvider(), INVALID_CODE);
        invalidResponseAssert(response, FailureReason.INVALID_CODE);
    }

    @Test
    public void shouldRecognizeFailureSuffixFromOtherPartner() throws PremiumSmsException {
        CodeVerifier verifier =
                new CodeVerifier(new PlatnosciOnlineResponseProvider(), getConfig());
        Response response =
                verifier.verify(getInvalidSuffixQueryCode(VALID_CODE_3_PLN));

        invalidResponseAssert(response, FailureReason.SUFFIX_OF_OTHER_PARTNER);
    }

    @Test
    public void shouldRecognizeFailureInvalidPartnerId() throws PremiumSmsException {
        CodeVerifier verifier = new CodeVerifier(new PlatnosciOnlineResponseProvider(),
                                                 getInvalidPartnerConfig());
        Response response = verifier.verify(get1PLNQueryCode(VALID_CODE_3_PLN));

        invalidResponseAssert(response, FailureReason.INVALID_PARTNER_ID);
    }

    private void invalidResponseAssert(Response response, FailureReason invalidPartnerId) {
        assertThat(response.getCost()).isEqualTo(0);
        assertThat(response.getVerificationStatus()).isEqualTo(VerificationStatus.FAILURE);
        assertThat(response.getFailureReason()).isEqualTo(invalidPartnerId);
    }

    private Response getResponseFromVerifier(ServerResponseProvider provider, String code)
            throws PremiumSmsException {
        CodeVerifier verifier = new CodeVerifier(provider, getConfig());
        return verifier.verify(get3PLNQueryCode(code));
    }

}
