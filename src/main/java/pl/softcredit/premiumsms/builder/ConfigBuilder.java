package pl.softcredit.premiumsms.builder;

import pl.softcredit.premiumsms.dto.Config;

/**
 * Class ConfigBuilder, builder for Config
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public final class ConfigBuilder {

    private String key;
    private String partnerId;
    private String url;

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

    public Config build() {
        return new Config(key, partnerId, url);
    }

    public static ConfigBuilder instance(){
        return new ConfigBuilder();
    }

    private ConfigBuilder() {
    }

}

