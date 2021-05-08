package insurance.premium;

import insurance.RiskType;
import insurance.policy.Policy;
import org.joda.money.CurrencyMismatchException;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PremiumCalculator {
    private PremiumCalculator() {
    }

    /**
     * Calculates premium for the specified policy. The current version supports only single currency EUR for sum insured.
     *
     * @param policy
     * @return Calculated premium
     * @throws UnknownRiskException
     */
    public static Money calculate(Policy policy) throws UnknownRiskException, NotSupportedCurrencyException {
        Money premium = Money.zero(CurrencyUnit.EUR);

        for (RiskType riskType : RiskType.values()) {
            Money perRisk = null;
            try {
                perRisk = calculatePerRisk(policy, riskType);
                premium = premium.plus(perRisk);
            } catch (CurrencyMismatchException e) {
                throw new NotSupportedCurrencyException(perRisk.getCurrencyUnit().getCode());
            } catch (NotSupportedCurrencyException e) {
                throw e;
            }
        }
        return premium;
    }

    private static Money calculatePerRisk(Policy policy, RiskType riskType) throws UnknownRiskException, NotSupportedCurrencyException {
        try {
            Money totalPerRisk = policy.getObjects().stream()
                    .flatMap(o -> o.getSubobjects().stream())
                    .filter(so -> so.getRiskType() == riskType)
                    .map(so -> so.getSumInsured())
                    .reduce(Money.zero(CurrencyUnit.EUR), Money::plus);

            PremiumCoefficient coeff = PremiumCoefficient.of(riskType);
            BigDecimal amount = totalPerRisk.getAmount();
            return totalPerRisk.multipliedBy(coeff.calculateCoefficient(amount), RoundingMode.HALF_EVEN);
        } catch (CurrencyMismatchException e) {
            throw new NotSupportedCurrencyException(e.getSecondCurrency().getCode());
        }
    }
}
