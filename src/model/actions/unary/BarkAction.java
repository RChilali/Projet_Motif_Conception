package src.model.actions.unary;

import src.model.actions.Action;
import src.model.individual.Individual;

/**
 * Définition des méthodes liées à l'action "aboyer".
 *
 * @see Action
 */
public class BarkAction extends Action {

    private final int energyCost;

    /**
     * Constructeur permettant de définir les points de dégâts pour l'action.
     *
     * @param energyCost les points que perd l'individu-source de l'action
     */
    public BarkAction(int energyCost) {
        super();
        this.energyCost = energyCost;
    }

    /**
     * Vérifie que l'individu source est vivant.
     *
     * @param source individu source de l'action
     * @param target n'est pas utilisé
     * @return true si l'individu source est vivant, false sinon
     */
    public boolean validate(Individual source, Individual target) {
        return source.getStats().getVitality() > 0;
    }

    /**
     * Définie le message qui décrit ce qui s'est passé et diminue les statistics de l'individu.
     *
     * @param source individu source de l'action
     * @param target n'est pas utilisé
     */
    public void execute(Individual source, Individual target) {
        actionMessage = source.getName() + " aboie !";
        source.getStats().setVitality(source.getStats().getVitality() - energyCost);
    }
}