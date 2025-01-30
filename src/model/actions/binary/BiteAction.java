package src.model.actions.binary;

import src.model.actions.Action;
import src.model.individual.Individual;
import src.model.stats.Stats;

/**
 * Définition des méthodes liées à l'action "mordre".
 *
 * @see Action
 */
public class BiteAction extends BinaryAction {

    /**
     * Constructeur permettant de définir les points de dégâts pour l'action
     *
     * @param damage les points que perd la cible de l'action
     * @param cost   les points que perd la source de l'action en effectuant cette action
     */
    public BiteAction(Stats damage, Stats cost) {
        super(damage, cost);
    }

    public void setActionMessage(Individual source, Individual target) {
        actionMessage =
                source.getName() + " mord " + target.getName() + " pour " + getDamage() + "  points de dégâts !";
    }

    public static String getActionName() {
        return "mordre";
    }
}