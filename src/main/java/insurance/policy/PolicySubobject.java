package insurance.policy;


import insurance.RiskType;
import org.joda.money.Money;

import java.math.BigDecimal;

public class PolicySubobject {
    private final String name;
    private final Money sumInsured;
    private final RiskType riskType;

    public PolicySubobject(String name, RiskType riskType, Money sumInsured) {
        if(sumInsured.getAmount().equals(BigDecimal.ZERO)) {
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