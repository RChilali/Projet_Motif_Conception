package src.model.individual;

import src.model.stats.Stats;

public class Dog extends Individual {

    private Gender gender;

    public Dog(String id, String name, String species, Stats stats) {
        this(id, name, species, stats, Gender.male);
    }

    public Dog(String id, String name, String species, Stats stats, Gender gender) {
        super(id, name, species, stats);
        setGender(gender);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("Dog [ID: %s, Name: %s, Gender: %c]", getId(), getName(), gender);
    }

    public enum Gender {
        male,
        female
    }
}