# Policy Premium Calculator

### Implementation description

The suggested implementation is based on:
* the requirement to easily extend implementation for new risk types; 
* an observation that the only moving part of the calculation is a coefficient applied to the sum insured of a special risk type; 
* an assumption that the main calculation formula (as specified in the task description section) will remain the same when adding new risk types and coefficients.  

Moving parts of the calculation are encapsulated in the instances of the PremiumCoefficient class.
The PremiumCoefficient is abstract and contains static factory method which returns an instance of a PremiumCoefficient for the specified RiskType.  

So to extend the calculator with a new risk type the required changes to the source code are:
1. Add a new RiskType enum value, e.g. `RiskType.FLOOD`
2. Add a new PremiumCoefficient class extending base abstract class, e.g. `RiskFloodCoefficient` and implement coefficient calculation logic based on sum insured.
3. Update `PremiumCoefficient.of(RiskType)` method to return a new instance of the new class for `RiskType.FLOOD` 

The rest of the code should remain the same, given no big changes in the main formula.
