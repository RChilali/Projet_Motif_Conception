package src.model.actions;

import src.model.individual.Individual;

/**
 * L'action appliquée aux individus.
 */
public abstract class Action {

    /** Message affiché lors de l'exécution réussie de l'action */
    public String actionMessage;

    public abstract boolean validate(Individual source,Individual target);

    public abstract void execute(Individual source, Individual target);

    /**
     * @return le message affiché lors de l'exécution réussie de l'action
     */
    public String getActionMessage() {
        return actionMessage == null ? "" : actionMessage;
    }
}