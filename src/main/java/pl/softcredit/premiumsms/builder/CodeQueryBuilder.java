package pl.softcredit.premiumsms.builder;

import pl.softcredit.premiumsms.dto.CodeQueryDTO;

/**
 * Class CodeQueryBuilder, builder for CodeQueryDTO
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class CodeQueryBuilder {

    private Integer cost;
    private String suffix;
    private String code;

    public CodeQueryBuilder cost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public CodeQueryBuilder suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public CodeQueryBuilder code(String code) {
        this.code = code;
        return this;
    }

    public static CodeQueryBuilder instance() {
        return new CodeQueryBuilder();
    }

    private CodeQueryBuilder() {
    }

    public CodeQueryDTO build() {
        return new CodeQueryDTO(cost, suffix, code);
    }
}