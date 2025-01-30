package src.model.actions.binary;

import src.model.actions.Action;
import src.model.individual.Individual;
import src.model.stats.Stats;

public abstract class BinaryAction extends Action {

    private final Stats damage;
    private final Stats cost;

    /**
     * Constructeur permettant de définir les points de dégâts pour l'action
     *
     * @param damage les points que perd la cible de l'action
     * @param cost   les points que perd la source de l'action en effectuant cette action
     */
    public BinaryAction(Stats damage, Stats cost) {
        super();
        this.damage = damage;
        this.cost = cost;
    }

    /**
     * Définie le message qui décrit ce qui s'est passé et diminue les statistics de deux individus participants.
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
     * Définie le message qui décrit ce qui s'est passé et diminue les statistics de deux individus participants.
     *
     * @param source individu source de l'action
     * @param target individu cible de l'action
     */
    public abstract void setActionMessage(Individual source, Individual target);

    public Stats getDamage() {
        return damage;
    }

    public Stats getCost() {
        return cost;
    }
}
