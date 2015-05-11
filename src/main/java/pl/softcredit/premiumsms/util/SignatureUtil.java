package pl.softcredit.premiumsms.util;

import org.apache.commons.lang3.ArrayUtils;

import pl.softcredit.premiumsms.dto.CodeQueryDTO;
import pl.softcredit.premiumsms.dto.ConfigDTO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 * Class SignatureUtil, computes transactions signatures
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class SignatureUtil {

    public static final String MD5 = "MD5";

    public static String compute(ConfigDTO config, CodeQueryDTO codeQuery)
            throws NoSuchAlgorithmException {
        String data = config.getPartnerId() + codeQuery.getSuffix() + codeQuery.getCode();
        byte[] input = ArrayUtils
                .addAll(data.getBytes(), DatatypeConverter.parseHexBinary(config.getKey()));
        return new BigInteger(1, MessageDigest.getInstance(MD5).digest(input)).toString(16);
    }

    private SignatureUtil(){

    }
}