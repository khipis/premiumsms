package pl.softcredit.premiumsms.dto;

/**
 * Class ConfigDTO, data transfer object for server configuration
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class ConfigDTO {

    private String key;
    private String partnerId;
    private String url;

    public ConfigDTO(String key, String partnerId, String url) {
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
        final StringBuilder sb = new StringBuilder("ConfigDTO{");
        sb.append("key='").append(key).append('\'');
        sb.append(", partnerId='").append(partnerId).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}