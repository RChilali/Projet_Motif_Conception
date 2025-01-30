package src.model.actions.binary;

import src.model.actions.Action;
import src.model.individual.Individual;
import src.model.stats.Stats;

/**
 * Définition d'action qui inclut deux individus.
 */
public abstract class BinaryAction extends Action {

    private final Stats damage;

    /**
     * Constructeur permettant de définir les points de dégâts pour l'action.
     *
     * @param damage les points que perd la cible de l'action
     * @param cost   les points que perd la source de l'action en effectuant cette action
     */
    public BinaryAction(Stats damage, Stats cost) {
        super(cost);
        this.damage = damage;
    }

    /**
     * Modifie le message qui décrit ce qui se passe entre les individus et diminue les statistiques des
     * deux individus participants.
     *
     * @param source individu source de l'action
     * @param target individu cible de l'action
     */
    public void execute(Individual source, Individual target) {
        setActionMessage(source, target);
        modifyStatistics(target, damage);
        modifyStatistics(source, cost);
    }

    /**
     * Définie le message qui décrit ce qui se passe.
     *
     * @param source individu source de l'action
     * @param target individu cible de l'action
     */
    public abstract void setActionMessage(Individual source, Individual target);

    public Stats getDamage() {
        return damage;
    }

}
