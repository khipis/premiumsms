package pl.softcredit.premiumsms;

import pl.softcredit.premiumsms.dto.CodeQuery;
import pl.softcredit.premiumsms.dto.Config;
import pl.softcredit.premiumsms.dto.Response;
import pl.softcredit.premiumsms.enums.VerificationStatus;
import pl.softcredit.premiumsms.exception.PremiumSmsException;
import pl.softcredit.premiumsms.provider.ServerResponseProvider;
import pl.softcredit.premiumsms.util.ResponseUtil;
import pl.softcredit.premiumsms.util.SignatureUtil;

import java.security.NoSuchAlgorithmException;

/**
 * Class CodeVerifier , main class for verifying sms code
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public final class CodeVerifier  {

    private final Config config;
    private final ServerResponseProvider responseProvider;

    public CodeVerifier(ServerResponseProvider responseProvider, Config config) {
        this.responseProvider = responseProvider;
        this.config = config;
        responseProvider.setConfig(config);
    }

    public Response verify(CodeQuery codeQuery) throws PremiumSmsException {

        String response = responseProvider.getResponse(codeQuery);

        String computedSignature;
        try {
            computedSignature = SignatureUtil.compute(config, codeQuery);
        } catch (NoSuchAlgorithmException e) {
            throw new PremiumSmsException("Problem with signature computing: " + e.getMessage());
        }

        Response responseDTO = ResponseUtil.parse(response);

        if (responseDTO.getVerificationStatus().equals(VerificationStatus.SUCCESS)) {
            boolean signatureCorrect = computedSignature.equals(responseDTO.getSignature());
            if (!signatureCorrect) {
                return new Response(responseDTO);
            }
        }

        return responseDTO;
    }


}
