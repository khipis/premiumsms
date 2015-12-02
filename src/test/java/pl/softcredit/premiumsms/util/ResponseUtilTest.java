package pl.softcredit.premiumsms.util;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.softcredit.premiumsms.dto.Response;

import static pl.softcredit.premiumsms.enums.FailureReason.INCORRECT_MODE;
import static pl.softcredit.premiumsms.enums.FailureReason.INVALID_PARTNER_ID;
import static pl.softcredit.premiumsms.enums.FailureReason.PREVIOUSLY_USED_CODE;
import static pl.softcredit.premiumsms.enums.FailureReason.SUFFIX_OF_OTHER_PARTNER;
import static pl.softcredit.premiumsms.enums.VerificationStatus.FAILURE;
import static pl.softcredit.premiumsms.enums.VerificationStatus.SUCCESS;

@RunWith(JUnit4.class)
public class ResponseUtilTest {

    private SoftAssertions softAssertion = new SoftAssertions();

    @Test
    public void shouldParseSuccessResponse() {
        Response response = ResponseUtil.parse("1\n100\nsignature");
        softAssertion = new SoftAssertions();
        softAssertion.assertThat(response.getVerificationStatus()).isEqualTo(SUCCESS);
        softAssertion.assertThat(response.getCost()).isEqualTo(100);
        softAssertion.assertThat(response.getSignature()).isEqualTo("signature");
        softAssertion.assertAll();
    }

    @Test
    public void shouldParseFailureResponseInvalidCode() {
        Response response = ResponseUtil.parse("0\n1\n2");
        softAssertion = new SoftAssertions();
        softAssertion.assertThat(response.getVerificationStatus()).isEqualTo(FAILURE);
        softAssertion.assertThat(response.getCost()).isEqualTo(0);
        softAssertion.assertThat(response.getFailureReason()).isEqualTo(PREVIOUSLY_USED_CODE);
        softAssertion.assertAll();
    }

    @Test
    public void shouldParseFailureResponseIncorrectMode() {
        Response response = ResponseUtil.parse("0\n4\n");
        softAssertion = new SoftAssertions();
        softAssertion.assertThat(response.getVerificationStatus()).isEqualTo(FAILURE);
        softAssertion.assertThat(response.getCost()).isEqualTo(0);
        softAssertion.assertThat(response.getFailureReason()).isEqualTo(INCORRECT_MODE);
        softAssertion.assertAll();
    }

    @Test
    public void shouldParseFailureResponseSuffixOfOtherPartner() {
        Response response = ResponseUtil.parse("0\n3\n");
        softAssertion = new SoftAssertions();
        softAssertion.assertThat(response.getVerificationStatus()).isEqualTo(FAILURE);
        softAssertion.assertThat(response.getCost()).isEqualTo(0);
        softAssertion.assertThat(response.getFailureReason()).isEqualTo(SUFFIX_OF_OTHER_PARTNER);
        softAssertion.assertAll();
    }

    @Test
    public void shouldParseFailureResponseInvalidPartnerId() {
        Response response = ResponseUtil.parse("0\n5\n");
        softAssertion = new SoftAssertions();
        softAssertion.assertThat(response.getVerificationStatus()).isEqualTo(FAILURE);
        softAssertion.assertThat(response.getCost()).isEqualTo(0);
        softAssertion.assertThat(response.getFailureReason()).isEqualTo(INVALID_PARTNER_ID);
        softAssertion.assertAll();
    }
}