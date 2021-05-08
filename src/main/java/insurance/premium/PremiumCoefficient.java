package insurance.premium;


import insurance.RiskType;
import org.joda.money.Money;

import java.math.BigDecimal;

public abstract class PremiumCoefficient {
    public abstract BigDecimal calculateCoefficient(BigDecimal sumInsured);

    public static PremiumCoefficient of(RiskType riskType) throws UnknownRiskException {
        switch (riskType) {
            case FIRE: return RiskFirePremiumCoefficient.INSTANCE;
            case THEFT: return RiskTheftPremiumCoefficient.INSTANCE;
            default: throw new UnknownRiskException(riskType);
        }
    }
}
