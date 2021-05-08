package insurance.premium;


import insurance.RiskType;

public class UnknownRiskException extends Exception {
    public UnknownRiskException(RiskType riskType) {
        super(String.format("Unknown RiskType %s", riskType.toString()));
    }
}
