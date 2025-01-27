package src.model.actions.binary;

import src.model.actions.Action;
import src.model.individual.Individual;

/**
 * Définition des méthodes liées à l'action "écraser".
 *
 * @see Action
 */
public class StompAction extends Action {

    private final int damage;
    private final int energyCost;

    /**
     * Constructeur permettant de définir les points de dégâts pour l'action
     *
     * @param damage     les points que perd la cible de l'action
     * @param energyCost les points que perd la source de l'action en effectuant cette action
     */
    public StompAction(int damage, int energyCost) {
        super();
        this.damage = damage;
        this.energyCost = energyCost;
    }

    /**
     * Vérifie que l'individu source et individu cible sont vivants.
     *
     * @param source individu source de l'action
     * @param target individu cible de l'action
     * @return true si les deux individus participant à l'action sont vivants, false sinon
     */
    public boolean validate(Individual source, Individual target) {
        return source != target
                && source.getStats().getVitality() > 0
                && target.getStats().getVitality() > 0;
    }

    /**
     * Définie le message qui décrit ce qui s'est passé et diminue les statistics de deux individus participants.
     *
     * @param source individu source de l'action
     * @param target individu cible de l'action
     */
    public void execute(Individual source, Individual target) {
        actionMessage = source.getName() + " écrase " + target.getName() + " pour " + damage + " points de dégâts !";
        target.getStats().setVitality(target.getStats().getVitality() - damage);
        source.getStats().setVitality(target.getStats().getVitality() - energyCost);
    }
}