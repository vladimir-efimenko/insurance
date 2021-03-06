package insurance.policy;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Policy {
    private final String policyNumber;
    private final List<PolicyObject> objects;
    private PolicyStatus status;

    public Policy(String policyNumber) {
        this(policyNumber, new ArrayList<>());
    }

    public Policy(String policyNumber, List<PolicyObject> policyObjects) {
        if(policyNumber == null || policyNumber.isEmpty()) {
            throw new IllegalArgumentException("Policy Number can not be null or empty");
        }
        this.policyNumber = policyNumber;
        this.objects = policyObjects != null ? policyObjects : new ArrayList<>();
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void addPolicyObject(PolicyObject obj) {
        objects.add(obj);
    }

    public boolean removePolicyObject(PolicyObject obj) {
        return objects.remove(obj);
    }

    public List<PolicyObject> getObjects() {
        return Collections.unmodifiableList(objects);
    }

    public  PolicyStatus getStatus() {
        return status;
    }

    public void setStatus(PolicyStatus status) {
        this.status = status;
    }
}
