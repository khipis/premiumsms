package pl.softcredit.premiumsms.provider.strategy;

import pl.softcredit.premiumsms.Constants;
import pl.softcredit.premiumsms.dto.CodeQuery;
import pl.softcredit.premiumsms.dto.Config;
import pl.softcredit.premiumsms.exception.PremiumSmsException;
import pl.softcredit.premiumsms.provider.ServerResponseProvider;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class PlatnosciOnlineResponseProvider, main strategy
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class PlatnosciOnlineResponseProvider implements ServerResponseProvider {

    private String response;
    private Config config;

    @Override
    public String getResponse(CodeQuery codeQuery) throws PremiumSmsException {

        try {
            HttpURLConnection connection = prepareConnection(config);

            try (DataOutputStream dataOutputStream = new DataOutputStream(
                    connection.getOutputStream())) {
                dataOutputStream.writeBytes(buildParameters(codeQuery, config));
            }

            try (DataInputStream dataInputStream = new DataInputStream(
                    connection.getInputStream())) {
                response = readStream(dataInputStream);
            }

        } catch (IOException e) {
            throw new PremiumSmsException("Cannot get response from server!" + e.getMessage());
        }


        return response;
    }

    private HttpURLConnection prepareConnection(Config config) throws IOException {
        HttpURLConnection connection =
                (HttpURLConnection) new URL(config.getUrl()).openConnection();
        connection.setRequestMethod(Constants.POST_METHOD);
        connection.setDoOutput(true);
        return connection;
    }


    private String buildParameters(CodeQuery query, Config config) {
        return new StringBuilder().append(Constants.SMS_CODE_URL_PARAMETER)
                .append(query.getCode())
                .append(Constants.AND_OPERATOR)
                .append(Constants.PARTNER_ID_URL_PARAMETER)
                .append(config.getPartnerId())
                .append(Constants.AND_OPERATOR)
                .append(Constants.SUFFIX_URL_PARAMETER)
                .append(query.getSuffix()).toString();
    }

    private String readStream(DataInputStream dataInputStream) throws IOException {
        StringBuilder response = new StringBuilder();
        int readChar;

        while ((readChar = dataInputStream.read()) != -1) {
            response.append((char) readChar);
        }

        return response.toString();
    }

    @Override
    public void setConfig(Config config) {
        this.config = config;
    }

}
