package src.model.actions.unary;

import src.model.actions.Action;
import src.model.individual.Individual;
import src.model.stats.Stats;

/**
 * Définition d'action qui inclut un individu.
 */
public abstract class UnaryAction extends Action {

    /**
     * Constructeur permettant de définir les points de regenération pour l'action.
     *
     * @param cost les points que reçoit l'individu-source de l'action
     */
    public UnaryAction(Stats cost) {
        super(cost);
    }

    public boolean validate(Individual source) {
        return source.getStats().getVitality() > 0;
    }

    /**
     * Modifie le message à afficher et altère l'état d'un individu participant.
     *
     * @param source individu source de l'action
     */
    @Override
    public void execute(Individual source) {
        setActionMessage(source);
        modifyStats(source, cost);
    }

    /**
     * Définie le message qui décrit ce qui se passe.
     *
     * @param source individu source de l'action
     */
    public abstract void setActionMessage(Individual source);

}
