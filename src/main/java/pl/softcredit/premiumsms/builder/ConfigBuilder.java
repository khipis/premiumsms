package pl.softcredit.premiumsms.builder;

import pl.softcredit.premiumsms.dto.ConfigDTO;

/**
 * Class ConfigBuilder, builder for ConfigDTO
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class ConfigBuilder {

    private String key = "";
    private String partnerId = "";
    private String url = "";

    public ConfigBuilder key(String key) {
        this.key = key;
        return this;
    }

    public ConfigBuilder partnerId(String partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public ConfigBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ConfigDTO build() {
        return new ConfigDTO(key, partnerId, url);
    }

    public static ConfigBuilder instance(){
        return new ConfigBuilder();
    }

    private ConfigBuilder() {
    }

}

