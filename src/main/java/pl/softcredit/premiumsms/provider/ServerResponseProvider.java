package pl.softcredit.premiumsms.provider;


import pl.softcredit.premiumsms.dto.CodeQueryDTO;
import pl.softcredit.premiumsms.dto.ConfigDTO;
import pl.softcredit.premiumsms.exception.PremiumSmsException;

/**
 * Interface ServerResponseProvider
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public interface ServerResponseProvider {

    String getResponse(CodeQueryDTO codeQuery) throws PremiumSmsException;

    void setConfig(ConfigDTO config);
}
