package pl.softcredit.premiumsms.dto;


/**
 * Class CodeQueryDTO, data transfer object for sms code querying
 *
 * @author Krzysztof Korolczuk {@literal <kkorolczuk@softcredit.pl>}
 */
public class CodeQueryDTO {

    private Integer cost;
    private String suffix;
    private String code;

    public CodeQueryDTO(Integer cost, String suffix, String code) {
        this.cost = cost;
        this.suffix = suffix;
        this.code = code;
    }

    public Integer getCost() {
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
        final StringBuilder sb = new StringBuilder("CodeQueryDTO{");
        sb.append("cost=").append(cost);
        sb.append(", suffix='").append(suffix).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}