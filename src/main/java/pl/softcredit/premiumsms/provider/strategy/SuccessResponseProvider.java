package pl.softcredit.premiumsms.provider.strategy;

import pl.softcredit.premiumsms.dto.CodeQueryDTO;
import pl.softcredit.premiumsms.dto.ConfigDTO;
import pl.softcredit.premiumsms.exception.PremiumSmsException;
import pl.softcredit.premiumsms.provider.ServerResponseProvider;

/**
 * Class SuccessResponseProvider, useful for testing valid codes
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class SuccessResponseProvider implements ServerResponseProvider {

    private String response;
    private ConfigDTO config;
    private String gross;
    private String signature;

    public SuccessResponseProvider(String gross, String signature) {
        this.gross = gross;
        this.signature = signature;
    }

    @Override
    public String getResponse(CodeQueryDTO codeQuery) throws PremiumSmsException {
        return "1\n" + gross + "\n" + signature;
    }

    @Override
    public void setConfig(ConfigDTO config) {

    }


}
