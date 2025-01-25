package src.model.actions.unary;

import src.model.actions.Action;
import src.model.individual.Individual;

public class BarkAction extends Action {
    public boolean validate(Individual source, Individual target) {
        return source.getStats().getVitality() > 0; // Can bark if alive
    }

    public void execute(Individual source, Individual target) {
        System.out.println(source.getName() + " barks loudly!");
        source.getStats().setVitality(source.getStats().getVitality() - 5); // Energy cost
    }
}