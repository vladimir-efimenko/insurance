package insurance;

import static junit.framework.TestCase.assertEquals;

import insurance.premium.RiskTheftPremiumCoefficient;
import org.junit.Test;

import insurance.premium.RiskFirePremiumCoefficient;

import java.math.BigDecimal;


public class PremiumCoefficientTest {

    @Test
    public void riskFirePremiumCoefficient_CalculatesDefault() {
        RiskFirePremiumCoefficient c = RiskFirePremiumCoefficient.INSTANCE;
        BigDecimal val = c.calculateCoefficient(new BigDecimal(100));

        assertEquals("Wrong default coefficient", new BigDecimal(0.014), val);
    }

    @Test
    public void riskFirePremiumCoefficient_SumOver100_CalculatesBigger() {
        RiskFirePremiumCoefficient c = RiskFirePremiumCoefficient.INSTANCE;
        BigDecimal val = c.calculateCoefficient(new BigDecimal(100.01));

        assertEquals("Wrong bigger coefficient", new BigDecimal(0.024), val);
    }

    @Test
    public void riskTheftPremiumCoefficient_CalculatesDefault() {
        RiskTheftPremiumCoefficient c = RiskTheftPremiumCoefficient.INSTANCE;
        BigDecimal val = c.calculateCoefficient(new BigDecimal(14.99));

        assertEquals("Wrong default coefficient", new BigDecimal(0.11), val);
    }

    @Test
    public void riskTheftPremiumCoefficient_Sum15_CalculatesSmaller() {
        RiskTheftPremiumCoefficient c = RiskTheftPremiumCoefficient.INSTANCE;
        BigDecimal val = c.calculateCoefficient(new BigDecimal(15));

        assertEquals("Wrong bigger coefficient", new BigDecimal(0.05), val);
    }
}
