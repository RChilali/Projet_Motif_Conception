package src.model.stats;

public class Stats {
    private float vitality;
    private float food;
    private float water;

    public Stats(float vitality, float food, float water) {
        this.vitality = vitality;
        this.food = food;
        this.water = water;
    }

    // Getters and setters
    public float getVitality() { return vitality; }
    public void setVitality(float vitality) { this.vitality = vitality; }
    public float getFood() { return food; }
    public void setFood(float food) { this.food = food; }
    public float getWater() { return water; }
    public void setWater(float water) { this.water = water; }
}