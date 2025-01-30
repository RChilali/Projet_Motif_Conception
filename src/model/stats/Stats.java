package src.model.stats;

/**
 * Définition et gestion des statistiques d'un individu.
 */
public class Stats {

    private float vitality;
    private float food;
    private float water;

    public Stats(float vitality, float food, float water) {
        this.vitality = vitality;
        this.food = food;
        this.water = water;
    }

    public float getVitality() {
        return vitality;
    }

    public void setVitality(float vitality) {
        this.vitality = vitality;
    }

    public float getFood() {
        return food;
    }

    public void setFood(float food) {
        this.food = food;
    }

    public float getWater() {
        return water;
    }

    public void setWater(float water) {
        this.water = water;
    }

    public void modifyStatsByDelta(Stats statsDelta) {
        this.food += statsDelta.getFood();
        this.water += statsDelta.getWater();
        this.vitality += statsDelta.getVitality();
    }

    @Override
    public String toString() {
        return "\u001B[32mStats{" + "vitality=" + vitality + ", food=" + food + ", water=" + water
                + "}\u001B[0m";
    }
}