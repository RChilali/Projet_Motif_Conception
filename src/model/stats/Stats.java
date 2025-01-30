package src.model.stats;

/**
 * Définition et gestion des statistiques d'un individu.
 */
public class Stats {

    private float vitality;
    private float food;
    private float water;

    /**
     * Constructeur champ à champ.
     *
     * @param vitality niveau initial de vitalité.
     * @param food     niveau initial de nourriture.
     * @param water    niveau initial d'eau.
     */
    public Stats(float vitality, float food, float water) {
        this.vitality = vitality;
        this.food = food;
        this.water = water;
    }

    /**
     * Renvoie le niveau de vitalité.
     *
     * @return Le niveau de vitalité.
     */
    public float getVitality() {
        return vitality;
    }

    /**
     * Définit le niveau de vitalité.
     *
     * @param vitality Le nouveau niveau de vitalité.
     */
    public void setVitality(float vitality) {
        this.vitality = vitality;
    }

    /**
     * Renvoie le niveau de nourriture.
     *
     * @return Le niveau de nourriture.
     */
    public float getFood() {
        return food;
    }

    /**
     * Définit le niveau de nourriture.
     *
     * @param food Le nouveau niveau de nourriture.
     */
    public void setFood(float food) {
        this.food = food;
    }

    /**
     * Renvoie le niveau d'eau.
     *
     * @return Le niveau d'eau.
     */
    public float getWater() {
        return water;
    }

    /**
     * Définit le niveau d'eau.
     *
     * @param water Le nouveau niveau d'eau.
     */
    public void setWater(float water) {
        this.water = water;
    }

    /**
     * Modifie les statistiques en appliquant une variation donnée.
     *
     * @param statsDelta Objet Stats contenant les variations à appliquer.
     */
    public void modifyStatsByDelta(Stats statsDelta) {
        this.food += statsDelta.getFood();
        this.water += statsDelta.getWater();
        this.vitality += statsDelta.getVitality();
    }

    /**
     * Renvoie une représentation textuelle des statistiques.
     *
     * @return Une chaîne de caractères représentant les statistiques.
     */
    @Override
    public String toString() {
        return "\u001B[32mStats{" + "vitality=" + vitality + ", food=" + food + ", water=" + water
                + "}\u001B[0m";
    }
}
