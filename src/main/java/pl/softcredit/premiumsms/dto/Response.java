package pl.softcredit.premiumsms.dto;

import pl.softcredit.premiumsms.enums.FailureReason;
import pl.softcredit.premiumsms.enums.VerificationStatus;

/**
 * Class Response, data transfer object for server response
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public final class Response {

    private final VerificationStatus verificationStatus;
    private final FailureReason failureReason;
    private final String signature;
    private final int cost;

    public Response(VerificationStatus verificationStatus, FailureReason failureReason,
                         String signature, int cost) {
        this.verificationStatus = verificationStatus;
        this.failureReason = failureReason;
        this.signature = signature;
        this.cost = cost;
    }

    public Response(Response response) {
        this.verificationStatus = response.getVerificationStatus();
        this.failureReason = response.getFailureReason();
        this.signature = response.getSignature();
        this.cost = response.getCost();
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public FailureReason getFailureReason() {
        return failureReason;
    }

    public String getSignature() {
        return signature;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseDTO{");
        sb.append("verificationStatus=").append(verificationStatus);
        sb.append(", failureReason=").append(failureReason);
        sb.append(", signature='").append(signature).append('\'');
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}