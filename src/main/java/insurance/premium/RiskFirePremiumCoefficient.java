package insurance.premium;


import java.math.BigDecimal;

public class RiskFirePremiumCoefficient extends PremiumCoefficient {
    public static final RiskFirePremiumCoefficient INSTANCE = new RiskFirePremiumCoefficient();

    private RiskFirePremiumCoefficient() {
    }

    @Override
    public BigDecimal calculateCoefficient(BigDecimal sumInsured) {
        return sumInsured.compareTo(new BigDecimal(100)) == 1 ? new BigDecimal(0.024) : new BigDecimal(0.014);
    }
}
