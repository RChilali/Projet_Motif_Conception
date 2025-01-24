package src.model.actions.binary;

import src.model.actions.Action;
import src.model.individual.Individual;

public class StompAction extends Action {
    @Override
    public boolean validate(Individual source, Individual target) {
        return source != target
                && source.getStats().getVitality() > 0 // Source must be alive
                && target.getStats().getVitality() > 0; // Target must be alive
    }

    @Override
    public void execute(Individual source, Individual target) {
        int damage = 15; // Fixed damage value
        System.out.println(source.getName() + " stomps on " + target.getName() + " for " + damage + " damage!");
        target.getStats().setVitality(target.getStats().getVitality() - damage);
    }
}