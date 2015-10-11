package pl.softcredit.premiumsms.dto;

/**
 * Class Config, data transfer object for server configuration
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public final class Config {

    private final String key;
    private final String partnerId;
    private final String url;

    public Config(String key, String partnerId, String url) {
        this.key = key;
        this.partnerId = partnerId;
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Config{");
        sb.append("key='").append(key).append('\'');
        sb.append(", partnerId='").append(partnerId).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}