package pl.softcredit.premiumsms.provider.strategy;

import pl.softcredit.premiumsms.dto.CodeQuery;
import pl.softcredit.premiumsms.dto.Config;
import pl.softcredit.premiumsms.exception.PremiumSmsException;
import pl.softcredit.premiumsms.provider.ServerResponseProvider;

/**
 * Class SuccessResponseProvider, useful for testing valid codes
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class SuccessResponseProvider implements ServerResponseProvider {

    private String gross;
    private String signature;

    public SuccessResponseProvider(String gross, String signature) {
        this.gross = gross;
        this.signature = signature;
    }

    @Override
    public String getResponse(CodeQuery codeQuery) throws PremiumSmsException {
        return "1\n" + gross + "\n" + signature;
    }

    @Override
    public void setConfig(Config config) {

    }

}
