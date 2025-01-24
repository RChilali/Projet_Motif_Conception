package src.model.actions.binary;

import src.model.actions.Action;
import src.model.individual.Individual;

public class BiteAction extends Action {
    @Override
    public boolean validate(Individual source, Individual target) {
        return source != target 
               && source.getStats().getVitality() > 0 // Source must be alive
               && target.getStats().getVitality() > 0; // Target must be alive
    }

    @Override
    public void execute(Individual source, Individual target) {
        int damage = 10; // Fixed damage value
        System.out.println(source.getName() + " bites " + target.getName() + " for " + damage + " damage!");
        target.getStats().setVitality(target.getStats().getVitality() - damage);
        source.getStats().setVitality(source.getStats().getVitality() - 5); // Energy cost
    }
}