package src.model.actions.unary;

import src.model.actions.Action;
import src.model.individual.Individual;

public class RestAction extends Action {
    @Override
    public boolean validate(Individual source, Individual target) {
        return source.getStats().getVitality() < 100; // Can rest if vitality is not full
    }

    @Override
    public void execute(Individual source, Individual target) {
        source.getStats().setVitality(source.getStats().getVitality() + 20); // Restore vitality
    }
}