package insurance.premium;

import insurance.policy.Policy;
import org.joda.money.Money;

import java.math.BigDecimal;

public class RiskTheftPremiumCoefficient extends PremiumCoefficient {
    public static final RiskTheftPremiumCoefficient INSTANCE = new RiskTheftPremiumCoefficient();

    @Override
    public BigDecimal calculateCoefficient(BigDecimal sumInsured) {
        return sumInsured.compareTo(new BigDecimal(15)) == -1 ? new BigDecimal(0.11) : new BigDecimal(0.05);
    }
}
