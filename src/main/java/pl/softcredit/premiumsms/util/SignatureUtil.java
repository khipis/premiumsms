package pl.softcredit.premiumsms.util;

import org.apache.commons.lang3.ArrayUtils;

import pl.softcredit.premiumsms.dto.CodeQuery;
import pl.softcredit.premiumsms.dto.Config;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 * Class SignatureUtil, computes transactions signatures
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public final class SignatureUtil {

    public static final String MD5 = "MD5";

    public static String compute(Config config, CodeQuery codeQuery)
            throws NoSuchAlgorithmException {
        String data = config.getPartnerId() + codeQuery.getSuffix() + codeQuery.getCode();
        byte[] input = ArrayUtils
                .addAll(data.getBytes(Charset.forName("UTF-8")), DatatypeConverter.parseHexBinary(config.getKey()));
        return new BigInteger(1, MessageDigest.getInstance(MD5).digest(input)).toString(16);
    }

    private SignatureUtil(){
    }
}