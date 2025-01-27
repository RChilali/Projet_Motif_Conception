package src.model.actions.unary;

import src.model.actions.Action;
import src.model.individual.Individual;

public class RestAction extends Action {

    private final int restoreCost;

    /**
     * Constructeur permettant de définir les points de regenération pour l'action.
     *
     * @param restoreCost les points que reçoit l'individu-source de l'action
     */
    public RestAction(int restoreCost) {
        this.restoreCost = restoreCost;
    }

    /**
     * Vérifie que l'individu source est vivant et que son niveau de vitalité n'est pas plein.
     *
     * @param source individu source de l'action
     * @param target n'est pas utilisé
     * @return true si l'individu source est vivant, false sinon
     */
    public boolean validate(Individual source, Individual target) {
        return source.getStats().getVitality() < 100 && source.getStats().getVitality() > 0;
    }

    /**
     * Définie le message qui décrit ce qui s'est passé et augmente les statistics de l'individu.
     *
     * @param source individu source de l'action
     * @param target n'est pas utilisé
     */
    public void execute(Individual source, Individual target) {
        actionMessage = source.getName() + " dort";
        source.getStats().setVitality(source.getStats().getVitality() + restoreCost);
    }
}