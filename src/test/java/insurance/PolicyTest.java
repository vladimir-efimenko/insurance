package insurance;

import insurance.policy.Policy;
import insurance.policy.PolicyObject;
import insurance.policy.PolicySubobject;
import org.joda.money.Money;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class PolicyTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void policyAddObjectDirectlyToCollection_ThrowsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);

        Policy policy = new Policy("P");
        policy.addPolicyObject(new PolicyObject("O1"));

        policy.getObjects().add(new PolicyObject("O2"));
    }

    @Test
    public void policyObjectAddSubobjectDirectlyToCollection_ThrowsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);

        PolicyObject obj = new PolicyObject("T");
        obj.addSubobject(new PolicySubobject("S1", RiskType.THEFT, Money.parse("USD 1")));

        obj.getSubobjects().add(new PolicySubobject("S2", RiskType.THEFT, Money.parse("USD 2")));
    }

    @Test
    public void policySubobjectZeroSumInsured_ThrowsIllegalArgumentException() {
        thrown.expect(IllegalArgumentException.class);

        new PolicySubobject("S", RiskType.THEFT, Money.parse("EUR 0"));
    }
}
