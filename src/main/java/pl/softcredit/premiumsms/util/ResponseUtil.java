package pl.softcredit.premiumsms.util;

import pl.softcredit.premiumsms.builder.ResponseBuilder;
import pl.softcredit.premiumsms.dto.ResponseDTO;
import pl.softcredit.premiumsms.enums.FailureReason;
import pl.softcredit.premiumsms.enums.VerificationStatus;

/**
 * Class ResponseUtil, parses server response
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class ResponseUtil {

    public static ResponseDTO parse(String stringResponse) {
        String[] response = stringResponse.split("\n");

        VerificationStatus verificationStatus =
                VerificationStatus.CACHE_BY_VALUE.get(Integer.parseInt(response[0]));

        ResponseDTO responseDTO = null;

        if (verificationStatus.equals(VerificationStatus.SUCCESS)) {

            Integer cost = Integer.parseInt(response[1]);
            return ResponseBuilder.instance()
                    .verificationStatus(VerificationStatus.SUCCESS)
                    .cost(cost)
                    .signature(response[2]).build();
        }

        Integer errorCode = Integer.parseInt(response[1]);

        return ResponseBuilder.instance()
                .verificationStatus(VerificationStatus.FAILURE)
                .failureReason(FailureReason.CACHE_BY_CODE.get(errorCode)).build();

    }

    private ResponseUtil(){

    }


}