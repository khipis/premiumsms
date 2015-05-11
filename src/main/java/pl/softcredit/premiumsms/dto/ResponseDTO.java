package pl.softcredit.premiumsms.dto;

import pl.softcredit.premiumsms.enums.FailureReason;
import pl.softcredit.premiumsms.enums.VerificationStatus;

/**
 * Class ResponseDTO, data transfer object for server response
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class ResponseDTO {

    private VerificationStatus verificationStatus;
    private FailureReason failureReason;
    private String signature;
    private int cost;

    public ResponseDTO(VerificationStatus verificationStatus, FailureReason failureReason,
                       String signature, int cost) {
        this.verificationStatus = verificationStatus;
        this.failureReason = failureReason;
        this.signature = signature;
        this.cost = cost;
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
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