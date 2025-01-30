package src.model.actions.unary;

import src.model.actions.Action;
import src.model.individual.Individual;
import src.model.stats.Stats;

/**
 * Définition des méthodes liées à l'action "aboyer".
 *
 * @see Action
 */
public class BarkAction extends UnaryAction {

    /**
     * Constructeur permettant de définir les points de dégâts pour l'action.
     *
     * @param cost les points que perd l'individu-source de l'action
     */
    public BarkAction(Stats cost) {
        super(cost);
    }

    public void setActionMessage(Individual source) {
        actionMessage = source.getName() + " aboie !";
    }

    public static String getActionName() {
        return "aboyer";
    }
}