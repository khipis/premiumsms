package pl.softcredit.premiumsms;

import pl.softcredit.premiumsms.dto.ConfigDTO;
import pl.softcredit.premiumsms.dto.CodeQueryDTO;
import pl.softcredit.premiumsms.dto.ResponseDTO;
import pl.softcredit.premiumsms.enums.VerificationStatus;
import pl.softcredit.premiumsms.exception.PremiumSmsException;
import pl.softcredit.premiumsms.provider.ServerResponseProvider;
import pl.softcredit.premiumsms.provider.strategy.PlatnosciOnlineResponseProvider;
import pl.softcredit.premiumsms.util.ResponseUtil;
import pl.softcredit.premiumsms.util.SignatureUtil;

import java.security.NoSuchAlgorithmException;

/**
 * Class CodeVerifier , main class for verifying sms code
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class CodeVerifier  {

    private ConfigDTO config;
    private ServerResponseProvider responseProvider;

    public CodeVerifier(ServerResponseProvider responseProvider, ConfigDTO config) {
        this.responseProvider = responseProvider;
        this.config = config;
        responseProvider.setConfig(config);
    }

    public CodeVerifier(ConfigDTO config) {
        this.responseProvider = new PlatnosciOnlineResponseProvider();
        this.config = config;
        responseProvider.setConfig(config);
    }

    public ResponseDTO verify(CodeQueryDTO codeQuery) throws PremiumSmsException {

        String response = responseProvider.getResponse(codeQuery);

        String computedSignature;
        try {
            computedSignature = SignatureUtil.compute(config, codeQuery);
        } catch (NoSuchAlgorithmException e) {
            throw new PremiumSmsException("Problem with signature computing: " + e.getMessage());
        }

        ResponseDTO responseDTO = ResponseUtil.parse(response);

        if (responseDTO.getVerificationStatus().equals(VerificationStatus.SUCCESS)) {
            boolean signatureCorrect = computedSignature.equals(responseDTO.getSignature());
            if (!signatureCorrect) {
                responseDTO.setVerificationStatus(VerificationStatus.SUCCESS_BAD_SIGNATURE);
            }
        }

        return responseDTO;
    }


}
