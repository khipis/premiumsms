package pl.softcredit.premiumsms.builder;

import pl.softcredit.premiumsms.dto.CodeQuery;

/**
 * Class CodeQueryBuilder, builder for CodeQuery
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public final class CodeQueryBuilder {

    private int cost;
    private String suffix;
    private String code;

    public CodeQueryBuilder cost(int cost) {
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

    public CodeQuery build() {
        return new CodeQuery(cost, suffix, code);
    }
}