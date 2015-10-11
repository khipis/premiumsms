package pl.softcredit.premiumsms.provider;


import pl.softcredit.premiumsms.dto.CodeQuery;
import pl.softcredit.premiumsms.dto.Config;
import pl.softcredit.premiumsms.exception.PremiumSmsException;

/**
 * Interface ServerResponseProvider
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public interface ServerResponseProvider {

    String getResponse(CodeQuery codeQuery) throws PremiumSmsException;

    void setConfig(Config config);
}
