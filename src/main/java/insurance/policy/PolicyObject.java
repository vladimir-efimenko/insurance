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
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("PolicyObject name can not be null or empty");
        }
        this.name = name;
        this.subobjects = subobjects != null ? subobjects : new ArrayList<>();
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
