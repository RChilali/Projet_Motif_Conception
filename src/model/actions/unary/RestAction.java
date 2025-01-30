package src.model.actions.unary;

import src.model.actions.Action;
import src.model.individual.Individual;
import src.model.stats.Stats;

/**
 * Définition des méthodes liées à l'action "dormir".
 *
 * @see Action
 */
public class RestAction extends UnaryAction {

    /**
     * Constructeur permettant de définir les points de regenération pour l'action.
     *
     * @param cost les points que reçoit l'individu-source de l'action
     */
    public RestAction(Stats cost) {
        super(cost);
    }

    @Override
    public boolean validate(Individual source, Individual target) {
        return source.getStats().getVitality() < (100 - getCost().getVitality()) && source.getStats().getVitality() > 0;
    }

    @Override
    public void setActionMessage(Individual source) {
        actionMessage = source.getName() + " dort";
    }

    public static String getActionName() {
        return "dormir";
    }
}