package pl.softcredit.premiumsms.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.softcredit.premiumsms.dto.Response;
import pl.softcredit.premiumsms.enums.FailureReason;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.softcredit.premiumsms.enums.VerificationStatus.FAILURE;
import static pl.softcredit.premiumsms.enums.VerificationStatus.SUCCESS;

@RunWith(JUnit4.class)
public class ResponseUtilTest {

    @Test
    public void shouldParseSuccessResponse() {
        Response response = ResponseUtil.parse("1\n100\nsignature");
        assertThat(response.getVerificationStatus()).isEqualTo(SUCCESS);
        assertThat(response.getCost()).isEqualTo(100);
        assertThat(response.getSignature()).isEqualTo("signature");
    }

    @Test
    public void shouldParseFailureResponseInvalidCode() {
        Response response = ResponseUtil.parse("0\n1\n2");
        assertThat(response.getVerificationStatus()).isEqualTo(FAILURE);
        assertThat(response.getCost()).isEqualTo(0);
        assertThat(response.getFailureReason()).isEqualTo(FailureReason.PREVIOUSLY_USED_CODE);
    }

    @Test
    public void shouldParseFailureResponseIncorrectMode() {
        Response response = ResponseUtil.parse("0\n4\n");
        assertThat(response.getVerificationStatus()).isEqualTo(FAILURE);
        assertThat(response.getCost()).isEqualTo(0);
        assertThat(response.getFailureReason()).isEqualTo(FailureReason.INCORRECT_MODE);
    }

    @Test
    public void shouldParseFailureResponseSuffixOfOtherPartner() {
        Response response = ResponseUtil.parse("0\n3\n");
        assertThat(response.getVerificationStatus()).isEqualTo(FAILURE);
        assertThat(response.getCost()).isEqualTo(0);
        assertThat(response.getFailureReason()).isEqualTo(FailureReason.SUFFIX_OF_OTHER_PARTNER);
    }

    @Test
    public void shouldParseFailureResponseInvalidPartnerId() {
        Response response = ResponseUtil.parse("0\n5\n");
        assertThat(response.getVerificationStatus()).isEqualTo(FAILURE);
        assertThat(response.getCost()).isEqualTo(0);
        assertThat(response.getFailureReason()).isEqualTo(FailureReason.INVALID_PARTNER_ID);
    }
}