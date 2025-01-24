package src.model.actions;

import src.model.individual.Individual;

public abstract class Action {

    public boolean validate(Individual source, Individual target) {
        return true;
    }

    public void execute(Individual source, Individual target) {

    }

}