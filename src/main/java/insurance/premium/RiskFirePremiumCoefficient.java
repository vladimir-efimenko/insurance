package insurance.premium;

import org.joda.money.Money;

import java.math.BigDecimal;

public class RiskFirePremiumCoefficient extends PremiumCoefficient {
    private RiskFirePremiumCoefficient() {
    }

    public static final RiskFirePremiumCoefficient INSTANCE = new RiskFirePremiumCoefficient();

    @Override
    public BigDecimal calculateCoefficient(BigDecimal sumInsured) {
        return sumInsured.compareTo(new BigDecimal(100)) == 1 ? new BigDecimal(0.024) : new BigDecimal(0.014);
    }
}
