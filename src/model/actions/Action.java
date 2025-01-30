package src.model.actions;

import src.model.individual.Individual;
import src.model.stats.Stats;

/**
 * L'action appliquée aux individus.
 */
public abstract class Action {

    /**
     * Message affiché lors de l'exécution réussie de l'action
     */
    public String actionMessage;

    protected final Stats cost;

    protected Action(Stats cost) {
        this.cost = cost;
    }

    /**
     * Vérifie que l'individu source et l'individu cible de l'action sont vivants.
     *
     * @param source individu source de l'action
     * @param target individu cible de l'action
     */
    public boolean validate(Individual source, Individual target) {
        return source != target && source.getStats().getVitality() > 0 && target.getStats().getVitality() > 0;
    }

    public void execute(Individual source, Individual target) {

    }

    public void modifyStatistics(Individual individual, Stats statsDelta) {
        Stats stats = individual.getStats();
        stats.modifyStatsByDelta(statsDelta);
        individual.setStats(stats);
    }

    /**
     * @return le message affiché lors de l'exécution réussie de l'action
     */
    public String getActionMessage() {
        return actionMessage == null ? "" : actionMessage;
    }

}