package src.model.actions;

import src.model.individual.Individual;

public interface Action {
    boolean validate(Individual source, Individual target);
    void execute(Individual source, Individual target);
}