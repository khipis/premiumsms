package pl.softcredit.premiumsms.dto;

/**
 * Class CodeQuery, data transfer object for sms code querying
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public final class CodeQuery {

    private final int cost;
    private final String suffix;
    private final String code;

    public CodeQuery(int cost, String suffix, String code) {
        this.cost = cost;
        this.suffix = suffix;
        this.code = code;
    }

    public int getCost() {
        return cost;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CodeQuery{");
        sb.append("cost=").append(cost);
        sb.append(", suffix='").append(suffix).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}