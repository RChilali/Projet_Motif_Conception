package src.model.actions;

import src.model.individual.Individual;

public abstract class Action {

    public abstract boolean validate(Individual source,Individual target);
    public abstract void execute(Individual source, Individual target);
}