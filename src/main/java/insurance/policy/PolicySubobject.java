package insurance.policy;


import insurance.RiskType;
import org.joda.money.Money;

import java.math.BigDecimal;

public class PolicySubobject {
    private final String name;
    private final Money sumInsured;
    private final RiskType riskType;

    public PolicySubobject(String name, RiskType riskType, Money sumInsured) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("PolicySubobject name can not be null or empty");
        }
        if(sumInsured.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("sumInsured can not be zero");
        }
        this.name = name;
        this.sumInsured = sumInsured;
        this.riskType = riskType;
    }

    public String getName() {
        return name;
    }

    public RiskType getRiskType() {
        return  riskType;
    }

    public Money getSumInsured() {
        return sumInsured;
    }
}
