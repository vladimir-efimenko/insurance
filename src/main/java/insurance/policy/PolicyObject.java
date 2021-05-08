package insurance.policy;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolicyObject {
    private final String name;
    private final List<PolicySubobject> subobjects;

    public PolicyObject(String name) {
        this(name, new ArrayList<>());
    }

    public PolicyObject(String name, List<PolicySubobject> subobjects) {
        this.name = name;
        this.subobjects = subobjects;
    }

    public String getName() {
        return name;
    }

    public void addSubobject(PolicySubobject obj) {
        subobjects.add(obj);
    }

    public boolean removeSubobject(PolicySubobject obj) {
        return subobjects.remove(obj);
    }

    public List<PolicySubobject> getSubobjects() {
        return Collections.unmodifiableList(subobjects);
    }
}
