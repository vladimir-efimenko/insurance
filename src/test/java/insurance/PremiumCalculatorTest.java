package insurance;

import static org.junit.Assert.assertEquals;

import insurance.policy.Policy;
import insurance.policy.PolicyObject;
import insurance.policy.PolicySubobject;
import insurance.premium.NotSupportedCurrencyException;
import insurance.premium.PremiumCalculator;
import insurance.premium.UnknownRiskException;
import org.joda.money.Money;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PremiumCalculatorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void emptyPolicy_CalculatesZeroEur() throws NotSupportedCurrencyException, UnknownRiskException {
        Money result = PremiumCalculator.calculate(new Policy("P1"));

        assertEquals(Money.parse("EUR 0"), result);
    }

    @Test
    public void oneFireOneTheftDefaultCoefficient_CalculatesCorrectly() throws UnknownRiskException, NotSupportedCurrencyException {
        Policy policy = new Policy("LV20-02-100000-5");
        PolicyObject tinyHouse = new PolicyObject("TinyHouse");
        tinyHouse.addSubobject(new PolicySubobject("Sofa", RiskType.FIRE, Money.parse("EUR 100")));
        tinyHouse.addSubobject(new PolicySubobject("TV", RiskType.THEFT, Money.parse("EUR 8")));
        policy.addPolicyObject(tinyHouse);

        Money result = PremiumCalculator.calculate(policy);
        assertEquals(Money.parse("EUR 2.28"), result);
    }

    @Test
    public void oneFireOneTheftBiggerCoefficient_CalculatesCorrectly() throws UnknownRiskException, NotSupportedCurrencyException {
        Policy policy = new Policy("LV20-02-100000-5");
        PolicyObject tinyHouse = new PolicyObject("TinyHouse");
        tinyHouse.addSubobject(new PolicySubobject("Sofa", RiskType.FIRE, Money.parse("EUR 500")));
        tinyHouse.addSubobject(new PolicySubobject("TV", RiskType.THEFT, Money.parse("EUR 102.51")));
        policy.addPolicyObject(tinyHouse);

        Money result = PremiumCalculator.calculate(policy);
        assertEquals(Money.parse("EUR 17.13"), result);
    }

    @Test
    public void subobjectDifferentCurrencies_DifferentRiskTypes_ThrowsCurrencyMismatchException() throws UnknownRiskException, NotSupportedCurrencyException {
        thrown.expect(NotSupportedCurrencyException.class);
        thrown.expectMessage("The currency USD is not supported.");

        Policy policy = new Policy("LV20-02-100000-5");
        PolicyObject tinyHouse = new PolicyObject("TinyHouse");
        tinyHouse.addSubobject(new PolicySubobject("Sofa", RiskType.FIRE, Money.parse("EUR 500")));
        tinyHouse.addSubobject(new PolicySubobject("TV", RiskType.THEFT, Money.parse("USD 100")));
        policy.addPolicyObject(tinyHouse);

        PremiumCalculator.calculate(policy);
    }

    @Test
    public void subobjectDifferentCurrencies_SameRiskType_ThrowsCurrencyMismatchException() throws UnknownRiskException, NotSupportedCurrencyException {
        thrown.expect(NotSupportedCurrencyException.class);
        thrown.expectMessage("The currency USD is not supported.");

        Policy policy = new Policy("LV20-02-100000-5");
        PolicyObject tinyHouse = new PolicyObject("TinyHouse");
        tinyHouse.addSubobject(new PolicySubobject("Sofa", RiskType.FIRE, Money.parse("EUR 500")));
        tinyHouse.addSubobject(new PolicySubobject("TV", RiskType.FIRE, Money.parse("USD 100")));
        policy.addPolicyObject(tinyHouse);

        PremiumCalculator.calculate(policy);
    }
}
