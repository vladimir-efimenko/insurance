package insurance.premium;


import java.math.BigDecimal;

public class RiskTheftPremiumCoefficient extends PremiumCoefficient {
    public static final RiskTheftPremiumCoefficient INSTANCE = new RiskTheftPremiumCoefficient();

    private RiskTheftPremiumCoefficient() {
    }

    @Override
    public BigDecimal calculateCoefficient(BigDecimal sumInsured) {
        return sumInsured.compareTo(new BigDecimal(15)) == -1 ? new BigDecimal(0.11) : new BigDecimal(0.05);
    }
}
