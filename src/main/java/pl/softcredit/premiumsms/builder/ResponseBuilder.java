package pl.softcredit.premiumsms.builder;

import pl.softcredit.premiumsms.dto.Response;
import pl.softcredit.premiumsms.enums.FailureReason;
import pl.softcredit.premiumsms.enums.VerificationStatus;

/**
 * Class ResponseBuilder, builder for ResponseDTO
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class ResponseBuilder {

    private VerificationStatus verificationStatus = VerificationStatus.FAILURE;
    private FailureReason failureReason = FailureReason.INVALID_CODE;
    private String signature;
    private int cost;

    public ResponseBuilder verificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
        return this;
    }

    public ResponseBuilder failureReason(FailureReason failureReason) {
        this.failureReason = failureReason;
        return this;
    }

    public ResponseBuilder signature(String signature) {
        this.signature = signature;
        return this;
    }

    public ResponseBuilder cost(int cost) {
        this.cost = cost;
        return this;
    }

    public Response build() {
        return new Response(verificationStatus, failureReason, signature, cost);
    }

    public static ResponseBuilder instance() {
        return new ResponseBuilder();
    }

    private ResponseBuilder() {

    }

}