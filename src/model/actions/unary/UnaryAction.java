package src.model.actions.unary;

import src.model.actions.Action;
import src.model.individual.Individual;
import src.model.stats.Stats;

public abstract class UnaryAction extends Action {

    private Stats cost;

    /**
     * Constructeur permettant de définir les points de regenération pour l'action.
     *
     * @param cost les points que reçoit l'individu-source de l'action
     */
    public UnaryAction(Stats cost) {
        this.cost = cost;
    }

    public boolean validate(Individual source, Individual target) {
        return source.getStats().getVitality() > 0;
    }

    public void execute(Individual source, Individual target) {
        execute(source);
    }

    public void execute(Individual source) {
        setActionMessage(source);
        modifyStatistics(source, cost);
    }

    public abstract void setActionMessage(Individual source);

}
